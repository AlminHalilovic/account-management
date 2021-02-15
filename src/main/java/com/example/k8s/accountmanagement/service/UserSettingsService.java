package com.example.k8s.accountmanagement.service;

import javax.annotation.PostConstruct;

import com.example.k8s.accountmanagement.domain.AccountUpdate;
import com.example.k8s.accountmanagement.exception.InternalServerException;
import com.example.k8s.accountmanagement.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserSettingsService {
    private static final Integer READ_TIMEOUT_MILLIS = 5000;
    private static final Integer CONNECT_TIMEOUT_MILLIS = 5000;

    private static RestTemplate restTemplateClient;


    @Value("${api.user-settings.url}")
    private String baseUrl;

    public void patchAccountSettings(String accountId, AccountUpdate accountUpdate) {

        String settingsUrl = String.format("%s/settings/%s/account-settings", baseUrl, accountId);
        try {
            restTemplateClient.patchForObject(
                    settingsUrl,
                    accountUpdate, Void.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new AccountNotFoundException();
            } else {
                throw new InternalServerException();
            }
        }
    }

    @PostConstruct
    public void initializeRestTemplate() {
        restTemplateClient = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
        requestFactory.setReadTimeout(READ_TIMEOUT_MILLIS);
        restTemplateClient.setRequestFactory(requestFactory);
    }
}
