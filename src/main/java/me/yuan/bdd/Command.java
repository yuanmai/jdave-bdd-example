package me.yuan.bdd;

import java.util.Stack;

public interface Command {
    String invoke(Stack<String> stack);
}
