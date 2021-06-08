package au.edu.rmit.storyboard_navigation.work;

import android.util.Log;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;

public class PTVCalculateURLSignature {
    private static final String privateKey = "d45c8eb9-bdcc-4c72-adf3-4b7be3922987";
    private static final int developerId = 3001831;

    public static String buildTTAPIURL(final String baseURL, final String uri) throws Exception {
        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        byte[] keyBytes = privateKey.getBytes();
        String uriWithDeveloperID = uri + (uri.contains("?") ? "&" : "?") +
                "devid=" + developerId;
        byte[] uriBytes = uriWithDeveloperID.getBytes();
        Key signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        byte[] signatureBytes = mac.doFinal(uriBytes);
        StringBuilder signature = new StringBuilder(signatureBytes.length * 2);

        for (byte signatureByte : signatureBytes)
        {
            int intVal = signatureByte & 0xff;
            if (intVal < 0x10)
            {
                signature.append("0");
            }
            signature.append(Integer.toHexString(intVal));
        }

        return baseURL + uri + (uri.contains("?") ? "&" : "?") +
                "devid=" + developerId + "&signature=" + signature.toString().toUpperCase();
    }
}
