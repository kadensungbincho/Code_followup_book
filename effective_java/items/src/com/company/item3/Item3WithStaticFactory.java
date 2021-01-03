package com.company.item3;

public class Item3WithStaticFactory {
    private static final Item3WithStaticFactory INSTANCE = new Item3WithStaticFactory();
    private Item3WithStaticFactory() {}

    public static Item3WithStaticFactory getInstance() {
        return INSTANCE;
    }
}
