package com.github.hronom.test.plugins.engine.viewer;

import com.github.hronom.test.plugins.engine.SimpleEngine;

public class App {
    public static void main(String[] args) {
        SimpleEngine simpleEngine = new SimpleEngine();
        System.out.println("Load 1");
        //simpleEngine.load1();
        System.out.println("Load 2");
        //simpleEngine.load2();
        System.out.println("Load 3");
        simpleEngine.load3();
    }
}
