import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by KioiEddy on 12/5/17.
 */
public class Balance {
    public JSONObject inquiry(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                              String lipaNaMpesaPasskey, String timestamp, String initiatorName,
                              String commandID, String partyA, String identifierCode, String comments,
                              String queueTimeOutURL, String resultURL)
            throws IOException {

        String url = "https://sandbox.safaricom.co.ke/mpesa/accountbalance/v1/query";
        //Some hardcoded variables
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiatorName);
        jsonObject.put("SecurityCredential", encodedPassword);
        jsonObject.put("CommandID", "AccountBalance");
        jsonObject.put("PartyA", partyA);
        jsonObject.put("IdentifierType", identifierCode);
        jsonObject.put("Remarks", comments);/*Comments that are sent along with the transaction. < 100 characters*/
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonArray.put(jsonObject);


        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);

        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);
    }
}
