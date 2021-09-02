package org.benhallam.extentions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class MyExecutionCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        System.out.println("---------\n" +
                "Extension : ExecutionCondition\n" +
                "This extension help us disable a test or not, based on the returned value of its method.\n" +
                "---------"
        );

        // If....
        return ConditionEvaluationResult.disabled("This test shouldn't be run !");

        // Or
        // return ConditionEvaluationResult.enabled("reason");

    }
}
