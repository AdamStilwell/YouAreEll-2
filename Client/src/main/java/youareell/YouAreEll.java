package youareell;

import controllers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpRequest;


public class YouAreEll {
    private String rootURL = "http://zipcode.rocks:8085";

    TransactionController tt;


    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll (MessageController m, IdController i){this.tt = new TransactionController(m,i);}

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    private String MakeURLCall(String s, String command, String s1) throws IOException  {
        StringBuilder sb = new StringBuilder();
        sb.append(rootURL)
                .append(s);
        URL obj = new URL(sb.toString());
        sb.setLength(0);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(command);
        //con.setRequestProperty("User-Agent", USER_AGENT);
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

    public String get_ids() throws IOException {
        return MakeURLCall("/ids", "GET", "");
    }

    public String get_messages() throws IOException {
        return MakeURLCall("/messages", "GET", "");
    }

    public String post_ids() throws IOException {
        return MakeURLCall("/ids", "POST", "");
    }

    public String post_messages() throws IOException {
        return MakeURLCall("/messages", "POST", "");
    }

    public String put_ids() throws IOException {
        return MakeURLCall("/ids", "PUT", "");
    }

    public String put_messages() throws IOException {
        return MakeURLCall("/messages", "PUT", "");
    }
}
