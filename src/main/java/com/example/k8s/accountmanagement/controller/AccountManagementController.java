package com.example.k8s.accountmanagement.controller;

import com.example.k8s.accountmanagement.domain.AccountUpdate;
import com.example.k8s.accountmanagement.service.UserSettingsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountManagementController {

    private Logger log = LogManager.getLogger(this.getClass());


    @Autowired
    private UserSettingsService userSettingsService;

    @PutMapping("/accounts/{accountId}/account-order")
    public ResponseEntity<Void> updateAccountOrder(@PathVariable("accountId") String accountId,
                                                   @RequestBody AccountUpdate accountUpdate) {

        userSettingsService.updateAccountSettingsOrder(accountId, accountUpdate);
        return ResponseEntity.noContent().build();
    }
}
