package com.sadiqov.tech_app_three.service;

import com.sadiqov.tech_app_three.dto.request.AccountToAccountRequestDTO;
import com.sadiqov.tech_app_three.dto.response.*;
import com.sadiqov.tech_app_three.entity.Account;
import com.sadiqov.tech_app_three.entity.TechUser;
import com.sadiqov.tech_app_three.exception.InvalidDTO;
import com.sadiqov.tech_app_three.exception.InvalidToken;
import com.sadiqov.tech_app_three.repository.AccountRepo;
import com.sadiqov.tech_app_three.repository.UserRepository;
import com.sadiqov.tech_app_three.util.CurrentUser;
import com.sadiqov.tech_app_three.util.DTOUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    DTOUtil dtoUtil;

    @Autowired
    CurrentUser currentUser;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public CommonResponse<?> getAccount() {
        Optional<TechUser> user = userRepository.findBYPin(currentUser.getCurrentUser().getUsername());

        return CommonResponse.builder().
                status(Status.builder().
                        statusCode(StatusCode.SUCCESS).
                        message("Account successfully fetched")
                        .build()).data(AccountResponseDtoList.
                        entityDtoAccountList(user.get().getAccountList())).build();

    }

    @Transactional
    public CommonResponse<?> account2account(AccountToAccountRequestDTO accountToAccountRequestDTO) {
        validateUserAndAccounts(accountToAccountRequestDTO);

        dtoUtil.isValid(accountToAccountRequestDTO);

        validateAccountToAccountRequest(accountToAccountRequestDTO);

        Optional<Account> accountByDebit = accountRepo.findByAccountNo(accountToAccountRequestDTO.getDebitAccount());

        Account debitAccount;
        Account creditAccount;

        if (accountByDebit.isPresent()) {
            debitAccount = accountByDebit.get();

            if (!debitAccount.getIsActive()) {
                throw exceptionMessage(StatusCode.NOT_ACTIVE_ACCOUNT, "Debit account is not active.!");

            }
            // Gonderilen meblegin , balansdan az olmamasını yoxxlamaq ucundur.

            if (debitAccount.getBalance().compareTo(accountToAccountRequestDTO.getAmount()) < 0) {
                throw exceptionMessage(StatusCode.INVALID_BALANCE, "Balance is ot enough");

            }
            Optional<Account> accountByCredit = accountRepo.findByAccountNo(accountToAccountRequestDTO.getCreditAccount());

            if (accountByCredit.isPresent()) {
                creditAccount = accountByCredit.get();

                if (!creditAccount.getIsActive()) {
                    throw exceptionMessage(StatusCode.NOT_ACTIVE_ACCOUNT, "Credit account is not active.!");
                }

            } else {
                throw exceptionMessage(StatusCode.NOT_PRESENT_ACCOUNT, "Credit account is not present.!");

            }
        } else {
            throw exceptionMessage(StatusCode.NOT_PRESENT_ACCOUNT, "Debit account is not present.!");

        }

        debitAccount.setBalance(debitAccount.getBalance().subtract(accountToAccountRequestDTO.getAmount()));
        creditAccount.setBalance(creditAccount.getBalance().add(accountToAccountRequestDTO.getAmount()));

        accountRepo.save(debitAccount);
        accountRepo.save(creditAccount);

        return CommonResponse.builder().status(Status.builder().
                        statusCode(StatusCode.SUCCESS).
                        message("Money transfer completed successfully...!").build()).
                data((AccountResponseDto.builder().
                        balance(debitAccount.getBalance())
                        .accountNo(debitAccount.getAccountNo())
                        .currency(debitAccount.getCurrency())
                        .isActive(debitAccount.getIsActive())
                        .build())).build();
    }


    private void validateUserAndAccounts(AccountToAccountRequestDTO accountToAccountRequestDTO) {
        Optional<TechUser> user = userRepository.findBYPin(currentUser.getCurrentUser().getUsername());
        if (user.isEmpty()) {
            throw exceptionMessageToken(StatusCode.INVALID_TOKEN, "The token is not linked to this account. ");
        }
        TechUser techUser = user.get();
        List<Account> accountList = techUser.getAccountList();

        if (accountList.stream().noneMatch(account -> account.getAccountNo().equals(accountToAccountRequestDTO.getDebitAccount()))) {
            ;
            throw exceptionMessageToken(StatusCode.INVALID_TOKEN, "The token is not linked to this account. ");
        }
    }

    private void validateAccountToAccountRequest(AccountToAccountRequestDTO dto) {
        dtoUtil.isValid(dto);

        if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw InvalidDTO.builder()
                    .responseDTO(CommonResponse.builder()
                            .status(Status.builder()
                                    .statusCode(StatusCode.AMOUNT_IS_NOT_CORRECT)
                                    .message("Amount is not correct")
                                    .build())
                            .build())
                    .build();
        }

        if (dto.getCreditAccount().equals(dto.getDebitAccount())) {
            throw InvalidDTO.builder()
                    .responseDTO(CommonResponse.builder()
                            .status(Status.builder()
                                    .statusCode(StatusCode.ACCOUNTS_IS_SAME)
                                    .message("DebitAccount " + dto.getDebitAccount() +
                                            " and Credit amount " + dto.getCreditAccount()
                                            + " are same...!")
                                    .build())
                            .build())
                    .build();
        }
    }

    private InvalidDTO exceptionMessage(StatusCode statusCode, String message) {

        return InvalidDTO.builder().
                responseDTO(CommonResponse.builder().
                        status(Status.builder().statusCode(statusCode).
                                message(message).
                                build()).build()).build();
    }

    private InvalidToken exceptionMessageToken(StatusCode statusCode, String message) {

        return InvalidToken.builder().
                commonResponse(CommonResponse.builder().
                        status(Status.builder().statusCode(statusCode).
                                message(message).
                                build()).build()).build();
    }
}
