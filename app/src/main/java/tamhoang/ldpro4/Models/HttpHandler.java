package tamhoang.ldpro4.Models;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public String makeServiceCall(String reqUrl) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(reqUrl).openConnection();
            conn.setRequestMethod("GET");
            return convertStreamToString(new BufferedInputStream(conn.getInputStream()));
        } catch (MalformedURLException e) {
            String str = TAG;
            Log.e(str, "MalformedURLException: " + e.getMessage());
            return null;
        } catch (ProtocolException e2) {
            String str2 = TAG;
            Log.e(str2, "ProtocolException: " + e2.getMessage());
            return null;
        } catch (IOException e3) {
            String str3 = TAG;
            Log.e(str3, "IOException: " + e3.getMessage());
            return null;
        } catch (Exception e4) {
            String str4 = TAG;
            Log.e(str4, "Exception: " + e4.getMessage());
            return null;
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = reader.readLine();
                String line = readLine;
                if (readLine != null) {
                    sb.append(line);
                    sb.append(10);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    is.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    is.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
        is.close();
        return sb.toString();
    }
}
