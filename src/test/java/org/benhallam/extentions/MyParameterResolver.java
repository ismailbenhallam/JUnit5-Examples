package org.benhallam.extentions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class MyParameterResolver implements ParameterResolver {
    public static final String VALUE_INJECTED_BY_PARAMETER_RESOLVER_EXTENSION = "Value injected by ParameterResolver extension!";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        System.out.println("---------\n" +
                "Extension : ParameterResolver\n" +
                "This extension help us to inject parameters in a test instance constructor or method\n" +
                "---------"
        );
        return parameterContext.getParameter().getType().equals(String.class) /*&& parameterContext.getIndex()==0*/;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return VALUE_INJECTED_BY_PARAMETER_RESOLVER_EXTENSION;
    }

}
