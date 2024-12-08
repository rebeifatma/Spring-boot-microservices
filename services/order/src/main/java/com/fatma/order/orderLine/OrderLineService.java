package com.fatma.order.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository produtRepository;
    private final OrderLineMapper productMapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = productMapper.toOrderLine(request);
        return produtRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return produtRepository.findAllByOrderId(orderId)
                .stream()
                .map(productMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
