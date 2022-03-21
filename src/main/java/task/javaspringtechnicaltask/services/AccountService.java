package task.javaspringtechnicaltask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.javaspringtechnicaltask.models.dao.Account;
import task.javaspringtechnicaltask.models.dto.CreateAccountDto;
import task.javaspringtechnicaltask.models.dto.DepositWithdrawAccountDto;
import task.javaspringtechnicaltask.models.dto.AccountDto;
import task.javaspringtechnicaltask.models.dto.TransferDto;
import task.javaspringtechnicaltask.repositories.AccountRepository;
import task.javaspringtechnicaltask.utils.Validator;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public JSONObject createAccount(CreateAccountDto accountDto) throws Exception {
        Account account = null;
        Validator.validateNameAndSurname(accountDto.getName(), accountDto.getSurname());
        if (accountRepository.existsAccountByNameAndSurname(accountDto.getName(), accountDto.getSurname())) {
            account = new Account(accountDto.getName(), accountDto.getSurname(), new BigDecimal(0));
            accountRepository.save(account);
        }
        return createSuccessResponse(account, "account successfully created");
    }

    public JSONObject depositToAccount(DepositWithdrawAccountDto depositAccountDto) throws Exception {
        Validator.validateNameAndSurname(depositAccountDto.getName(), depositAccountDto.getSurname());
        Validator.validateAmount(depositAccountDto.getAmount());
        Account account = accountRepository
                .findAccountByNameAndSurname(depositAccountDto.getName(), depositAccountDto.getSurname());
        account.setBalance(account.getBalance().add(depositAccountDto.getAmount()));
        accountRepository.save(account);
        return createSuccessResponse(account, "deposit successfully completed");
    }

    public JSONObject withdrawFromAccount(DepositWithdrawAccountDto depositAccountDto) throws Exception {
        Validator.validateNameAndSurname(depositAccountDto.getName(), depositAccountDto.getSurname());
        Validator.validateAmount(depositAccountDto.getAmount());
        Account account = accountRepository
                .findAccountByNameAndSurname(depositAccountDto.getName(), depositAccountDto.getSurname());
        Validator.validateAmountToWithdrawOrTransfer(account, depositAccountDto.getAmount());
        account.setBalance(account.getBalance().subtract(depositAccountDto.getAmount()));
        accountRepository.save(account);
        return createSuccessResponse(account, "withdraw successfully completed");
    }

    public JSONObject transferToAccount(TransferDto transferDto) throws Exception {
        Validator.validateAmount(transferDto.getAmount());
        Validator.validateNameAndSurname(transferDto.getPersonTo().getName(),
                transferDto.getPersonTo().getSurname());
        Validator.validateNameAndSurname(transferDto.getPersonFrom().getName(),
                transferDto.getPersonFrom().getSurname());
        Account accountTo = accountRepository.findAccountByNameAndSurname(transferDto.getPersonTo().getName(),
                transferDto.getPersonTo().getSurname());
        Account accountFrom = accountRepository.findAccountByNameAndSurname(transferDto.getPersonFrom().getName(),
                transferDto.getPersonFrom().getSurname());
        Validator.validateAmountToWithdrawOrTransfer(accountFrom, transferDto.getAmount());
        accountFrom.setBalance(accountFrom.getBalance().subtract(transferDto.getAmount()));
        accountRepository.save(accountFrom);
        accountTo.setBalance(accountTo.getBalance().add(transferDto.getAmount()));
        accountRepository.save(accountTo);
        JSONObject response = new JSONObject();
        response.put("receiver", createSuccessResponse(accountTo, "transaction received"));
        response.put("sender", createSuccessResponse(accountFrom, "transaction completed"));
        return response;
    }

    public JSONObject getAccountBalance(String name, String surname) throws Exception {
        Validator.validateNameAndSurname(name,
                surname);
        return createSuccessResponse(accountRepository.findAccountByNameAndSurname(name,
                surname), "Account balance");
    }

    private JSONObject createSuccessResponse(Account account, String message) throws JsonProcessingException {
        JSONObject response = new JSONObject();
        response.put("message", message);
        response.put("balance", account.getBalance());
        response.put("surname", account.getSurname());
        response.put("name", account.getName());
        return response;
    }


}
