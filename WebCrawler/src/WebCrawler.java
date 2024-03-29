package crawler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class WebCrawler extends JFrame {
    JTextArea center;
    JButton button;
    JButton export;
    JTextField urlInput;
    JTextField exportFileName;
    String LINE_SEPARATOR;
    JLabel title;
    JTable table;

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setVisible(true);
        setTitle("Simple Window");
        getContentPane().setLayout(null);

        JTextArea center = new JTextArea();
        //add(center);
        center.setName("HtmlTextArea");
        center.setBounds(10,75, 550,550);
        center.setEnabled(false);
        center.setVisible(true);
        center.setForeground(Color.BLACK);

        title = new JLabel();
        add(title);
        title.setName("TitleLabel");
        title.setBounds(10,35,550,20);
        title.setText("Title: ");

        Object[] column = {"URL","Title"};
        table = new JTable(new DefaultTableModel(column, 0));
        table.setName("TitlesTable");
        table.setEnabled(false);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10,75, 550,550);
        add(pane);

        button = new JButton("Get text!");
        add(button);
        button.setName("RunButton");
        button.setBounds(570,10,100,40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                downloadSource(table, title);
            }
        });

        urlInput = new JTextField();
        add(urlInput);
        urlInput.setName("UrlTextField");
        urlInput.setBounds(10,10,550,20);

        export = new JButton("Save");
        add(export);
        export.setName("ExportButton");
        export.setBounds(570,650,100,40);
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                exportTable(table);
            }
        });

        exportFileName = new JTextField();
        add(exportFileName);
        exportFileName.setName("ExportUrlTextField");
        exportFileName.setBounds(10,650,550,20);

        LINE_SEPARATOR = System.getProperty("line.separator");
    }

    public void exportTable(JTable out) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        String fileName = exportFileName.getText();
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            for (Vector v : model.getDataVector()) {
                writer.write(v.get(0).toString() + "\n");
                writer.write(v.get(1).toString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void downloadSource(JTable out, JLabel title) {
        final String url = urlInput.getText()/* Get url from JTextField */;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setNumRows(0);
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
        title.setText(webTitle);

        start = siteText.indexOf("href=\"", 0);
        try {
            while (start > 0) {
                end = siteText.indexOf("\"", start + 6);
                String urlLink = siteText.substring(start + 6, end);
                if (!urlLink.contains("/")) {
                    urlLink = url + urlLink;
                }
                if (!urlLink.startsWith("http")) {
                    urlLink = "http://" + urlLink;
                }
                URLConnection connection = new URL(urlLink).openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
                System.out.println(connection.getContentType());
                if (connection.getContentType().startsWith("text/html")) {
                    String linkTitle = extractTitle(connection);
                    model.addRow(new Object[]{urlLink, linkTitle});
                }
                start = siteText.indexOf("href=", start+6);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String extractTitle(URLConnection connection) {
        final InputStream inputStream;
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = connection.getInputStream();
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
        return siteText.substring(start + 7, end);
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