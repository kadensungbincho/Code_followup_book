package com.company.item5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpellChecker {
    private final Map dictionary;

    // dependency injection
    public SpellChecker(HashMap dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
}
