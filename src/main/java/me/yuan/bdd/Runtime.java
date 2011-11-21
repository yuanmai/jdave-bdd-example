package me.yuan.bdd;

import java.io.Writer;
import java.util.List;
import java.util.Stack;

public interface Runtime {
    Stack<String> run(List<Command> program);

    Stack<String> run(List<Command> program, Processor processor);

    Processor cpu();

    Processor breakAt(int breakPoint, Processor inner);

    void printTo(Writer output);

    Processor tracing(Processor inner);
}
