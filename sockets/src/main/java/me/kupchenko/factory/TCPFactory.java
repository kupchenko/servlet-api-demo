package me.kupchenko.factory;


import me.kupchenko.entity.Transport;
import me.kupchenko.service.ProductService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class TCPFactory extends AbstractFactory {
    private static final String ENCODING = "UTF-8";
    private ProductService service;

    public TCPFactory(ProductService service) {
        this.service = service;
    }

    @Override
    public Runnable getCountThread(String string, Socket socket) {
        return () -> {
            int size = service.getCount();
            try (OutputStream out = socket.getOutputStream(); Writer output = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                output.append("count=" + size);

            } catch (IOException e) {
                System.err.println("TCP getCountThread error " + e);
            }

        };
    }

    @Override
    public Runnable getProductInfoThread(String string, Socket socket) {
        return () -> {
            String value = string.split("=")[1];
            Transport transport = service.getTransport(value);
            try (OutputStream out = socket.getOutputStream(); Writer output = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                if (transport != null) {
                    output.append(value + " | " + transport.getPrice());
                } else {
                    output.append("Nothing found");
                }
            } catch (IOException e) {
                System.err.println("TCP getProductInfoThread error " + e);
            }
        };
    }

    @Override
    public Runnable getError(String s, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer output = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                output.append("Error");
            } catch (IOException e) {
                System.err.println("TCP getError error " + e);
            }
        };
    }
}
