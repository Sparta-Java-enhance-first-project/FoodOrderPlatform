package com.example.foodorderplatform.controller;

import com.example.foodorderplatform.dto.*;
import com.example.foodorderplatform.entity.Payment;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.enumclass.BankEnum;
import com.example.foodorderplatform.enumclass.PaymentStatusEnum;
import com.example.foodorderplatform.security.UserDetailsImpl;
import com.example.foodorderplatform.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

@Slf4j(topic = "Order Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> payRequest(@RequestBody @Valid OrderRequestDto orderRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.paymentRequest(orderRequestDto, userDetails.getUser());
    }

    @GetMapping("/order")
    public ResponseEntity<OrderInfoResponseDto> getOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrders(userDetails);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDetailResponseDto> getOrderDetail(@PathVariable UUID orderId){
        return orderService.getOrderDetails(orderId);
    }

    @PatchMapping("/order/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable UUID orderId, @RequestBody OrderUpdateRequestDto orderUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return orderService.updateOrder(orderId, orderUpdateRequestDto, userDetails);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return orderService.deleteOrder(orderId, userDetails);
    }

    @GetMapping("/store/{storeId}/order")
//    public ResponseEntity<StoreOrderInfoResponseDto> getStoreOrders(@PathVariable UUID storeId, @RequestParam(required = false) String orderStatus) {
    public ResponseEntity<OrderInfoResponseDto> getStoreOrders(@PathVariable UUID storeId, @RequestParam(required = false) String orderStatus) {
        return orderService.getStoreOrders(storeId, orderStatus);
    }

    @PatchMapping("/store/{storeId}/order/{orderId}")
    public ResponseEntity<String> updateOrderSatusByStore(@PathVariable UUID storeId, @PathVariable UUID orderId, @RequestBody OrderUpdateRequestByStoreDto orderUpdateRequest){
        return orderService.updateOrderStatusByStore(storeId, orderId, orderUpdateRequest);
    }

    @DeleteMapping("/store/{storeId}/order/{orderId}")
    public ResponseEntity<String> deleteOrderByStore(@PathVariable UUID storeId, @PathVariable UUID orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.deleteOrder(orderId, userDetails);
    }


    @PostMapping("/order/{paymentId}/bank/{bankId}")
    public ResponseEntity<PaymentStatusEnum> makePaymentResult(@PathVariable String paymentId,@PathVariable String bankId, @RequestBody PaymentRequestDto paymentRequestDto)  {
        try {
            UUID paymentUUID = UUID.fromString(URLDecoder.decode(paymentId,"UTF-8"));
            BankEnum decoder = BankEnum.valueOf(URLDecoder.decode(bankId, "UTF-8"));
            PaymentStatusEnum paymentResult = PaymentStatusEnum.SUCCESS;
            return new ResponseEntity<>(paymentResult, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            return new ResponseEntity<>(PaymentStatusEnum.FAIL, HttpStatus.OK);
        }
    }

    @DeleteMapping("/order/{paymentId}/bank/{bankId}")
    public void deletePaymentResult(@PathVariable String paymentId, @PathVariable String bankId, @RequestBody PaymentRequestDto paymentRequestDto){


    }
}
