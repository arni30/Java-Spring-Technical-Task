package task.javaspringtechnicaltask.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositWithdrawAccountDto extends AccountDto {

    private BigDecimal amount;

}
