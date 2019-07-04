package me.kupchenko.factory;

import me.kupchenko.entity.Transport;
import me.kupchenko.service.ProductService;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class HTTPFactory extends AbstractFactory {

    private static final String ENCODING = "UTF-8";
    private ProductService service;

    public HTTPFactory(ProductService service) {
        this.service = service;
    }

    @Override
    public Runnable getCountThread(String string, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer res = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                JSONObject jsonObject = new JSONObject();
                int size = service.getCount();
                jsonObject.put("count", size);
                res.append(jsonObject.toString());
                res.flush();
            } catch (IOException e) {
                System.err.println("HTTP getCountThread error " + e);
            }
        };
    }

    @Override
    public Runnable getProductInfoThread(String string, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer res = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                JSONObject jsonObject = new JSONObject();
                String value = string.split("=")[1];
                Transport transport = service.getTransport(value);
                if (transport != null) {
                    jsonObject.put("name", value);
                    jsonObject.put("price", transport.getPrice());
                    res.append(jsonObject.toString());
                } else {
                    res.append("Nothing found");
                }
            } catch (IOException e) {
                System.err.println("HTTP getProductInfoThread error " + e);
            }
        };
    }

    @Override
    public Runnable getError(String s, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer res = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                res.append("Error");
            } catch (IOException e) {
                System.err.println("HTTP getError error " + e);
            }
        };
    }

}
