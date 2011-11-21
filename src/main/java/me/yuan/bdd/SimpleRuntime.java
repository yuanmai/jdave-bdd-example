package me.yuan.bdd;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Stack;

public class SimpleRuntime implements Runtime {
    private Stack<String> stack = new Stack<String>();
    private Writer output = new PrintWriter(System.out);
    private int count = 0;

    public Stack<String> run(List<Command> program) {
        return run(program, cpu());
    }

    public Stack<String> run(List<Command> program, Processor processor) {
        for (Command each : program) {
            processor.process(each);
            count++;
        }
        return stack;
    }

    public Processor cpu() {
        return new Processor() {
            public void process(Command command) {
                command.invoke(stack);
            }
        };
    }

    public Processor breakAt(final int breakPoint, final Processor inner) {
        return new Processor() {
            public void process(Command command) {
                if (breakPoint > count) {
                    inner.process(command);
                }
            }
        };
    }

    public void printTo(Writer output) {
        this.output = output;
    }

    public Processor tracing(final Processor inner) {
        return new Processor() {
            public void process(Command command) {
                inner.process(command);
                try {
                    output.write(stack.size() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
