package com.company.item5;

import java.util.HashMap;
import java.util.Map;

public class InappropriateSpellChecker1 {
    private static final Map dictionary = new HashMap();

    private InappropriateSpellChecker1() {}

    /*

     - Static utility classes and singletons are inappropriate for classes
        whose behavior is parameterized by an underlying resource.
     */
}
