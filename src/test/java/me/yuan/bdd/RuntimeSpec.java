package me.yuan.bdd;

import jdave.Specification;
import org.junit.runner.RunWith;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@RunWith(Naughty.JDaveRunner.class)
public class RuntimeSpec extends Specification<Runtime> {
    private Runtime runtime = new SimpleRuntime();
    private Naughty a = new Naughty();
    private List<Command> program;
    private Stack<String> stack;
    private StringWriter output = new StringWriter();

    public class ProgramIsEmpty {
        {
            program = new ArrayList<Command>();
        }

        public void doesNothingToStackOnRun() {
            stack = runtime.run(program);
            specify(stack.empty());
        }
    }

    public class ProgramHasTwoCommands {
        {
            program = new CommandBuilder()
                    .push(a.stringFoo())
                    .push(a.stringBar())
                    .getProgram();
        }

        public void invokesCommandsInSequence() {
            stack = runtime.run(program);
            specify(stack, containsInOrder(a.stringFoo(), a.stringBar()));
        }

        public void breaksAtBreakPoint() {
            stack = runtime.run(program, runtime.breakAt(1, runtime.cpu()));
            specify(stack, containsExactly(a.stringFoo()));
        }

        public void printsStackSizeAfterInvokingEachCommand() {
            runtime.printTo(output);
            stack = runtime.run(program, runtime.tracing(runtime.cpu()));
            specify(output.toString(), equal("1\n2\n"));
        }
    }
}
