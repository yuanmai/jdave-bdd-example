package me.yuan.bdd;

import jdave.Specification;
import org.junit.runner.notification.RunNotifier;

public class Naughty {
    private static int seed = 0;
    private static String[] strings = new String[]{"foo", "bar"};

    public String stringFoo() {
        return strings[seed];
    }

    public String stringBar() {
        return strings[1 - seed];
    }

    public String string(String prefix) {
        return prefix + seed;
    }

    public static class JDaveRunner extends jdave.junit4.JDaveRunner {
        public JDaveRunner(Class<? extends Specification<?>> spec) {
            super(spec);
        }

        @Override
        public void run(RunNotifier notifier) {
            seed = 0;
            super.run(notifier);
            seed = 1;
            super.run(notifier);
        }
    }
}
