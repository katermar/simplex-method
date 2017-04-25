package com.mrt.example.simplex;

/**
 * Created by USER on 4/9/2017.
 */
public class SimplexTest {

    public static void main(String[] args) {

        boolean quit = false;

        // Example problem:
        // maximize 3x + 5y
        // subject to x +  y = 4 and
        //            x + 3y = 6
        float[][] standardized =  {
                { 0.8f,   0.5f, 0.6f,  1,  0,  0,  800},
                { 0.4f,   0.4f, 0.3f,  0,  1,  0,  600},
                {    0,   0.1f, 0.1f,  0,  0,  1,  120},
                {-108 ,   -112, -126,  0,  0,  0,  0  }
        };

        // row and column do not include
        // right hand side values
        // and objective row

        Simplex simplex = new Simplex(3, 6);

        simplex.fillTable(standardized);

        // print it out
        System.out.println("---Starting set---");
        simplex.print();

        // if table is not optimal re-iterate
        while(!quit){
            Simplex.ERROR err = simplex.compute();

            if(err == Simplex.ERROR.IS_OPTIMAL){
                simplex.print();
                quit = true;
            }
            else if(err == Simplex.ERROR.UNBOUNDED){
                System.out.println("---Solution is unbounded---");
                quit = true;
            }
        }
    }
}
