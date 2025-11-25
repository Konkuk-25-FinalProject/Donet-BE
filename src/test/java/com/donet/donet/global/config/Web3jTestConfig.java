package com.donet.donet.global.config;

import com.donet.donet.global.smartContract.CampaignFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

import static org.mockito.Mockito.mock;

@Profile("test")
@Configuration
public class Web3jTestConfig {
    @Bean
    public Web3j web3j() {
        return mock(Web3j.class);
    }

    @Bean
    public Credentials credentials() {
        return mock(Credentials.class);
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return mock(ContractGasProvider.class);
    }

    @Bean
    public CampaignFactory campaignFactory(
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider
    ) {
        return mock(CampaignFactory.class);
    }
}