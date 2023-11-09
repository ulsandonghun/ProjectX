package org.example.week2;

import java.util.ArrayList;

public class RoadToBiodome02 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        String s = String.join(" ", args);
        String[] strarr=s.split("");
        for (String i : strarr) {
            stack.push(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());

    }

}

class Stack {
    private ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> getList() {
        return list;
    }

    public void push(String a) {
        list.add(a);
    }

    public String pop() {
        return list.remove(list.size() - 1);
    }
    public boolean isEmpty() {
        if (list.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

}
