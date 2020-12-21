/*
 * Copyright 2020, Avinash Ramana, All rights reserved.
 */

package com.gent00.tests;

import com.gent00.junit5.JSONLiteralSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;

public class JSONStaticTest {
    @ParameterizedTest
    @JSONLiteralSource(valueSauce = "{\"names\":[{\"name\":\"God\"},{\"name\":\"Dog\"}]}")
    public void testName(ArgumentsAccessor argumentsAccessor) {
        System.out.println("Asserting on " + argumentsAccessor.getString(0));
        Assertions.assertTrue(argumentsAccessor.getString(0).length() == 3);
    }

    @Test
    public void testDog1() {
        Assertions.assertTrue("Dog".length() == 3);
    }

    @Test
    public void testGod1() {
        Assertions.assertTrue("God".length() == 3);
    }

}
