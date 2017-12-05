import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by KioiEddy on 12/5/17.
 */
public class Reversal {
    public JSONObject request(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                              String lipaNaMpesaPasskey, String timestamp, String initiatorName,
                              String commandID, String transactionID, String amount, String sender,
                              String recieverIdentifierCode, String resultURL, String queueTimeOutURL, String remarks,
                              String ocassion) throws IOException {

        String url = "https://sandbox.safaricom.co.ke/mpesa/request/v1/request";
        //Some hardcoded variables
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiatorName);
        jsonObject.put("SecurityCredential", encodedPassword);
        jsonObject.put("CommandID", "TransactionReversal");
        jsonObject.put("TransactionID", transactionID);
        jsonObject.put("Amount", amount);
        jsonObject.put("ReceiverParty", sender);
        jsonObject.put("RecieverIdentifierType", recieverIdentifierCode);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("Occasion", ocassion);


        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);

        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);
    }
}
