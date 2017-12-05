import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by KioiEddy on 12/4/17.
 */
public class AccountBalance {
    public JSONObject Inquiry(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                              String lipaNaMpesaPasskey, String timestamp, String initiatorName, String identifierCode,
                              String remarks, String queueTimeOutURL, String resultURL)
            throws IOException {


        //Some hardcoded variables
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        String url = "https://sandbox.safaricom.co.ke/mpesa/accountbalance/v1/query";

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiatorName);
        jsonObject.put("SecurityCredential", encodedPassword);
        jsonObject.put("CommandID", "AccountBalance");
        jsonObject.put("PartyA", lipaNaMpesaShortcode);
        jsonObject.put("IdentifierType", identifierCode);/*1 for MSISDN
                                                          2 for Till Number
                                                          4 for Organization short code*/
        jsonObject.put("Remarks", remarks);/* The API doc says "Comments that are sent along with the transaction
        "String of < 100 characters*/
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);

        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);
    }
}
