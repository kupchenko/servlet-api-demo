package me.kupchenko.command;


import java.util.HashMap;

public class CommandContainer {
    private final Command DEFAULT_COMMAD;
    private HashMap<String, Command> container = new HashMap<>();

    public CommandContainer(HashMap<String, Command> container, Command def) {
        this.DEFAULT_COMMAD = def;
        this.container = container;

    }

    public Command getCommand(String key) {
        return container.getOrDefault(key, DEFAULT_COMMAD);
    }
}
