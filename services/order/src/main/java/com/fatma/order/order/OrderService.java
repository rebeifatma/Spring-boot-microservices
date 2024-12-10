package com.fatma.order.order;

import com.fatma.order.Kafka.OrderConfirmation;
import com.fatma.order.Kafka.OrderProducer;
import com.fatma.order.customer.CustomerClient;
import com.fatma.order.exception.BusinessException;
import com.fatma.order.orderLine.OrderLineRequest;
import com.fatma.order.orderLine.OrderLineService;
import com.fatma.order.payment.PaymentClient;
import com.fatma.order.payment.PaymentRequest;
import com.fatma.order.product.ProductClient;
import com.fatma.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OrderService {
    private  final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private  final OrderProducer orderProducer;
    private PaymentClient paymentClient;





    //pursase product
    //persist order
    //persisit order line
    //start payment processus

    @Transactional

    public Integer createOrder(OrderRequest request) {

        //chek customer

        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));




        //pursase product

        var purchasedProducts = productClient.purchaseProducts(request.products());


        //persist order

        var order = this.orderRepository.save(orderMapper.toOrder(request));


        //persisit order line
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        //start payment processus
        orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts));

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(this.orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(this.orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
