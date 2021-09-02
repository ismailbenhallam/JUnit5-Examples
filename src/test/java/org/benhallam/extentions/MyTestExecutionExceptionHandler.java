package org.benhallam.extentions;

import org.benhallam.MyCustomException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class MyTestExecutionExceptionHandler implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        System.out.println("---------\n" +
                "Extension : TestExecutionExceptionHandler\n" +
                "This extension help us catch an exception thrown by a test method\n" +
                "---------"
        );
        if (throwable.getClass().equals(MyCustomException.class)) {
            System.out.println("Exception caught by MyTestExecutionExceptionHandler :)");
            return;
        }

        throw throwable;
    }
}
