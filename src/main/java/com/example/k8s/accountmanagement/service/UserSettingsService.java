package com.example.k8s.accountmanagement.service;

import com.example.k8s.accountmanagement.domain.AccountUpdate;
import com.example.k8s.accountmanagement.exception.InternalServerException;
import com.example.k8s.accountmanagement.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserSettingsService {
    private final static RestTemplate restTemplateClient = new RestTemplate();

    @Value("${api.user-settings.url}")
    private String baseUrl;

    public void updateAccountSettingsOrder(String accountId, AccountUpdate accountUpdate) {

        String settingsUrl = String.format("%s/settings/%s/account-order", baseUrl, accountId);
        try {
            restTemplateClient.put(
                    settingsUrl,
                    accountUpdate);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new AccountNotFoundException();
            } else {
                throw new InternalServerException();
            }
        }
    }
}
