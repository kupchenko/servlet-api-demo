package me.kupchenko.command;

import me.kupchenko.factory.AbstractFactory;
import me.kupchenko.factory.FactoryProducer;
import me.kupchenko.server.Servers;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class GetProductCountCommand extends Command {
    private FactoryProducer factoryProducer;

    public GetProductCountCommand(ExecutorService executorService, FactoryProducer factoryProducer) {
        super(executorService);
        this.factoryProducer = factoryProducer;
    }

    @Override
    public void execute(Servers server, String string, Socket socket) {
        AbstractFactory factory = factoryProducer.getFactory(server);
        executorService.submit(factory.getCountThread(string, socket));
    }
}
