package org.benhallam.extentions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class MyTestInstancePostProcessor implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
        System.out.println("---------\n" +
                "Extension : TestInstancePostProcessor\n" +
                "This extension help us to post process a test instance.\n" +
                "For example we can call a method on the test instance (Dependency Injection)\n" +
                "---------"
        );
//        o.getClass().getMethod("methodName",String.class).invoke("value");
    }
}
