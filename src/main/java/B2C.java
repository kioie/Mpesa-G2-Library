import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by KioiEddy on 12/5/17.
 */
public class B2C {
    public JSONObject Request(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                              String lipaNaMpesaPasskey, String timestamp, String initiatorName,
                              String commandID, String amount, String partyA,
                              String phoneNumber, String comments, String queueTimeOutURL, String resultURL, String occassion)
            throws IOException {

        String url = "https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest";
        //Some hardcoded variables
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("InitiatorName", initiatorName);
        jsonObject.put("SecurityCredential", encodedPassword);
        jsonObject.put("CommandID", commandID); /*SalaryPayment or BusinessPayment or PromotionPayment*/
        jsonObject.put("Amount", amount);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", phoneNumber);
        jsonObject.put("Remarks", comments);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonObject.put("Occassion", occassion);
        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");


        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);
    }
}
