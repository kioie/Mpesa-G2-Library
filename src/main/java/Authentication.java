import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by KioiEddy on 12/1/17.
 */
public class Authentication {
    public String authenticate(String consumer_key, String consumer_secret) throws IOException {

        String access_token = consumer_key + ":" + consumer_secret;
        byte[] bytes = access_token.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization", "Basic " + encoded)
                .addHeader("cache-control", "no-cache")

                .build();

        Response response = client.newCall(request).execute();

        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject.getString("access_token");
    }

    public JSONObject connect(String consumer_key, String consumer_secret, String requestJson, String url) throws
            IOException {
        OkHttpClient client = new OkHttpClient();

        String accessToken = authenticate(consumer_key, consumer_secret);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("cache-control", "no-cache")

                .build();

        Response response = client.newCall(request).execute();
        JSONObject B2BRequestResponse = new JSONObject(response.body().string());
        return B2BRequestResponse;
    }
}
