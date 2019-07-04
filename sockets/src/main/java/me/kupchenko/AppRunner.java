package me.kupchenko;

import me.kupchenko.client.Client;
import me.kupchenko.command.Command;
import me.kupchenko.command.CommandContainer;
import me.kupchenko.command.DefaultCommand;
import me.kupchenko.command.GetProductCountCommand;
import me.kupchenko.command.GetProductInfoCommand;
import me.kupchenko.dao.ProductDAOImpl;
import me.kupchenko.entity.Car;
import me.kupchenko.entity.Helicopter;
import me.kupchenko.entity.Order;
import me.kupchenko.entity.Transport;
import me.kupchenko.factory.AbstractFactory;
import me.kupchenko.factory.FactoryProducer;
import me.kupchenko.factory.HTTPFactory;
import me.kupchenko.factory.TCPFactory;
import me.kupchenko.server.HTTPServer;
import me.kupchenko.server.Servers;
import me.kupchenko.server.TCPServer;
import me.kupchenko.service.ProductService;
import me.kupchenko.storage.Storage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRunner {
    private static final int HTTP_PORT = 8000;
    private static final int TCP_PORT = 3000;
    private static final String HOST = "localhost";
    private ExecutorService executorServiceHTTP = Executors.newFixedThreadPool(100);

    public static LinkedHashMap<String, Transport> initGoods(LinkedHashMap<String, Transport> goods) {
        goods.put("Bullet", new Car(1000, 800_000, 300, 4, 200, 4));
        goods.put("Porshe", new Car(1000, 860_000, 300, 4, 300, 4));
        goods.put("VAS", new Car(1000, 500_000, 234, 4, 150, 4));
        goods.put("LADA", new Car(1000, 1_000, 100, 4, 70, 4));

        goods.put("Apache", new Helicopter(1000, 1_000_000, 100, 100_000, 5));
        goods.put("Alegator", new Helicopter(1000, 1_200_000, 100, 200_000, 10));

        goods.put("Mitsubisi", new Transport(1000, 300_000, 353));
        goods.put("VAS", new Transport(1000, 500_000, 234));
        goods.put("Bullet", new Transport(1000, 80_000, 300));
        goods.put("LADA", new Transport(1000, 1_000, 100));
        goods.put("Bullet", new Transport(1000, 80_000, 300));
        goods.put("Mers", new Transport(1000, 2_000_000, 300));

        return goods;
    }

    public static TreeMap<Date, Order> initOrderList(TreeMap<Date, Order> orderList) {
        HashMap<String, Integer> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        map.put("iPhone 6", 6);
        map.put("iPhone 6s", 1);
        try {
            Order order = new Order(simpleDateFormat.parse("21.03.2016"), 5601, map);
            Order order1 = new Order(simpleDateFormat.parse("21.06.2016"), 5602, map);
            Order order2 = new Order(simpleDateFormat.parse("21.07.2016"), 5603, map);
            Order order3 = new Order(simpleDateFormat.parse("21.08.1996"), 5604, map);
            Order order4 = new Order(simpleDateFormat.parse("21.09.2001"), 5605, map);
            Order order5 = new Order(simpleDateFormat.parse("21.10.1999"), 5606, map);
            orderList.put(order.getDate(), order);
            orderList.put(order1.getDate(), order1);
            orderList.put(order2.getDate(), order2);
            orderList.put(order3.getDate(), order3);
            orderList.put(order4.getDate(), order4);
            orderList.put(order5.getDate(), order5);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return orderList;
    }

    public void start() {
        Storage storage = new Storage(initGoods(new LinkedHashMap<>()),
                new LinkedHashMap<>(),
                new LinkedHashMap<>(),
                initOrderList(new TreeMap<>()));
        ProductService productService = new ProductService(new ProductDAOImpl(storage.getGoods()));
        FactoryProducer factoryProducer = initDirector(productService);
        CommandContainer commandContainer = initCommandContainer(factoryProducer);
        try {
            Thread httpServer = new Thread(new HTTPServer(commandContainer, HTTP_PORT));
            httpServer.start();
        } catch (IOException e) {
            System.err.println("Error in HTTP server " + e);
        }
        try {
            TCPServer tcpServer = new TCPServer(commandContainer, TCP_PORT);
            tcpServer.startServer();
        } catch (IOException e) {
            System.err.println("Error in TCP server " + e);
        }
        Client client = new Client(HOST, TCP_PORT);
        client.makeRequest();
    }

    public CommandContainer initCommandContainer(FactoryProducer factoryProducer) {
        HashMap<String, Command> commands = new HashMap<>();
        Command commandCount = new GetProductCountCommand(executorServiceHTTP, factoryProducer);
        Command info = new GetProductInfoCommand(executorServiceHTTP, factoryProducer);

        commands.put("/shop/count", commandCount);
        commands.put("/shop/item", info);

        commands.put("exist count", commandCount);
        commands.put("exist item", info);

        return new CommandContainer(commands, new DefaultCommand(executorServiceHTTP, factoryProducer));
    }

    public FactoryProducer initDirector(ProductService productService) {
        HashMap<Servers, AbstractFactory> factories = new HashMap<>();
        factories.put(Servers.HTTP, new HTTPFactory(productService));
        factories.put(Servers.TCP, new TCPFactory(productService));
        return new FactoryProducer(factories);
    }
}
