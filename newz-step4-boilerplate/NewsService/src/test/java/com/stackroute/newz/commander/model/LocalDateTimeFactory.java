package com.stackroute.newz.commander.model;

import java.time.LocalDateTime;

import org.meanbean.lang.Factory;

public class LocalDateTimeFactory implements Factory<LocalDateTime> {
    @Override
    public LocalDateTime create() {
        return LocalDateTime.now();
    }
}

