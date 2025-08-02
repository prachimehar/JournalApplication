package com.prachimehar.journalApp.service;

import com.prachimehar.journalApp.Entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomStringProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(User.builder().username("ram").password("ram").build()),
                Arguments.of(User.builder().username("yash").password("").build())
        );
    }
}