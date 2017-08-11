package com.mrt.example.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by USER on 5/1/2017.
 */
public class ResultModel {
    @JsonProperty("array")
    float[][] array;

    @JsonProperty("message")
    String message;

    @JsonCreator
    public ResultModel(float[][] array, String message) {
        this.array = array;
        this.message = message;
    }

    public ResultModel() {
    }

    public void setArray(float[][] array) {
        this.array = array;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
