package me.kupchenko.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private static final String ENCODING = "UTF-8";
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void makeRequest() {
        new Thread(() -> {
            while (true) {
                try (Socket client = new Socket(host, port)) {
                    OutputStream outToServer = client.getOutputStream();
                    InputStream inFromServer = client.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    DataOutputStream out = new DataOutputStream(outToServer);
                    BufferedReader in = new BufferedReader(new InputStreamReader(inFromServer, ENCODING));

                    System.out.println("\nPrint request:");
                    String line = br.readLine();
                    out.writeBytes(line + '\n');
                    out.flush();
                    System.out.println(in.readLine());
                } catch (IOException e) {
                    System.err.println("Client error " + e);
                }

            }
        }).start();
    }
}
