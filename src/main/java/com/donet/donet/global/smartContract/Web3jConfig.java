package com.donet.donet.global.smartContract;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Configuration
@RequiredArgsConstructor
public class Web3jConfig {

    @Value("${web3.rpcUrl}")
    private String rpcUrl;

    @Value("${web3.privateKey}")
    private String privateKey;

    @Value("${web3.gasPrice}")
    private BigInteger gasPrice;

    @Value("${web3.gasLimit}")
    private BigInteger gasLimit;

    // 이미 배포되어 있는 CampaignFactory의 on-chain 주소
    @Value("${contracts.campaignFactoryAddress}")
    private String campaignFactoryAddress;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(rpcUrl));
    }

    @Bean
    public Credentials credentials() {
        return Credentials.create(privateKey);
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return new StaticGasProvider(gasPrice, gasLimit);
    }

    @Bean
    public CampaignFactory campaignFactory(
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider
    ) {
        return CampaignFactory.load(campaignFactoryAddress, web3j, credentials, gasProvider);
    }
}