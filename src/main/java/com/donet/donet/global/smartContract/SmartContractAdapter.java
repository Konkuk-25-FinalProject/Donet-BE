package com.donet.donet.global.smartContract;

import com.donet.donet.donation.application.port.out.SmartContractPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SmartContractAdapter implements SmartContractPort {

    private final CampaignFactory campaignFactory;   // 컨트랙트 주소 조회용
    private final Web3j web3j;                       // Web3 네트워크 연동
    private final Credentials credentials;           // 서버 지갑
    private final ContractGasProvider gasProvider;           // 가스 설정

    @Override
    public boolean refundDonations(List<Long> donationIds) {

        try {
            for (Long donationId : donationIds) {

                // 1. Factory에서 donationId로 캠페인 주소 조회
                String campaignAddress = String.valueOf(campaignFactory.getCampaignAddress(BigInteger.valueOf(donationId)));

                if (campaignAddress == null) {
                    throw new IllegalStateException("Campaign address not found for donationId=" + donationId);
                }

                // 2. 해당 주소로 Campaign 컨트랙트 인스턴스 로딩
                Campaign campaign = Campaign.load(
                        campaignAddress,
                        web3j,
                        credentials,
                        gasProvider
                );

                // 3. Donation ID 기반 환불 트랜잭션 실행
                TransactionReceipt tx = campaign.refund().send();

                // 4. 실패 여부 확인
                if (!tx.isStatusOK()) {
                    throw new RuntimeException("Refund transaction failed: " + tx.getTransactionHash());
                }

                System.out.println("Refund success for donationId=" + donationId +
                        " / tx=" + tx.getTransactionHash());
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
