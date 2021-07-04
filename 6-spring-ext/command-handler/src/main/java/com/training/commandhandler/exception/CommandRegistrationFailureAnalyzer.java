package com.training.commandhandler.exception;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class CommandRegistrationFailureAnalyzer extends
        AbstractFailureAnalyzer<CommandHandlerRegistrationException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, CommandHandlerRegistrationException cause) {

        StringBuilder msgBuilder = new StringBuilder();
        String msg = msgBuilder
                .append(cause.getMessage())
                .append(", Bean name:" + cause.getBeanName())
                .append(", Bean class: " + cause.getAClass().getName())
                .append(", Method name: " + cause.getMethodName())
                .toString();
        return new FailureAnalysis(msg,"Make method accept only one parameter",cause);
    }
}
