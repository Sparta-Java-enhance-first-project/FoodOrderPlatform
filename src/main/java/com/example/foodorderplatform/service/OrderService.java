package com.example.foodorderplatform.service;

import com.example.foodorderplatform.dto.*;
import com.example.foodorderplatform.entity.*;
import com.example.foodorderplatform.enumclass.BankEnum;
import com.example.foodorderplatform.enumclass.OrderStatusEnum;
import com.example.foodorderplatform.enumclass.PaymentStatusEnum;
import com.example.foodorderplatform.repository.*;
import com.example.foodorderplatform.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.foodorderplatform.enumclass.OrderStatusEnum.PAYMENT_COMPLETED;
import static com.example.foodorderplatform.message.ErrorMessage.*;
import static com.example.foodorderplatform.message.SuccessMessage.*;
import static java.rmi.server.LogStream.log;

@Transactional
@Slf4j(topic = "OrderService")
@Service
@RequiredArgsConstructor
public class OrderService {

    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final OrderRepository orderRepository;
    private final FoodCartRepository foodCartRepository;

    public ResponseEntity<String> paymentRequest(OrderRequestDto orderRequestDto, User user) {
        try {
            Payment payment = new Payment(user, orderRequestDto.getBank(), orderRequestDto.getOrderPrice());
//            PaymentStatusEnum paymentResult = postCallResultPayment(payment.getId(), orderRequestDto, user);
            PaymentStatusEnum paymentResult = PaymentStatusEnum.SUCCESS;

            OrderStatusEnum orderStatus;
            if (paymentResult.equals(PaymentStatusEnum.SUCCESS)) {
                orderStatus = PAYMENT_COMPLETED;
            }else{
                orderStatus = OrderStatusEnum.ORDER_FAIL;
            }

            Cart cart = cartRepository.findByIdAndDeletedAtIsNull(orderRequestDto.getCartId()).orElse(null);
            Store store = cart.getStore();


            Order order = new Order(store, user, orderStatus, orderRequestDto);
//            Order order = new Order(orderStatus, orderRequestDto);

            paymentRepository.save(payment);
            order.setPayment(payment);
            orderRepository.save(order);


            FoodOrder foodOrder;
            for (FoodCartRequestDto foodCartRequestDto : orderRequestDto.getFoodCarRequestList()) {
                Optional<Food> food = foodRepository.findById(foodCartRequestDto.getFoodId());
                if (food.isPresent()) {
                    foodOrder = new FoodOrder(order, food.get(), foodCartRequestDto.getFoodCount());
                    foodOrderRepository.save(foodOrder);
                }
            }

            if (orderStatus.equals(OrderStatusEnum.ORDER_FAIL)) {
                return new ResponseEntity<>(CREATE_ORDER_FAIL.getMessage(), HttpStatus.OK);
            }

//            LocalDateTime now = LocalDateTime.now();
//            String userName = user.getUserName();
//            List<FoodCart> foodCartList = foodCartRepository.findAllByCart_idAndDeletedAtIsNull(orderRequestDto.getCartId());
//            for (FoodCart foodCart : foodCartList) {
//                foodCart.setFoodCnt(0);
//                foodCart.setDeletedAt(now);
//                foodCart.setDeletedBy(userName);
//            }
//            cart.setDeletedAt(now);
//            cart.setDeletedBy(userName);


            return new ResponseEntity<>(CREATE_ORDER_SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(CREATE_ORDER_ERROR.getMessage(), HttpStatus.OK);
        }

    }

    public ResponseEntity<OrderInfoResponseDto> getOrders(UserDetailsImpl userDetails) {
        List<Order> orderList = orderRepository.findAllByUser_id(userDetails.getUser().getId());
        OrderInfoResponseDto orderInfoResponseDto = new OrderInfoResponseDto(orderList);
        return new ResponseEntity<>(orderInfoResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<OrderDetailResponseDto> getOrderDetails(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        List<FoodOrder> foodOrderList = foodOrderRepository.findAllByOrder_id(orderId);

        OrderDetailResponseDto orderDetailResponseDto = new OrderDetailResponseDto(order, foodOrderList);

        return new ResponseEntity<>(orderDetailResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<String> updateOrder(UUID orderId, OrderUpdateRequestDto orderUpdateRequestDto, UserDetailsImpl userDetails) {
        try{
            String orderRequest = orderUpdateRequestDto.getOrderRequest();
            String address = orderUpdateRequestDto.getAddress();
            Order order = orderRepository.findById(orderId).orElse(null);

            if(!order.getOrderStatus().equals(PAYMENT_COMPLETED)){
                return new ResponseEntity<>(UPDATE_ORDER_FAIL.getMessage(), HttpStatus.OK);
            }

            if ( !orderRequest.isBlank() && !orderRequest.isEmpty() ){

                order.setOrderRequest(orderRequest);
            }

            if ( !address.isBlank() && !address.isEmpty() ){
                userDetails.getUser().getAddress().setAddressName(address);
            }

            return new ResponseEntity<>(UPDATE_ORDER_SUCCESS.getMessage(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(UPDATE_ORDER_ERROR.getMessage(), HttpStatus.OK);
        }

    }

    public ResponseEntity<String> deleteOrder(UUID orderId, UserDetailsImpl userDetails) {
        try{
            Order order = orderRepository.findById(orderId).orElse(null);
            PaymentStatusEnum paymentStatus = deleteCallResultPayment(order.getPayment());

            if(!order.getOrderStatus().equals(PAYMENT_COMPLETED)){
                return new ResponseEntity<>(DELETE_ORDER_FAIL.getMessage(), HttpStatus.OK);
            }

            if (paymentStatus.equals(PaymentStatusEnum.CANCEL_FAIL)) {
                return new ResponseEntity<>(DELETE_ORDER_ERROR.getMessage(), HttpStatus.OK);
            }

            Payment payment = paymentRepository.findById(order.getPayment().getId()).orElse(null);
            List<FoodOrder> foodOrderList = foodOrderRepository.findAllByOrder_id(order.getId());

            LocalDateTime now = LocalDateTime.now();
            order.setDeletedAt(now);
            order.setDeletedBy(userDetails.getUser().getUserName());
            payment.setDeletedAt(now);
            payment.setDeletedBy(userDetails.getUser().getUserName());
            for (FoodOrder foodOrder : foodOrderList) {
                foodOrder.setDeletedAt(now);
                foodOrder.setDeletedBy(userDetails.getUser().getUserName());
            }

            return new ResponseEntity<>(DELETE_ORDER_SUCCESS.getMessage(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(DELETE_ORDER_ERROR.getMessage(), HttpStatus.OK);
        }

    }

    public ResponseEntity<StoreOrderInfoResponseDto> getStoreOrders(UUID storeId, String orderStatus) {
        List<Order> orderList;
        if (orderStatus.isEmpty() || orderStatus.isBlank()) {
            orderList = orderRepository.findAllByStore_id(storeId);
        }else {
            orderList = orderRepository.findAllByStore_idAndOrderStatus(storeId, OrderStatusEnum.valueOf(orderStatus));
        }

        StoreOrderInfoResponseDto storeOrderInfoResponseDto = new StoreOrderInfoResponseDto(orderList);

        return new ResponseEntity<>(storeOrderInfoResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<String> updateOrderStatusByStore(UUID storeId, UUID orderId, OrderUpdateRequestByStoreDto orderUpdateRequest) {

        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            order.setOrderStatus(orderUpdateRequest.getStatus());

            return new ResponseEntity<>(UPDATE_ORDER_SUCCESS.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(UPDATE_ORDER_ERROR.getMessage(), HttpStatus.OK);
        }
    }

    private PaymentStatusEnum postCallResultPayment(UUID paymentId, OrderRequestDto orderRequestDto, User user) {
        RestTemplate restTemplate = new RestTemplate();
        BankEnum bank = orderRequestDto.getBank();
        String bankString;
        if(bank.equals(BankEnum.HANA)){
            bankString = BankEnum.HANA.toString();
        }else  if(bank.equals(BankEnum.SHINHAN)){
            bankString = BankEnum.SHINHAN.toString();
        }else{
            bankString = BankEnum.KB.toString();
        }

        String idString = paymentId.toString();
        String paymentIdEncoding = URLEncoder.encode(idString, StandardCharsets.UTF_8);
        String query = URLEncoder.encode(bankString, StandardCharsets.UTF_8);
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/order/{paymentId}/bank/{bankId}")
                .encode()
                .build()
                .expand(paymentIdEncoding, query)
                .toUri();
        OrderService.log.info("uri = " + uri);

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(
                paymentId,
                user.getUserNickName(),
                user.getUserBirth(),
                user.getUserTel(),
                user.getUserEmail(),
                orderRequestDto.getOrderPrice()
        );

        ResponseEntity<PaymentStatusEnum> responseEntity = restTemplate.postForEntity(uri, paymentRequestDto, PaymentStatusEnum.class);

        OrderService.log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    private PaymentStatusEnum deleteCallResultPayment(Payment payment) {
        RestTemplate restTemplate = new RestTemplate();
        BankEnum bank = payment.getBank();
        UUID paymentId = payment.getId();
        String bankQuery;
        if(bank.equals(BankEnum.HANA)){
            bankQuery = BankEnum.HANA.toString();
        }else  if(bank.equals(BankEnum.SHINHAN)){
            bankQuery = BankEnum.SHINHAN.toString();
        }else{
            bankQuery = BankEnum.KB.toString();
        }

        try {
            String idString = paymentId.toString();
            String paymentIdEncoding = URLEncoder.encode(idString, StandardCharsets.UTF_8);
            String bankEncoding = URLEncoder.encode(bankQuery,StandardCharsets.UTF_8);
            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:8080")
                    .path("/api/order/{paymentId}/bank/{bankId}")
                    .encode()
                    .build()
                    .expand(paymentIdEncoding, bankEncoding)
                    .toUri();
            OrderService.log.info("uri = " + uri);

            restTemplate.delete(uri);

            OrderService.log.info("request delete payment");

            return PaymentStatusEnum.CANCEL;
        } catch (Exception e) {
            return PaymentStatusEnum.CANCEL_FAIL;
        }
    }
}
