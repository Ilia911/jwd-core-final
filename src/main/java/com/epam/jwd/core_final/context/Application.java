package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public interface Application {

    Logger LOGGER = LoggerFactory.getLogger(Application.class);

    static void start() {

        final ApplicationContext nassaContext = NassaContext.INSTANCE;
        try {
            nassaContext.init();
        } catch (InvalidStateException e) {
            LOGGER.error("Initialization failed" + e.getMessage());
        }

        final Supplier<ApplicationMenu> applicationMenuSupplier = () -> (ApplicationMenu) () -> nassaContext;
        // todo

        applicationMenuSupplier.get().greeting();
        applicationMenuSupplier.get().printAvailableOptions();
        try {
            applicationMenuSupplier.get().handleUserInput();
        } catch (InvalidStateException e) {
            LOGGER.error("Initialization failed" + e.getMessage());
        }
    }
}
