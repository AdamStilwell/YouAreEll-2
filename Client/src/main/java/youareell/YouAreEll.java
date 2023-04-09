package youareell;

import controllers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;


public class YouAreEll {
    private static String rootURL = "http://zipcode.rocks:8085";

    TransactionController tt;


    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll (MessageController m, IdController i){this.tt = new TransactionController(m,i);}

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
//        YouAreEll urlhandler = new YouAreEll(
//            new TransactionController(
//                new MessageController(), new IdController()
//        ));
//        System.out.println(MakeURLCall("/ids", "GET", ""));
//        System.out.println(MakeURLCall("/messages", "GET", ""));
    }

    public static String MakeURLCall(String url, String command, String json) throws IOException  {
        StringBuilder sb = new StringBuilder();
        sb.append(rootURL)
                .append(url);
        URL obj = new URL(sb.toString());
        sb.setLength(0);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(command);
        if(command.equals("POST")){
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            try(OutputStream os = con.getOutputStream()){
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }else if(command.equals("PUT")){

        }
        int responseCode = con.getResponseCode();
        System.out.println(command + " Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("GET request did not work.");
        }
        return sb.toString();
    }

    public static String get_ids(String url) throws IOException {
        return MakeURLCall(url, "GET", "");
    }

    public static String get_messages(String url) throws IOException {
        return MakeURLCall(url, "GET", "");
    }

    public static String post_ids(String url, String json) throws IOException {
        return MakeURLCall(url, "POST", json);
    }

    public static String post_messages(String url, String json) throws IOException {
        return MakeURLCall(url, "POST", json);
    }

    public static String put_ids(String url, String json) throws IOException {
        return MakeURLCall(url, "PUT", json);
    }
}
