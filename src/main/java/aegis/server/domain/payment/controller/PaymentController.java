package aegis.server.domain.payment.controller;

import aegis.server.domain.payment.dto.request.PaymentRequest;
import aegis.server.domain.payment.dto.response.PaymentStatusResponse;
import aegis.server.domain.payment.service.PaymentService;
import aegis.server.global.security.annotation.LoginUser;
import aegis.server.global.security.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> createPendingPayment(
            @RequestBody PaymentRequest request,
            @LoginUser SessionUser user
    ) {
        paymentService.createPendingPayment(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/status")
    public ResponseEntity<PaymentStatusResponse> checkPaymentStatus(@LoginUser SessionUser user) {
        return ResponseEntity.ok(paymentService.checkPaymentStatus(user));
    }
}
