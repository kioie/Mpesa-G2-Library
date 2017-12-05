import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by KioiEddy on 12/1/17.
 */
public class B2B {
    public JSONObject Request(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                              String lipaNaMpesaPasskey, String timestamp, String initiatorName, String commandID,
                              String senderIdentifierType,
                              String receiverIdentifierType, String amount, String partyA, String partyB, String
                                      remarks,
                              String queueTimeOutURL, String resultURL) throws IOException {

        /*JSON Request Body
        {"Initiator": "", "SecurityCredential": "", "CommandID": "", "SenderIdentifierType": "",
                "RecieverIdentifierType": "", "Amount": "", "PartyA": "", "PartyB": "", "AccountReference": "",
                "Remarks": "", "QueueTimeOutURL": "", "ResultURL": ""}*/
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        String url = "https://sandbox.safaricom.co.ke/mpesa/b2b/v1/paymentrequest";

        String accountReference = RandomStringUtils.randomAlphabetic(10);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiatorName);
        jsonObject.put("SecurityCredential", encodedPassword);
        jsonObject.put("CommandID", commandID); /* BusinessPayBill or BusinessBuyGoods or DisburseFundsToBusiness or
                                                BusinessToBusinessTransfer or MerchantToMerchantTransfer*/
        jsonObject.put("SenderIdentifierType", senderIdentifierType); /*1 for MSISDN
                                                                        2 for Till Number
                                                                        4 for Organization short code*/
        jsonObject.put("RecieverIdentifierType", receiverIdentifierType);/*1 for MSISDN
                                                                        2 for Till Number
                                                                        4 for Organization short code*/
        jsonObject.put("Amount", amount);
        jsonObject.put("PartyA", lipaNaMpesaShortcode);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("Remarks", remarks); /* Not entirely sure what this is about, but ther API doc says "Comments
        that are sent along with the transaction"String of < 100 characters*/
        jsonObject.put("AccountReference", accountReference); /*mandatory for "BussinessPaybill" CommandID. Should be
         < 15 characters*/
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);

        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);

    }
}
