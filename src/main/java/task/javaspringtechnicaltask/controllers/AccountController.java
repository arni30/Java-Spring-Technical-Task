package task.javaspringtechnicaltask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.javaspringtechnicaltask.models.dto.AccountDto;
import task.javaspringtechnicaltask.models.dto.CreateAccountDto;
import task.javaspringtechnicaltask.models.dto.DepositWithdrawAccountDto;
import task.javaspringtechnicaltask.models.dto.TransferDto;
import task.javaspringtechnicaltask.services.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(@RequestParam String name, @RequestParam String surname) {
        try {
            return ResponseEntity.ok().body(accountService.getAccountBalance(name, surname).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        try {
            return ResponseEntity.ok().body(accountService.createAccount(createAccountDto).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositToAccount(@RequestBody DepositWithdrawAccountDto depositAccountDto) {
        try {
            return ResponseEntity.ok().body(accountService.depositToAccount(depositAccountDto).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdrawFromAccount(@RequestBody DepositWithdrawAccountDto withdrawAccountDto) {
        try {
            return ResponseEntity.ok().body(accountService.withdrawFromAccount(withdrawAccountDto).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transferToAccount(@RequestBody TransferDto transferDto) {
        try {
            return ResponseEntity.ok().body(accountService.transferToAccount(transferDto).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
