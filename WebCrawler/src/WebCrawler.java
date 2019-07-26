package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebCrawler extends JFrame {
    JTextArea center;
    JButton button;
    JTextField urlInput;
    String LINE_SEPARATOR;
    JLabel title;

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        setTitle("Simple Window");
        getContentPane().setLayout(null);

        JTextArea center = new JTextArea();
        add(center);
        center.setName("HtmlTextArea");
        center.setBounds(10,75, 550,550);
        center.setEnabled(false);
        center.setVisible(true);
        center.setForeground(Color.BLACK);
        LINE_SEPARATOR = System.getProperty("line.separator");

        title = new JLabel();
        add(title);
        title.setName("TitleLabel");
        title.setBounds(10,35,550,20);
        title.setText("Title: ");

        button = new JButton("Get text!");
        add(button);
        button.setName("RunButton");
        button.setBounds(570,10,100,40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                downloadSource(center, title);
            }
        });

        urlInput = new JTextField();
        add(urlInput);
        urlInput.setName("UrlTextField");
        urlInput.setBounds(10,10,550,20);

    }

    public void downloadSource(JTextArea out, JLabel title) {
        final String url = urlInput.getText()/* Get url from JTextField */;

        final InputStream inputStream;
        final StringBuilder stringBuilder = new StringBuilder();
        String webTitle = "";
        try {
            inputStream = new URL(url).openStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));


            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                stringBuilder.append(nextLine);
                stringBuilder.append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String siteText = stringBuilder.toString();
        int start = siteText.indexOf("<title>");
        int end = siteText.indexOf("</",start);
        webTitle = siteText.substring(start + 7, end);
        out.setText(siteText);
        title.setText(webTitle);
    }
}