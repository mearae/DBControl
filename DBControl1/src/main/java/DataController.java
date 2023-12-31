import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataController {
    private String urlName  = null;
    private URL url = null;
    HttpURLConnection connection = null;

    public DataController() {
        urlName = "https://script.google.com/macros/s/AKfycbyWIqG1Mt1QTgCw1ihed7UAa2efXqqmr2YgFFtRaalAMKndsrQzp4IiuMfbpDYmRSV-Nw/exec";
    }

    public void crud(String act, String line, String name, String age, String email){
        try {
            url = new URL(urlName);
            connection = (HttpURLConnection) url.openConnection();
            // 배포 url
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);

            String jsonData = "{\"mode\": \"" + act + "\", " +
                    "\"idx\": \"" + line + "\", " +
                    "\"name\": \"" + name + "\", " +
                    "\"age\": \"" + age +"\", " +
                    "\"email\": \""+ email +"\"}";

            sendData(jsonData);

            if (act.equals("read")){
                printData(connection);
            }

            System.out.println(connection.getResponseCode());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendData(String jsonData){
        try(OutputStream os = connection.getOutputStream()){
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printData(HttpURLConnection connection){
        try {
            int responseCode = connection.getResponseCode();
            System.out.println("ResponseCode : " + responseCode);

            StringBuilder response = new StringBuilder();
            try (InputStream is = (responseCode >= 200 && responseCode <= 299) ?
                    connection.getInputStream() : connection.getErrorStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) {
                String ll;
                while ((ll = br.readLine()) != null) {
                    response.append(ll);
                }

                String message = response.toString();
                System.out.println(message);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
