package me.yuan.bdd;

import java.util.Stack;

public class Concat implements Command {
    public String invoke(Stack<String> stack) {
        String tail = stack.pop();
        String head = stack.pop();
        stack.push(head + tail);
        return null;
    }
}
