package me.kupchenko.factory;

import java.net.Socket;

public abstract class AbstractFactory {
    public abstract Runnable getCountThread(String s, Socket socket);

    public abstract Runnable getProductInfoThread(String s, Socket socket);

    public abstract Runnable getError(String s, Socket socket);
}