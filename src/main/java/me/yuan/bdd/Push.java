package me.yuan.bdd;

import java.util.Stack;

public class Push implements Command {
    private final String value;

    public Push(String value) {
        this.value = value;
    }

    public String invoke(Stack<String> stack) {
        stack.push(value);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Push push = (Push) o;

        if (value != null ? !value.equals(push.value) : push.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}
