package me.yuan.bdd;

import jdave.Specification;
import jdave.contract.EqualsHashCodeContract;
import org.jmock.Expectations;
import org.junit.runner.RunWith;

import java.util.Stack;

@RunWith(Naughty.JDaveRunner.class)
public class CommandSpec extends Specification<Command> {
    private Command command;
    private Stack<String> stack = mock(Stack.class);
    private Naughty a = new Naughty();

    public class PushCommand {
        {
            command = new Push(a.stringFoo());
        }

        public void pushesStringIntoStack() {
            checking(new Expectations() {{
                one(stack).push(a.stringFoo());
            }});
            command.invoke(stack);
        }

        public void hasConsistentEqualsAndHashCode() {
            specify(command, satisfies(new EqualsHashCodeContract<Push>() {
                @Override
                protected Push equal() {
                    return new Push(a.stringFoo());
                }

                @Override
                protected Push nonEqual() {
                    return new Push(a.stringBar());
                }

                @Override
                protected Push subType() {
                    return new Push(a.stringFoo()) {
                    };
                }
            }));
        }
    }

    public class ConcatCommand {
        {
            command = new Concat();
        }

        public void concatsTheTopTwoStringsThenPushItBack() {
            checking(new Expectations() {{
                one(stack).pop();
                will(returnValue(a.string("tail")));
                one(stack).pop();
                will(returnValue(a.string("head")));
                one(stack).push(a.string("head") + a.string("tail"));
            }});
            command.invoke(stack);
        }
    }

    public class PrintCommand {
        {
            command = new Print();
        }

        public void printsTheTopString() {
            checking(new Expectations() {{
                allowing(stack).pop();
                will(returnValue(a.stringFoo()));
            }});
            specify(command.invoke(stack), must.equal(a.stringFoo()));
        }
    }

    public class HelloWorldProgram {
        private Command[] program = new Command[]{
                new Push("Hello "),
                new Push("World!"),
                new Concat(),
                new Print(),
        };

        {
            stack = new Stack<String>();
        }

        public void concatsHelloAndWorldThenPrintsIt() {
            String lastValue = null;
            for (Command command : program) {
                lastValue = command.invoke(stack);
            }
            specify(lastValue, must.equal("Hello World!"));
        }
    }
}
