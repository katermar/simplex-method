package com.mrt.example.simplex;

import com.mrt.example.model.ValuesModel;

/**
 * Created by katermar on 5/1/2017.
 */
public class SimplexService {
    SimplexTest test;

    public boolean test(ValuesModel valuesModel) {
        test = new SimplexTest();
        float[][] array = valuesModel.getArray();
        test.makeStandadized(array);
        return test.calculate();
    }

    public float[][] getStartTable() {
        return test.getListOfTables().get(0);
    }

    public float[][] getResultTable() {
        return test.getListOfTables().get(1);
    }

}
