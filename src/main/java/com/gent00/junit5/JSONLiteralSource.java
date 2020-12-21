/*
 * Copyright 2020, Avinash Ramana, All rights reserved.
 */

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JSONArgumentSource.class)
public @interface JSONLiteralSource {
    /**
     * The name of the static variable
     */
    String valueSauce();
}
