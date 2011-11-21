package me.yuan.bdd;

import jdave.Block;
import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class MyStackSpec extends Specification<MyStack<?>> {
    private MyStack<Integer> stack;

    public class EmptyStack {
        public MyStack<Integer> create() {
            stack = new MyStack<Integer>();
            return stack;
        }

        public void isEmpty() {
            specify(stack, must.be.empty());
        }

        public void isNoLongerEmptyAfterPush() {
            stack.push(1);
            specify(stack, must.not().be.empty());
        }
    }

    public class FullStack {
        public MyStack<Integer> create() {
            stack = new MyStack<Integer>(10);
            for (int i = 0; i < 10; i++) {
                stack.push(i);
            }
            return stack;
        }

        public void isFull() {
            specify(stack, must.be.full());
        }

        public void complainsOnPush() {
            specify(new Block() {
                public void run() throws Throwable {
                    stack.push(100);
                }
            }, should.raise(StackOverflowException.class));
        }

        public void containsAllItems() {
            for (int i = 0; i < 10; i++) {
                specify(stack, contains(i));
            }
        }

        public void doesNotContainRemovedItem() {
            stack.remove(3);
            specify(stack, does.not().contains(3));
        }

        public void containsAllButRemovedItem() {
            stack.remove(3);
            specify(stack, containExactly(0, 1, 2, 3, 5, 6, 7, 8, 9));
        }
    }

    public class StackWhichIsNeitherEmptyNorFull {
        public MyStack<Integer> create() {
            stack = new MyStack<Integer>();
            for (int i = 0; i < 10; i++) {
                stack.push(i);
            }
            return stack;
        }

        public void addsToTheTopWhenSentPush() {
            stack.push(100);
            specify(stack.peek(), must.equal(100));
        }
    }
}
