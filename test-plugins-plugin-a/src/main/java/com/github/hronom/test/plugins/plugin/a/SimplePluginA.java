package com.github.hronom.test.plugins.plugin.a;

import com.github.hronom.test.plugins.plugin.api.SimplePlugin;

import net.moznion.random.string.RandomStringGenerator;

public class SimplePluginA implements SimplePlugin {
    private final RandomStringGenerator generator = new RandomStringGenerator();
    private final String name = SimplePluginA.class.getSimpleName() + " XXX " +
                                generator.generateFromPattern("ccc");

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String process(String inputString) {
        return null;
    }

    @Override
    public void test() {

    }
}
