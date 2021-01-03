package com.company.item2;

public class TelescopingPatternNutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    // har to write client code when there are many parameters, and harder still to read it
    public TelescopingPatternNutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public TelescopingPatternNutritionFacts(int servingSize, int servings,
                                            int calories) {
        this(servingSize, servings, calories, 0);
    }

    public TelescopingPatternNutritionFacts(int servingSize, int servings,
                                            int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public TelescopingPatternNutritionFacts(int servingSize, int servings,
                                            int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public TelescopingPatternNutritionFacts(int servingSize, int servings,
                                            int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
