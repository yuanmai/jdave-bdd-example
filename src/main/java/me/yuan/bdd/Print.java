package me.yuan.bdd;

import java.util.Stack;

public class Print implements Command {
    public String invoke(Stack<String> stack) {
        return stack.pop();
    }
}
