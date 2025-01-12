package asmaa.hr.ecommerce.order;

import asmaa.hr.ecommerce.customer.CustomerClient;
import asmaa.hr.ecommerce.customer.CustomerResponse;
import asmaa.hr.ecommerce.exception.BusinessException;
import asmaa.hr.ecommerce.kafka.OrderConfirmation;
import asmaa.hr.ecommerce.kafka.OrderProducer;
import asmaa.hr.ecommerce.orderLine.OrderLineService;
import asmaa.hr.ecommerce.payment.PaymentClient;
import asmaa.hr.ecommerce.payment.PaymentRequest;
import asmaa.hr.ecommerce.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient custumerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest){
        // check the customer -> calloing microservice using OpenFeign
        CustomerResponse customerResponse = custumerClient.findCustomerById(orderRequest.cutomerId())
                .orElseThrow(() -> new BusinessException("No customer found with the provided id"));

        // purchase the products -> calling microservice using restTemplate
        var purchaseResponse = this.productClient.purchaseProducts(orderRequest.products());

        // persist order
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        // persist order lines
        for(PurchaseRequest purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        // start payment process

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customerResponse
        );
        // send the payment process

        this.paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customerResponse,
                        purchaseResponse
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return this.orderRepository
                .findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
