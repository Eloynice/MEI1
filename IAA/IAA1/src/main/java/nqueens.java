import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;
import java.util.Random;

public class nqueens {
    public static void main(String[] args) {
        Random rd = new Random(); // creating Random object
        int n = 10;
        int[] arr = new int[n];

        boolean correct = false;
        while (!correct) {
            correct = true;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt((n-1) + 1) + 1 ;
        }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] == arr[j]) {
                        correct = false;
                    }
                    if (arr[i] == (arr[j] + (j - i))) {
                        correct = false;
                    }
                    if (arr[i] == (arr[j] - (j - i))) {
                        correct = false;
                    }
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
