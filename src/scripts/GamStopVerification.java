package scripts;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GamStopVerification {
    public static String getParamsString(HashMap<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
    public static void main(String[] args) {

        //String inputCSVFile = "C:\\Users\\rpesaladenne\\Desktop\\Rajitha\\IdeaProjects\\MyProject\\src\\scripts\\gamstop_data.csv";
        String inputCSVFile = "C:\\Users\\rpesaladenne\\Desktop\\Rajitha\\IdeaProjects\\MyProject\\src\\scripts\\sample.csv";
        String outputCSVFile = "C:\\Users\\rpesaladenne\\Desktop\\Rajitha\\IdeaProjects\\MyProject\\src\\scripts\\gamstop_results.csv";
        String line = "";
        String cvsSplitBy = ",";
        String url = "https://api.gambusters.co.uk";
        String XAPIKey = "bb56751eca733b82";

        try {
            URL urlObj = new URL(url);
            BufferedReader br = new BufferedReader(new FileReader(inputCSVFile));
            FileWriter fileWriter = new FileWriter(outputCSVFile);

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                System.out.println(Arrays.toString(row));

                if (Integer.parseInt(row[0])%10 == 0) {
                    fileWriter.append(Arrays.toString(row)).append("\n");
                }

                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("POST");

                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("firstName", row[0]);
                parameters.put("lastName", row[1]);
                parameters.put("dateOfBirth", row[2]);
                parameters.put("email", row[3]);
                parameters.put("postcode", row[4]);

                char correct_response = row[5].charAt(0);
                String xTraceId = (System.currentTimeMillis()/1000L) + "";

                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("X-API-Key", "bb56751eca733b82");
                connection.setRequestProperty("X-Trace-Id", xTraceId);

                connection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(getParamsString(parameters));
                out.flush();
                out.close();

                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                if (connection.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    System.out.println (content);
                }
                connection.disconnect();
            }
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
