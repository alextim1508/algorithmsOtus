package com.alextim;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String result = new Tester(args).run();
        System.out.println("Result:\n" + result);
    }
}
