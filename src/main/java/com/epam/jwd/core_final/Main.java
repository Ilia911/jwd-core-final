package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
        Application.start();
        //String s = "#faj";
        //System.out.println(s.startsWith("#"));
        System.out.println("hello");
        System.out.println();
        System.out.println(ApplicationProperties.getInstance().getDateTimeFormat());
    }
}