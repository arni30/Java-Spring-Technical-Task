package task.javaspringtechnicaltask.utils;

import task.javaspringtechnicaltask.models.dao.Account;

import java.math.BigDecimal;

public class Validator {
    public static void validateNameAndSurname(String name, String surname) throws Exception {
        if (name == null || name.length() == 0 || surname == null || surname.length() == 0)
            throw new Exception("Account name and surname must be filled");
    }

    public static void validateAmountToWithdrawOrTransfer(Account account, BigDecimal amountToWithdrawOrTransfer)
            throws Exception {
        if (account.getBalance().compareTo(amountToWithdrawOrTransfer) < 0)
            throw new Exception("You have less money than you want to withdraw");
    }

    public static void validateAmount(BigDecimal amount) throws Exception {
        System.out.println(amount);
        if (amount == null || amount.compareTo(new BigDecimal(0)) <= 0)
            throw new Exception("amount should be more than 0");
    }
}
