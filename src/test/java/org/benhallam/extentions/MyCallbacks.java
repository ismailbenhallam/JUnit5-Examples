package org.benhallam.extentions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MyCallbacks implements BeforeAllCallback, BeforeTestExecutionCallback, BeforeEachCallback /* There are also the "After*" callbacks */ {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("---------\n" +
                "Extension : BeforeAllCallback\n" +
                "It's executed even before the method annotated with @BeforeAll\n" +
                "---------"
        );
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("---------\n" +
                "Extension : BeforeEachCallback\n" +
                "It's executed before the method annotated with @BeforeEach\n" +
                "---------"
        );
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        System.out.println("---------\n" +
                "Extension : BeforeTestExecutionCallback\n" +
                "It's executed JUST before the execution of test\n" +
                "---------"
        );
    }

}
