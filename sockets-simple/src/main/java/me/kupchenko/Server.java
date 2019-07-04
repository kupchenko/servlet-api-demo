package me.kupchenko;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket server;
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private ObjectMapper objectMapper = new ObjectMapper();

    public void startServer() {
        try {
            server = new ServerSocket(8080);
            System.out.println("Server started!!!");
            while (true) {
                Socket accept = server.accept();
                executorService.submit(() -> handleRequest(accept));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(Socket socket) {
        try (OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             Writer res = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8))) {

            String url = getURL(in);
            if (url != null && !url.endsWith(".ico")) {
                System.out.println("Request from url " + url);
                if ("/json".equals(url)) {
                    String s = prepareJsonRequest();
                    res.append(s);
                } else {
                    String response = prepareHtmlResponse();
                    res.append(response);
                }
            } else {
                String s = prepareBadRequest();
                res.append(s);
            }
            res.flush();
        } catch (IOException e) {
            System.err.println("HTTP getCountThread error " + e);
        }
    }

    private String prepareBadRequest() {
        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 404 OK\r\n");

        return builder.toString();
    }

    private String prepareJsonRequest() throws IOException {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("field", "value");
        String body = objectMapper.writeValueAsString(objectNode);

        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 200 OK\r\n");
        builder.append("Content-Type: application/json\r\n");
        builder.append("\r\n");
        builder.append(body);

        return builder.toString();
    }

    private String prepareHtmlResponse() {
        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 201 OK\r\n");
        builder.append("Content-Type: text/html\r\n");
        builder.append("\r\n");
        builder.append("<TITLE>Example</TITLE>");
        builder.append("<P>Some content here</P>");

        return builder.toString();
    }

    private String getURL(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String fullPath = bufferedReader.readLine();
        if (fullPath == null || fullPath.split(" ").length < 1) {
            return fullPath;
        }
        return fullPath.split(" ")[1];
    }
}
