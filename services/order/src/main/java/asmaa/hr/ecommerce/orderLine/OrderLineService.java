package asmaa.hr.ecommerce.orderLine;

import asmaa.hr.ecommerce.order.OrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest purchaseRequest) {
        OrderLine orderLine = this.orderLineMapper.toOrderLine(purchaseRequest);
        var order = this.orderLineRepository.save(orderLine);
        return order.getId();
    }

    public List<OrderLineResponse> getOrderLineByOrderId(Integer orderId) {
        return this.orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
