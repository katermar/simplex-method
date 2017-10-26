package com.martinkevich.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katermar on 10/4/2017.
 */
public class BreakfastMenu {

    private String day;
    private List<Food> menu;

    private BreakfastMenu() {
        // private constructor
        menu = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "com.martinkevich.app.BreakfastMenu{" +
                "day='" + day + '\'' +
                ", menu=" + menu +
                '}';
    }

    public String getDay() {
        return day;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public static Builder newBuilder() {
        return new BreakfastMenu().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setDay(String day) {
            BreakfastMenu.this.day = day;

            return this;
        }

        public Builder setMenu(List<Food> menu) {
            BreakfastMenu.this.menu = menu;

            return this;
        }

        public BreakfastMenu build() {
            return BreakfastMenu.this;
        }

    }

}
