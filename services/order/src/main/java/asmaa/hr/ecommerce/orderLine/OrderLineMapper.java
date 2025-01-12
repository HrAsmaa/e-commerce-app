package asmaa.hr.ecommerce.orderLine;

import asmaa.hr.ecommerce.order.Order;
import asmaa.hr.ecommerce.order.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest purchaseRequest) {
        return OrderLine.builder()
                .id(purchaseRequest.id())
                .productId(purchaseRequest.productId())
                .order(Order.builder().id(purchaseRequest.orderId()).build())
                .quantity(purchaseRequest.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getProductId(),
                orderLine.getQuantity());
    }
}
