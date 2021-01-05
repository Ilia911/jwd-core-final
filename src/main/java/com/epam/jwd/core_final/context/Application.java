package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static void start() throws InvalidStateException {

        final ApplicationContext nassaContext = NassaContext.INSTANCE;
        nassaContext.init();

        final Supplier<ApplicationMenu> applicationMenuSupplier = () -> (ApplicationMenu) () -> nassaContext;
        // todo

        applicationMenuSupplier.get().printAvailableOptions();

        //applicationMenuSupplier.get().handleUserInput();

    }
}
