package com.donet.donet.global.smartContract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
//import org.web3j.abi.datatypes.CustomError;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class Campaign extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061001961001e565b6100d0565b7ff0c57e16840df040f15088dc2f81fe391c3923bec73e23a9662efc9c229c6a00805468010000000000000000900460ff161561006e5760405163f92ee8a960e01b815260040160405180910390fd5b80546001600160401b03908116146100cd5780546001600160401b0319166001600160401b0390811782556040519081527fc7f505b2f371ae2175ee4913f4499e1f2633a7b5936321eed1cdaeb6115181d29060200160405180910390a15b50565b611370806100df6000396000f3fe60806040526004361061012a5760003560e01c80637f83a4a6116100ab578063e9fee16f1161006f578063e9fee16f146102e7578063eb13554f14610307578063eeca08f014610327578063f0ea4bfc1461033d578063f14faf6f14610353578063ff203bc01461037357600080fd5b80637f83a4a614610285578063b69ef8a81461029a578063b907b846146102af578063c806a067146102c5578063d3eb6f61146102cd57600080fd5b8063451c3d80116100f2578063451c3d80146101f8578063590e1ae3146102185780635c76ca2d1461022f57806363bd1d4a146102505780636845516b1461026557600080fd5b806329dcb0cf1461012f5780632f13b60c1461015857806338af3eed1461017d57806340193883146101b557806342e94c90146101cb575b600080fd5b34801561013b57600080fd5b5061014560025481565b6040519081526020015b60405180910390f35b34801561016457600080fd5b5061016d610388565b604051901515815260200161014f565b34801561018957600080fd5b5060005461019d906001600160a01b031681565b6040516001600160a01b03909116815260200161014f565b3480156101c157600080fd5b5061014560015481565b3480156101d757600080fd5b506101456101e63660046111c3565b60076020526000908152604090205481565b34801561020457600080fd5b5060045461019d906001600160a01b031681565b34801561022457600080fd5b5061022d6103a3565b005b34801561023b57600080fd5b5060045461016d90600160a01b900460ff1681565b34801561025c57600080fd5b5061022d6103f8565b34801561027157600080fd5b5061022d6102803660046111e5565b610679565b34801561029157600080fd5b5061016d61080d565b3480156102a657600080fd5b5061014561085e565b3480156102bb57600080fd5b50610145600a5481565b61022d6108e8565b3480156102d957600080fd5b50600154600354101561016d565b3480156102f357600080fd5b5061022d610302366004611244565b610a74565b34801561031357600080fd5b5060065461019d906001600160a01b031681565b34801561033357600080fd5b5061014560055481565b34801561034957600080fd5b5061014560035481565b34801561035f57600080fd5b5061022d61036e366004611244565b610ba5565b34801561037f57600080fd5b5061016d610d49565b600060025460000361039a5750600090565b50600254421190565b6103ab610d70565b6103b3610da8565b60006103be33610e26565b9050806103de57604051631971fbf360e21b815260040160405180910390fd5b506103f6600160008051602061131b83398151915255565b565b610400610d70565b600454600160a01b900460ff161561042b57604051631897796360e21b815260040160405180910390fd5b6001546003541015610450576040516378c754c960e01b815260040160405180910390fd5b6004805460ff60a01b1916600160a01b1790556005546003546000916127109161047a9190611273565b610484919061128a565b905060008160035461049691906112ac565b6004549091506001600160a01b03166105b857600080546040516001600160a01b039091169083908381818185875af1925050503d80600081146104f6576040519150601f19603f3d011682016040523d82523d6000602084013e6104fb565b606091505b505090508061051d576040516312171d8360e31b815260040160405180910390fd5b60008311801561053757506006546001600160a01b031615155b156105b2576006546040516000916001600160a01b03169085908381818185875af1925050503d8060008114610589576040519150601f19603f3d011682016040523d82523d6000602084013e61058e565b606091505b50509050806105b0576040516312171d8360e31b815260040160405180910390fd5b505b50610612565b6004546000546001600160a01b03918216916105d79183911684610eec565b6000831180156105f157506006546001600160a01b031615155b1561061057600654610610906001600160a01b03838116911685610eec565b505b6000546040805183815260208101859052428183015290516001600160a01b03909216917f807c2c73192f2d30961a7d01ec8dc57115a4cd9f9bd0434331beacb916c6a8cc9181900360600190a250506103f6600160008051602061131b83398151915255565b6000610683610f50565b805490915060ff600160401b820416159067ffffffffffffffff166000811580156106ab5750825b905060008267ffffffffffffffff1660011480156106c85750303b155b9050811580156106d6575080155b156106f45760405163f92ee8a960e01b815260040160405180910390fd5b845467ffffffffffffffff19166001178555831561071e57845460ff60401b1916600160401b1785555b6001600160a01b038b1661074557604051631559b7d760e21b815260040160405180910390fd5b8960000361076657604051639b60eb4d60e01b815260040160405180910390fd5b61076e610f7b565b600080546001600160a01b03808e166001600160a01b03199283161790925560018c905560028b9055600480548b841690831617905560058990556006805492891692909116919091179055831561080057845460ff60401b19168555604051600181527fc7f505b2f371ae2175ee4913f4499e1f2633a7b5936321eed1cdaeb6115181d29060200160405180910390a15b5050505050505050505050565b600454600090600160a01b900460ff16156108285750600090565b6002546000036108385750600090565b60025442116108475750600090565b600154600354106108585750600090565b50600190565b6004546000906001600160a01b031661087657504790565b600480546040516370a0823160e01b815230928101929092526001600160a01b0316906370a0823190602401602060405180830381865afa1580156108bf573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108e391906112bf565b905090565b6108f0610d70565b6004546001600160a01b03161561091a5760405163c1ab6dc160e01b815260040160405180910390fd5b3460000361093b57604051631f2a200560e01b815260040160405180910390fd5b6002541580159061094d575060025442115b1561096b5760405163387b2e5560e11b815260040160405180910390fd5b600454600160a01b900460ff161561099657604051631897796360e21b815260040160405180910390fd5b61099f33610f8b565b33600090815260076020526040812080543492906109be9084906112d8565b9250508190555034600360008282546109d791906112d8565b90915550506040805134815242602082015233917f4928895ba6723e8e27b15f32e4c3054a1b6c7f8c03f133558d6fa42b3928d14c910160405180910390a260015460035410610a5d57600354604080519182524260208301527f85b3ed4e45559c5f41fb220aa4ac86a440dfc741f219089de694242940aaa09c910160405180910390a15b6103f6600160008051602061131b83398151915255565b610a7c610d70565b610a84610da8565b80600003610aa557604051637862e95960e01b815260040160405180910390fd5b600854600a548111610aca5760405163571ee64360e01b815260040160405180910390fd5b60005b81600a54108015610add57508281105b15610b475760006008600a5481548110610af957610af96112eb565b6000918252602082200154600a80546001600160a01b0390921693509091610b2083611301565b9190505550610b2e81610e26565b15610b415781610b3d81611301565b9250505b50610acd565b600a546040805183815260208101929092524282820152517f5178c4962565e20ed5b9c5b08dbf83398b41c5b8d3e11ce05a57f9c3882d8b559181900360600190a15050610ba2600160008051602061131b83398151915255565b50565b610bad610d70565b6004546001600160a01b0316610bd65760405163c1ab6dc160e01b815260040160405180910390fd5b80600003610bf757604051631f2a200560e01b815260040160405180910390fd5b60025415801590610c09575060025442115b15610c275760405163387b2e5560e11b815260040160405180910390fd5b600454600160a01b900460ff1615610c5257604051631897796360e21b815260040160405180910390fd5b6004546001600160a01b0316610c6a81333085611011565b610c7333610f8b565b3360009081526007602052604081208054849290610c929084906112d8565b925050819055508160036000828254610cab91906112d8565b90915550506040805183815242602082015233917f4928895ba6723e8e27b15f32e4c3054a1b6c7f8c03f133558d6fa42b3928d14c910160405180910390a260015460035410610d3157600354604080519182524260208301527f85b3ed4e45559c5f41fb220aa4ac86a440dfc741f219089de694242940aaa09c910160405180910390a15b50610ba2600160008051602061131b83398151915255565b600454600090600160a01b900460ff1615610d645750600090565b50600154600354101590565b60008051602061131b833981519152805460011901610da257604051633ee5aeb560e01b815260040160405180910390fd5b60029055565b600454600160a01b900460ff1615610dd357604051631897796360e21b815260040160405180910390fd5b6002541580610de457506002544211155b15610e025760405163387b2e5560e11b815260040160405180910390fd5b600154600354106103f65760405163465c128f60e11b815260040160405180910390fd5b6001600160a01b038116600090815260076020526040812054808203610e4f5750600092915050565b6001600160a01b038316600090815260076020526040812081905560038054839290610e7c9084906112ac565b90915550610e8c90508382611050565b604080518281524260208201526001600160a01b038516917f2dc8e290002f06fc0085bbca9dfb8b415cf4d1178950c72ff9ee8f4d8878ee66910160405180910390a250600192915050565b600160008051602061131b83398151915255565b6040516001600160a01b03838116602483015260448201839052610f4b91859182169063a9059cbb906064015b604051602081830303815290604052915060e01b6020820180516001600160e01b0383818316178352505050506110eb565b505050565b6000807ff0c57e16840df040f15088dc2f81fe391c3923bec73e23a9662efc9c229c6a005b92915050565b610f83611160565b6103f6611185565b6001600160a01b03811660009081526009602052604090205460ff16610ba2576001600160a01b03166000818152600960205260408120805460ff191660019081179091556008805491820181559091527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30180546001600160a01b0319169091179055565b6040516001600160a01b03848116602483015283811660448301526064820183905261104a9186918216906323b872dd90608401610f19565b50505050565b6004546001600160a01b03166110d4576000826001600160a01b03168260405160006040518083038185875af1925050503d80600081146110ad576040519150601f19603f3d011682016040523d82523d6000602084013e6110b2565b606091505b5050905080610f4b576040516312171d8360e31b815260040160405180910390fd5b6004546001600160a01b0316610f4b818484610eec565b600080602060008451602086016000885af18061110e576040513d6000823e3d81fd5b50506000513d91508115611126578060011415611133565b6001600160a01b0384163b155b1561104a57604051635274afe760e01b81526001600160a01b038516600482015260240160405180910390fd5b61116861118d565b6103f657604051631afcd79f60e31b815260040160405180910390fd5b610ed8611160565b6000611197610f50565b54600160401b900460ff16919050565b80356001600160a01b03811681146111be57600080fd5b919050565b6000602082840312156111d557600080fd5b6111de826111a7565b9392505050565b60008060008060008060c087890312156111fe57600080fd5b611207876111a7565b95506020870135945060408701359350611223606088016111a7565b92506080870135915061123860a088016111a7565b90509295509295509295565b60006020828403121561125657600080fd5b5035919050565b634e487b7160e01b600052601160045260246000fd5b8082028115828204841417610f7557610f7561125d565b6000826112a757634e487b7160e01b600052601260045260246000fd5b500490565b81810381811115610f7557610f7561125d565b6000602082840312156112d157600080fd5b5051919050565b80820180821115610f7557610f7561125d565b634e487b7160e01b600052603260045260246000fd5b6000600182016113135761131361125d565b506001019056fe9b779b17422d0df92223018b32b4d1fa46e071723d6817e2486d003becc55f00a2646970667358221220f19cbd997b24e347044c68e31c3da79e578f18e593bf9141901f306db159efe464736f6c63430008160033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ACCEPTEDTOKEN = "acceptedToken";

    public static final String FUNC_BALANCE = "balance";

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_CANPAYOUT = "canPayout";

    public static final String FUNC_CANREFUND = "canRefund";

    public static final String FUNC_CONTRIBUTIONS = "contributions";

    public static final String FUNC_DEADLINE = "deadline";

    public static final String FUNC_DONATE = "donate";

    public static final String FUNC_DONATENATIVE = "donateNative";

    public static final String FUNC_GOAL = "goal";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_ISEXPIRED = "isExpired";

    public static final String FUNC_ISGOALREACHED = "isGoalReached";

    public static final String FUNC_PAIDOUT = "paidOut";

    public static final String FUNC_PAYOUT = "payout";

    public static final String FUNC_PLATFORMFEERATE = "platformFeeRate";

    public static final String FUNC_PLATFORMFEERECIPIENT = "platformFeeRecipient";

    public static final String FUNC_RAISED = "raised";

    public static final String FUNC_REFUND = "refund";

    public static final String FUNC_REFUNDALL = "refundAll";

    public static final String FUNC_REFUNDCURSOR = "refundCursor";

//    public static final CustomError ALLREFUNDSPROCESSED_ERROR = new CustomError("AllRefundsProcessed",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError ALREADYPAIDOUT_ERROR = new CustomError("AlreadyPaidOut",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError DEADLINEPASSED_ERROR = new CustomError("DeadlinePassed",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError GOALALREADYREACHED_ERROR = new CustomError("GoalAlreadyReached",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError GOALNOTREACHED_ERROR = new CustomError("GoalNotReached",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError INVALIDBATCHSIZE_ERROR = new CustomError("InvalidBatchSize",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError INVALIDBENEFICIARY_ERROR = new CustomError("InvalidBeneficiary",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError INVALIDGOAL_ERROR = new CustomError("InvalidGoal",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError INVALIDINITIALIZATION_ERROR = new CustomError("InvalidInitialization",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError INVALIDTOKEN_ERROR = new CustomError("InvalidToken",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError NOCONTRIBUTION_ERROR = new CustomError("NoContribution",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError NOTINITIALIZING_ERROR = new CustomError("NotInitializing",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError REENTRANCYGUARDREENTRANTCALL_ERROR = new CustomError("ReentrancyGuardReentrantCall",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError SAFEERC20FAILEDOPERATION_ERROR = new CustomError("SafeERC20FailedOperation",
//            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
//    ;
//
//    public static final CustomError TRANSFERFAILED_ERROR = new CustomError("TransferFailed",
//            Arrays.<TypeReference<?>>asList());
//    ;
//
//    public static final CustomError ZEROAMOUNT_ERROR = new CustomError("ZeroAmount",
//            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DONATED_EVENT = new Event("Donated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event GOALREACHED_EVENT = new Event("GoalReached", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event INITIALIZED_EVENT = new Event("Initialized", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
    ;

    public static final Event PAIDOUT_EVENT = new Event("PaidOut", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REFUNDBATCHPROCESSED_EVENT = new Event("RefundBatchProcessed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REFUNDED_EVENT = new Event("Refunded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Campaign(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Campaign(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Campaign(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Campaign(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DonatedEventResponse> getDonatedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DONATED_EVENT, transactionReceipt);
        ArrayList<DonatedEventResponse> responses = new ArrayList<DonatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DonatedEventResponse typedResponse = new DonatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.donor = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DonatedEventResponse getDonatedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DONATED_EVENT, log);
        DonatedEventResponse typedResponse = new DonatedEventResponse();
        typedResponse.log = log;
        typedResponse.donor = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DonatedEventResponse> donatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDonatedEventFromLog(log));
    }

    public Flowable<DonatedEventResponse> donatedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DONATED_EVENT));
        return donatedEventFlowable(filter);
    }

    public static List<GoalReachedEventResponse> getGoalReachedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(GOALREACHED_EVENT, transactionReceipt);
        ArrayList<GoalReachedEventResponse> responses = new ArrayList<GoalReachedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            GoalReachedEventResponse typedResponse = new GoalReachedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.total = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static GoalReachedEventResponse getGoalReachedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(GOALREACHED_EVENT, log);
        GoalReachedEventResponse typedResponse = new GoalReachedEventResponse();
        typedResponse.log = log;
        typedResponse.total = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<GoalReachedEventResponse> goalReachedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getGoalReachedEventFromLog(log));
    }

    public Flowable<GoalReachedEventResponse> goalReachedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GOALREACHED_EVENT));
        return goalReachedEventFlowable(filter);
    }

    public static List<InitializedEventResponse> getInitializedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INITIALIZED_EVENT, transactionReceipt);
        ArrayList<InitializedEventResponse> responses = new ArrayList<InitializedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            InitializedEventResponse typedResponse = new InitializedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.version = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static InitializedEventResponse getInitializedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(INITIALIZED_EVENT, log);
        InitializedEventResponse typedResponse = new InitializedEventResponse();
        typedResponse.log = log;
        typedResponse.version = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<InitializedEventResponse> initializedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getInitializedEventFromLog(log));
    }

    public Flowable<InitializedEventResponse> initializedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INITIALIZED_EVENT));
        return initializedEventFlowable(filter);
    }

    public static List<PaidOutEventResponse> getPaidOutEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAIDOUT_EVENT, transactionReceipt);
        ArrayList<PaidOutEventResponse> responses = new ArrayList<PaidOutEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PaidOutEventResponse typedResponse = new PaidOutEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.beneficiary = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.platformFee = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaidOutEventResponse getPaidOutEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAIDOUT_EVENT, log);
        PaidOutEventResponse typedResponse = new PaidOutEventResponse();
        typedResponse.log = log;
        typedResponse.beneficiary = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.platformFee = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<PaidOutEventResponse> paidOutEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaidOutEventFromLog(log));
    }

    public Flowable<PaidOutEventResponse> paidOutEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAIDOUT_EVENT));
        return paidOutEventFlowable(filter);
    }

    public static List<RefundBatchProcessedEventResponse> getRefundBatchProcessedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REFUNDBATCHPROCESSED_EVENT, transactionReceipt);
        ArrayList<RefundBatchProcessedEventResponse> responses = new ArrayList<RefundBatchProcessedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RefundBatchProcessedEventResponse typedResponse = new RefundBatchProcessedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.processedCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.nextCursor = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RefundBatchProcessedEventResponse getRefundBatchProcessedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REFUNDBATCHPROCESSED_EVENT, log);
        RefundBatchProcessedEventResponse typedResponse = new RefundBatchProcessedEventResponse();
        typedResponse.log = log;
        typedResponse.processedCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.nextCursor = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RefundBatchProcessedEventResponse> refundBatchProcessedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRefundBatchProcessedEventFromLog(log));
    }

    public Flowable<RefundBatchProcessedEventResponse> refundBatchProcessedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUNDBATCHPROCESSED_EVENT));
        return refundBatchProcessedEventFlowable(filter);
    }

    public static List<RefundedEventResponse> getRefundedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REFUNDED_EVENT, transactionReceipt);
        ArrayList<RefundedEventResponse> responses = new ArrayList<RefundedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RefundedEventResponse typedResponse = new RefundedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.donor = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RefundedEventResponse getRefundedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REFUNDED_EVENT, log);
        RefundedEventResponse typedResponse = new RefundedEventResponse();
        typedResponse.log = log;
        typedResponse.donor = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<RefundedEventResponse> refundedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRefundedEventFromLog(log));
    }

    public Flowable<RefundedEventResponse> refundedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUNDED_EVENT));
        return refundedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> acceptedToken() {
        final Function function = new Function(FUNC_ACCEPTEDTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> balance() {
        final Function function = new Function(FUNC_BALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> canPayout() {
        final Function function = new Function(FUNC_CANPAYOUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> canRefund() {
        final Function function = new Function(FUNC_CANREFUND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> contributions(String param0) {
        final Function function = new Function(FUNC_CONTRIBUTIONS, 
                Arrays.<Type>asList(new Address(160, param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> deadline() {
        final Function function = new Function(FUNC_DEADLINE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> donate(BigInteger amount) {
        final Function function = new Function(
                FUNC_DONATE, 
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> donateNative(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DONATENATIVE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> goal() {
        final Function function = new Function(FUNC_GOAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String _beneficiary, BigInteger _goal,
            BigInteger _deadline, String _acceptedToken, BigInteger _platformFeeRate,
            String _platformFeeRecipient) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new Address(160, _beneficiary),
                new Uint256(_goal),
                new Uint256(_deadline),
                new Address(160, _acceptedToken),
                new Uint256(_platformFeeRate),
                new Address(160, _platformFeeRecipient)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isExpired() {
        final Function function = new Function(FUNC_ISEXPIRED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isGoalReached() {
        final Function function = new Function(FUNC_ISGOALREACHED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> paidOut() {
        final Function function = new Function(FUNC_PAIDOUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> payout() {
        final Function function = new Function(
                FUNC_PAYOUT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> platformFeeRate() {
        final Function function = new Function(FUNC_PLATFORMFEERATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> platformFeeRecipient() {
        final Function function = new Function(FUNC_PLATFORMFEERECIPIENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> raised() {
        final Function function = new Function(FUNC_RAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> refund() {
        final Function function = new Function(
                FUNC_REFUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> refundAll(BigInteger batchSize) {
        final Function function = new Function(
                FUNC_REFUNDALL, 
                Arrays.<Type>asList(new Uint256(batchSize)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> refundCursor() {
        final Function function = new Function(FUNC_REFUNDCURSOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Campaign load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new Campaign(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Campaign load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Campaign(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Campaign load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new Campaign(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Campaign load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Campaign(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Campaign> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Campaign.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<Campaign> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Campaign.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Campaign> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Campaign.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Campaign> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Campaign.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class DonatedEventResponse extends BaseEventResponse {
        public String donor;

        public BigInteger amount;

        public BigInteger timestamp;
    }

    public static class GoalReachedEventResponse extends BaseEventResponse {
        public BigInteger total;

        public BigInteger timestamp;
    }

    public static class InitializedEventResponse extends BaseEventResponse {
        public BigInteger version;
    }

    public static class PaidOutEventResponse extends BaseEventResponse {
        public String beneficiary;

        public BigInteger amount;

        public BigInteger platformFee;

        public BigInteger timestamp;
    }

    public static class RefundBatchProcessedEventResponse extends BaseEventResponse {
        public BigInteger processedCount;

        public BigInteger nextCursor;

        public BigInteger timestamp;
    }

    public static class RefundedEventResponse extends BaseEventResponse {
        public String donor;

        public BigInteger amount;

        public BigInteger timestamp;
    }
}
