package com.example.shahbazahmed.loginmvvmdatabinding.api;

/**
 * Created by shahbazahmed on 15/08/17.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ElasticEmailClient {

    public static String send(String userName, String apiKey, String from, String fromName, String subject, String body, String to, String isTransactional) {
        String result = null;
        try {
            String encoding = "UTF-8";
            String data = "apikey=" + URLEncoder.encode(apiKey, encoding);
            data += "&from=" + URLEncoder.encode(from, encoding);
            data += "&fromName=" + URLEncoder.encode(fromName, encoding);
            data += "&subject=" + URLEncoder.encode(subject, encoding);
            data += "&bodyHtml=" + URLEncoder.encode(body, encoding);
            data += "&to=" + URLEncoder.encode(to, encoding);
            data += "&isTransactional=" + URLEncoder.encode(isTransactional, encoding);

            URL url = new URL("https://api.elasticemail.com/v2/email/send");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = rd.readLine();
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}