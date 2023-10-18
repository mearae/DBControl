import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static String urlName  = "https://script.google.com/macros/s/AKfycbyWIqG1Mt1QTgCw1ihed7UAa2efXqqmr2YgFFtRaalAMKndsrQzp4IiuMfbpDYmRSV-Nw/exec";

    public static void crud(String act,String line,String name,String age,String email){
        try {
            // 배포 url
            URL url  = new URL(urlName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);

            String jsonData = "{\"mode\": \"" + act + "\", " +
                    "\"idx\": \"" + line + "\", " +
                    "\"name\": \"" + name + "\", " +
                    "\"age\": \"" + age +"\", " +
                    "\"email\": \""+ email +"\"}";

            try(OutputStream os = connection.getOutputStream()){
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            if (act.equals("read")){
                printData(connection);
            }

            System.out.println(connection.getResponseCode());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void printData(HttpURLConnection connection){
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

    static Scanner scanner = new Scanner(System.in);

    public static String scan(String key){
        String value = null;

        try {
            System.out.println(key+" : ");

            value = scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("잘못 입력");
        }
        return value;
    }

    public static void main(String[] args) {
        JTextArea jTextArea = new JTextArea();

        JFrame frame = new JFrame("test");

        frame.setLayout((new BorderLayout()));
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        JButton button1 = new JButton("C");
        JButton button2 = new JButton("R");
        JButton button3 = new JButton("U");
        JButton button4 = new JButton("D");

        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("create","",scan("name"),scan("age"),scan("email"));
            }
        });

        button2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("read",scan("line"),"","","");
            }
        });

        button3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("update",scan("line"),scan("name"),scan("age"),scan("email"));
            }
        });

        button4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crud("delete",scan("line"),"","","");
            }
        });

        jPanel.add(button1);
        jPanel.add(button2);
        jPanel.add(button3);
        jPanel.add(button4);

        frame.add(jPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(jTextArea),BorderLayout.CENTER);

        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {
                scanner.close();
            }

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
}
