package me.yuan.bdd;

import jdave.Specification;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(Naughty.JDaveRunner.class)
public class CommandBuilderSpec extends Specification<List<Command>> {
    private CommandBuilder builder = new CommandBuilder();
    private List<Command> program;
    @Rule
    public Naughty a = new Naughty();

    public class EmptyProgram {
        public List<Command> create() {
            program = builder.getProgram();
            return program;
        }

        public void itsProgramIsEmpty() {
            specify(program, must.be.isEmpty());
        }
    }

    public class SimpleProgram {

        public void create() {
            program = builder
                    .push(a.stringFoo())
                    .push(a.stringBar())
                    .concat()
                    .print()
                    .getProgram();
        }

        public void buildsCommandsInOrder() {
            specify(program, containsAll(
                    new Push(a.stringFoo()),
                    new Push(a.stringBar()),
                    CommandBuilder.CONCAT,
                    CommandBuilder.PRINT
            ));
        }
    }
}
