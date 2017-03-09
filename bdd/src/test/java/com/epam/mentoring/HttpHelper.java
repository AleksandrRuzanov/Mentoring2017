package com.epam.mentoring;

import com.epam.mentoring.person.Person;
import gherkin.deps.com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Aleksandr_Ruzanov on 07.03.2017.
 */
public class HttpHelper {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static HttpURLConnection getHttpConnection(String url, String type) {
        URL uri = null;
        HttpURLConnection con = null;
        try {
            uri = new URL(url);
            con = (HttpURLConnection) uri.openConnection();
            con.setRequestMethod(type);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(60000);
            con.setReadTimeout(60000);
            con.setRequestProperty("User-Agent", USER_AGENT);
            //con.setRequestProperty("Accept-Encoding", "application/json");
            //con.setRequestProperty("Content-Type", "UTF-8");
        } catch (Exception e) {
            System.out.println("connection i/o failed");
        }
        return con;
    }

    public static Person sendRequest(String url, String type, String reqbody) {
        HttpURLConnection con = null;
        Person person = null;
        try {
            con = getHttpConnection(url, type);
            if (reqbody != null) {
                con.setDoInput(true);
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(reqbody);
                out.flush();
                out.close();
            }

            int responseCode = con.getResponseCode();
            System.out.println("Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {

                con.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine).append(" ");
                }
                person = new Gson().fromJson(response.toString(), Person.class);

                in.close();
                System.out.println(person);
            } else {
                System.out.println(type + " request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return person;
    }

}

