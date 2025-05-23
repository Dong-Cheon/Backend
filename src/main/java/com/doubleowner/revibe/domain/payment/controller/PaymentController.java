package com.doubleowner.revibe.domain.payment.controller;

import com.doubleowner.revibe.domain.payment.dto.CardPaymentRequestDto;
import com.doubleowner.revibe.domain.payment.dto.PaymentResponseDto;
import com.doubleowner.revibe.domain.payment.service.PaymentService;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import com.doubleowner.revibe.global.config.auth.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "결제 관련 API")
public class PaymentController {
    private final PaymentService paymentService;
    @Value("${toss.widget-key}")
    private String WIDGET_SECRET_KEY;
    @Value("${toss.secret-key}")
    private String API_SECRET_KEY;

    @PostMapping("api/v1/payments")
    public ResponseEntity<CommonResponseBody<PaymentResponseDto>> requestCardPayment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CardPaymentRequestDto cardPaymentRequestDto) {
        PaymentResponseDto paymentResponseDto = paymentService.payCard(userDetails.getUser(), cardPaymentRequestDto);
        return new ResponseEntity<>(new CommonResponseBody<>("주문이 완료 되었습니다.", paymentResponseDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/v1/payments/confirm", "/confirm/payment"})
    public ResponseEntity<CommonResponseBody<PaymentResponseDto>> confirmPayment(HttpServletRequest request, @RequestParam String paymentType, @RequestParam String orderId, @RequestParam String paymentKey, @RequestParam String amount) {
        String secretKey = request.getRequestURI().contains("/confirm/payment") ? API_SECRET_KEY : WIDGET_SECRET_KEY;
        PaymentResponseDto paytoss = paymentService.payToss(secretKey, paymentType, orderId, paymentKey, amount);

        return new ResponseEntity<>(new CommonResponseBody<>("주문이 완료 되었습니다.", paytoss), HttpStatus.CREATED);
    }

    @GetMapping("payments")
    public ResponseEntity<CommonResponseBody<List<PaymentResponseDto>>> getPaymentHistory(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                                          @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        List<PaymentResponseDto> history = paymentService.getHistory(userDetails.getUser(), page, size);
        return new ResponseEntity<>(new CommonResponseBody<>("결제 내역 조회.", history), HttpStatus.CREATED);
    }


}