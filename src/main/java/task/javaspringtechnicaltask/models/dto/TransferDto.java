package task.javaspringtechnicaltask.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferDto {

    private AccountDto personFrom;

    private AccountDto personTo;

    private BigDecimal amount;
}
