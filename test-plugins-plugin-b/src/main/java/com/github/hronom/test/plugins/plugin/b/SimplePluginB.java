package com.github.hronom.test.plugins.plugin.b;

import com.github.hronom.test.plugins.plugin.api.SimplePlugin;

import net.moznion.random.string.RandomStringGenerator;

public class SimplePluginB implements SimplePlugin {

    @Override
    public String getName() {
        RandomStringGenerator generator = new RandomStringGenerator();
        return SimplePluginB.class.getSimpleName() + " XXX " + generator.generateFromPattern("ccc");
    }

    @Override
    public String process(String inputString) {
        return null;
    }

    @Override
    public void test() {

    }
}
