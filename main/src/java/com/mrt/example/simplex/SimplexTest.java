package com.mrt.example.simplex;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by katermar on 4/9/2017.
 */
public class SimplexTest {

    static Stack<String> users = new Stack<>();

    private String FILENAME;
    static int counter = 0;
    private ArrayList<float[][]> listOfTables;
    boolean state;
    private float[][] standardized;

    public SimplexTest() {
        listOfTables = new ArrayList<float[][]>();
        counter++;
        FILENAME = "D:\\log(" + users.peek() + counter + ")" + LocalDate.now() + ".txt";
    }

    public static void setUsers(String user) {
        SimplexTest.users.push(user);
    }

    public boolean calculate() {
        boolean quit = false;

        Simplex simplex = new Simplex(standardized.length - 1, standardized[0].length - 1);

        simplex.fillTable(standardized);

        // print it out
        System.out.println("---Starting set---");
        writeToFile("---Starting set---");
        writeArray(simplex.getTable());
        simplex.print();
        listOfTables.add(standardized);

        // if table is not optimal re-iterate
        while (!quit) {
            Simplex.ERROR err = simplex.compute();

            if (err == Simplex.ERROR.IS_OPTIMAL) {
                writeArray(simplex.getTable());
                simplex.print();
                state(true);
                listOfTables.add(simplex.getTable());
                quit = true;
            } else if (err == Simplex.ERROR.UNBOUNDED) {
                System.out.println("---Solution is unbounded---");
                writeToFile("---Solution is unbounded---");
                state(false);
                quit = true;
            }
        }

        return state;
    }

    public boolean state(boolean state) {
        this.state = state;
        return state;
    }

    public boolean writeToFile(String info) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
            bw.newLine();
            bw.write(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean writeArray(float[][] array) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
            bw.newLine();
            for (int i = 0; i < array.length; i++) {
                bw.write(Arrays.toString(array[i]));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public float[][] getStandardized() {
        return standardized;
    }

    public float[][] makeStandadized(float[][] array) {
        float data[][] = new float[array.length][(array[0].length * 2) - 1];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (i == array.length - 1) {
                    data[i][j] = -array[i][j];
                } else {
                    data[i][j] = array[i][j];
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = array[0].length - 1; j < data[0].length - 1; j++) {
                if (i == j - array[0].length) {
                    data[i][j] = 1;
                } else {
                    data[i][j] = 0;
                }
            }
            data[i][data[0].length - 1] = array[i][array[0].length - 1];
        }

        setStandardized(data);

        return data;
    }

    public void setStandardized(float[][] standardized) {
        this.standardized = standardized;
    }

    public ArrayList<float[][]> getListOfTables() {
        return listOfTables;
    }

    public void setListOfTables(ArrayList<float[][]> listOfTables) {
        this.listOfTables = listOfTables;
    }

    public static void main(String[] args) {

        boolean quit = false;

        // Example problem:
        // maximize 3x + 5y
        // subject to x +  y = 4 and
        //            x + 3y = 6
        float[][] standardized = {
                {0.8f, 0.5f, 0.6f, 1, 0, 0, 800},
                {0.4f, 0.4f, 0.3f, 0, 1, 0, 600},
                {0, 0.1f, 0.1f, 0, 0, 1, 120},
                {-108, -112, -126, 0, 0, 0, 0}
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
        while (!quit) {
            Simplex.ERROR err = simplex.compute();

            if (err == Simplex.ERROR.IS_OPTIMAL) {
                simplex.print();
                quit = true;
            } else if (err == Simplex.ERROR.UNBOUNDED) {
                System.out.println("---Solution is unbounded---");
                quit = true;
            }
        }
    }
}
