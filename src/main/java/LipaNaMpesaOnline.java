import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * Created by KioiEddy on 12/6/17.
 */
public class LipaNaMpesaOnline {
    public JSONObject queryPaymentStatus(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                                         String lipaNaMpesaPasskey, String timestamp, String checkoutRequestID)
            throws IOException {

        String url = "https://sandbox.safaricom.co.ke/mpesa/stkpushquery/v1/query";
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timestamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", lipaNaMpesaShortcode);
        jsonObject.put("Password", encodedPassword);
        jsonObject.put("Timestamp", timestamp);
        jsonObject.put("CheckoutRequestID", checkoutRequestID);
        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");


        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);

    }

    public JSONObject payment(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                              String lipaNaMpesaPasskey, String amount, String phoneNumber, String callBackURL,
                              String accountNumber, String queueTimeOutURL, String transactionDesc) throws IOException {

        String url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String password = lipaNaMpesaShortcode + lipaNaMpesaPasskey + timeStamp;
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", lipaNaMpesaShortcode);
        jsonObject.put("Password", encodedPassword);
        jsonObject.put("Timestamp", timeStamp);
        jsonObject.put("TransactionType", "CustomerPayBillOnline");
        jsonObject.put("Amount", amount);
        jsonObject.put("PhoneNumber", phoneNumber);
        jsonObject.put("PartyA", phoneNumber);
        jsonObject.put("PartyB", lipaNaMpesaShortcode);
        jsonObject.put("CallBackURL", callBackURL);
        jsonObject.put("AccountReference", accountNumber); //Name of the company/owner of the paybill that will be
        // displayed to the client
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("TransactionDesc", transactionDesc);
        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");

        Authentication authenticator = new Authentication();
        JSONObject response = authenticator.connect(consumer_key, consumer_secret, requestJson, url);
        response.put("Timestamp", timeStamp);

        return response;
    }
}
