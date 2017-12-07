import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KioiEddy on 12/3/17.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String Consumer_Key = "fpikIlQJc7E30cgVNjQTfKaphpG6l0VD";
        String Consumer_Secret = "saMGtjeutSCYwQ3U";

        String Shortcode_1 = "600263";
        String Shortcode_2 = "600000";
        String Initiator_Name = "testapi0263";
        String Security_Credential = "Safaricom263!";
        String Test_MSISDN = "254708374149";
        String ExpiryDate = "2017-11-16T16:25:26+03:00";
        String LipaNaMpesaOnlineShortcode = "174379";
        String LipaNaMpesaOnlinePasskey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";


        /*B2B b2B = new B2B();
        System.out.println(b2B.Request(Consumer_Key,Consumer_Secret,LipaNaMpesaOnlineShortcode,
                LipaNaMpesaOnlinePasskey,timeStamp,Initiator_Name,"BusinessPayBill","4",
                "4","1",LipaNaMpesaOnlineShortcode,Shortcode_1,"test","https://google.com/api",
                "https://google.com/api"));
        AccountBalance acB = new AccountBalance();
        System.out.println(acB.Inquiry(Consumer_Key, Consumer_Secret, LipaNaMpesaOnlineShortcode,
                LipaNaMpesaOnlinePasskey, timeStamp, Initiator_Name, "4", "test", "https://google.com",
                "https://google.com/api"));*/

        LipaNaMpesaOnline lipa = new LipaNaMpesaOnline();
        System.out.println(lipa.payment(Consumer_Key, Consumer_Secret, LipaNaMpesaOnlineShortcode, LipaNaMpesaOnlinePasskey, "1",
                "254724478905", "https://google.com/api", "https://google.com/api/test", "test"));
    }

}
