import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by KioiEddy on 12/5/17.
 */
public class C2B {
    public JSONObject Simulation(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                                 String commandID, String amount, String phoneNumber) throws IOException {


        String url = "https://sandbox.safaricom.co.ke/mpesa/c2b/v1/simulate";
        String billRefNumber = RandomStringUtils.randomAlphabetic(16);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode", lipaNaMpesaShortcode);
        jsonObject.put("CommandID", commandID); /*CustomerPayBillOnline or CustomerBuyGoodsOnline*/
        jsonObject.put("Amount", amount);
        jsonObject.put("Msisdn", phoneNumber);
        jsonObject.put("BillRefNumber", billRefNumber);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);
        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);
    }

    public JSONObject URLregistration(String consumer_key, String consumer_secret, String lipaNaMpesaShortcode,
                                      String responseType,
                                      String confirmationURL, String validationURL)
            throws IOException {

        String url = "https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode", lipaNaMpesaShortcode);
        jsonObject.put("ResponseType", "Completed");
        jsonObject.put("ConfirmationURL", confirmationURL);
        jsonObject.put("ValidationURL", validationURL);
        jsonArray.put(jsonObject);


        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);

        Authentication authenticator = new Authentication();

        return authenticator.connect(consumer_key, consumer_secret, requestJson, url);
    }
}
