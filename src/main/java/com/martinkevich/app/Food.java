package com.martinkevich.app;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by katermar on 10/4/2017.
 */
public class Food extends Product{
    private String name;
    private String price;
    private String description;
    private int calories;
    private Set<String> ingredients;

    public Food() {
        ingredients = new TreeSet<>();
    }


    public Food(String name, String price, String description, int calories, Set<String> ingredients) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (calories != food.calories) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (price != null ? !price.equals(food.price) : food.price != null) return false;
        if (description != null ? !description.equals(food.description) : food.description != null) return false;
        return ingredients != null ? ingredients.equals(food.ingredients) : food.ingredients == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "com.martinkevich.app.Food{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", ingredients=" + ingredients +
                '}';
    }
}
