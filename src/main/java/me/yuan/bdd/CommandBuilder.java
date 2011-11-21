package me.yuan.bdd;

import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {
    private ArrayList<Command> program = new ArrayList<Command>();
    public static final Command PRINT = new Print();
    public static final Command CONCAT = new Concat();

    public CommandBuilder push(String value) {
        program.add(new Push(value));
        return this;
    }

    public List<Command> getProgram() {
        return program;
    }

    public CommandBuilder print() {
        program.add(PRINT);
        return this;
    }

    public CommandBuilder concat() {
        program.add(CONCAT);
        return this;
    }
}
