package com.mrt.example.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

/**
 * Created by katermar on 4/30/2017.
 */
public class ValuesModel {
    private float[][] array;

    @JsonCreator
    public ValuesModel() {

    }

    @Override
    public String toString() {
        return "ValuesModel{" +
                "array=" + Arrays.deepToString(array) +
                '}';
    }

    @JsonAnySetter
    public void setArray(float[][] array) {
        this.array = array;
    }

    public float[][] getArray() {
        return array;
    }
}
