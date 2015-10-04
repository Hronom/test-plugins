package com.github.hronom.test.plugins.engine;

import com.github.hronom.test.plugins.plugin.api.SimplePlugin;

import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;
import org.xeustechnologies.jcl.JclUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class SimpleEngine {
    private String[] inputPaths;
    private URL[] inputUrls;

    public SimpleEngine() {
        try {
            inputPaths = new String[] {
                "../test-plugins-plugin-a/bin/lib/",
                "../test-plugins-plugin-a/target/classes/",
                "../test-plugins-plugin-b/bin/lib/",
                "../test-plugins-plugin-b/target/classes/"
            };

            inputUrls = new URL[] {
                new URL("file", "", "../test-plugins-plugin-a/bin/lib/"),
                //new URL("file", "", "../test-plugins-plugin-a/bin/test-plugins-plugin-a-1.0.0.jar"),
                //new URL("file", "", "../test-plugins-plugin-b/bin/test-plugins-plugin-b-1.0.0.jar"),
                new URL("file", "", "../test-plugins-plugin-a/target/classes/"),
                //new URL("file", "", "../test-plugins-plugin-b/target/classes/")
            };
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void load1() {
        try {
            URLClassLoader ucl = new URLClassLoader(
                inputUrls, Thread.currentThread().getContextClassLoader()
            );

            ServiceLoader<SimplePlugin> sl = ServiceLoader.load(SimplePlugin.class, ucl);
            Iterator<SimplePlugin> apit = sl.iterator();
            while (apit.hasNext()) {
                SimplePlugin simplePlugin = apit.next();
                System.out.println(simplePlugin.getName());
                simplePlugin.test();
            }
        } catch (ServiceConfigurationError e) {
            e.printStackTrace();
        }
    }

    public void load2() {
        try {
            URLClassLoader ucl = new URLClassLoader(
                inputUrls, Thread.currentThread().getContextClassLoader()
            );
            Class classToLoad = Class
                .forName("com.github.hronom.test.plugins.simple.plugin.a.SimplePluginA", true, ucl);
            SimplePlugin simplePlugin = (SimplePlugin) classToLoad.newInstance();
            System.out.println(simplePlugin.getName());
            simplePlugin.test();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoClassDefFoundError error) {
            error.printStackTrace();
        }
    }

    public void load3() {
        JarClassLoader jcl = new JarClassLoader();
        for (String paths : inputPaths) {
            jcl.add(paths);
        }

        // Create a factory of castable objects/proxies.
        JclObjectFactory factory = JclObjectFactory.getInstance();

        // A.
        {
            // Create object of loaded class.
            Object obj = factory
                .create(jcl, "com.github.hronom.test.plugins.plugin.a.SimplePluginA");

            // Obtain interface reference in the current classloader.
            SimplePlugin simplePlugin = JclUtils.cast(obj, SimplePlugin.class);
            System.out.println(simplePlugin.getName());
            simplePlugin.test();
        }

        // B.
        {
            // Create object of loaded class.
            Object obj = factory
                .create(jcl, "com.github.hronom.test.plugins.plugin.b.SimplePluginB");

            // Obtain interface reference in the current classloader.
            SimplePlugin simplePlugin = JclUtils.cast(obj, SimplePlugin.class);
            System.out.println(simplePlugin.getName());
            simplePlugin.test();
        }
    }
}
