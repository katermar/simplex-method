package com.martinkevich.app;

/**
 * Created by katermar on 10/4/2017.
 */
public class FoodCreator extends Creator {
    @Override
    public Product factoryMethod() {
        return new Food();
    }
}
