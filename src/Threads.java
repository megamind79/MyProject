import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Threads {
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) throws Exception {
        String url = "https://www.google.com";
        URL url_object = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url_object.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        Scanner in = new Scanner(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.nextLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }
}
