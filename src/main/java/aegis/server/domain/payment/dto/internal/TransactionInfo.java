package aegis.server.domain.payment.dto.internal;

import aegis.server.domain.payment.domain.Transaction;
import aegis.server.domain.payment.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionInfo(
        Long id,
        LocalDateTime transactionTime,
        String depositorName,
        TransactionType transactionType,
        BigDecimal amount,
        BigDecimal balance
) {
    public static TransactionInfo from(Transaction transaction) {
        return new TransactionInfo(
                transaction.getId(),
                transaction.getTransactionTime(),
                transaction.getDepositorName(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getBalance()
        );
    }
}
