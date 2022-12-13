package semestralka;

import java.util.Scanner;
/**
 * min = 3,743392E-23
 * max = 3,4028235E38
 */
/**
 * 29. Checks if the system of n-vectors with n-elements is orthogonal and if
 * so, trarnsforms the vectors to normal form.
 *
 * @author Petr Kaštánek
 */
public class KastanekPetrSemestralProject {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int count;
        float[][] matrix;
        double[] sizes;
        while (true) {
            //input
            System.out.println("Zadej pocet vektoru");
            count = sc.nextInt();
            if (count <= 0) {     //ends the program
                break;
            }
            System.out.println("Zadej vektory");

            //generation + output
            matrix = CreateSquareMatrix(count);     //creates the matrix
            sizes = CalcSizes(matrix);       //creates array of the vector sizes
            if (sizes.length == 1) {        //checks if the user input generates matrix
                System.out.println("Není soubor vektorů");
            } 
            else if (!IsInArray(sizes, 0) && DotProductsAllZeros(matrix)) {      //checks if matrix is orthogonal
                PrintMatrix(matrix);
                System.out.println("System je ortogonalni");
                Normalize(matrix, sizes);       //transforms the matrix into normal form
                PrintMatrix(matrix);
            } else {
                PrintMatrix(matrix);
                System.out.println("System neni ortogonalni");
            }
            System.out.println();
        }
    }

    /**
     * Creates square matrix with size a*a using user input
     *
     * @param size size of the matrix
     * @return matrix a*a filled with user inputed numbers
     */
    private static float[][] CreateSquareMatrix(int size) {
        float arr[][] = new float[size][size];
        for (int m = 0; m < size; m++) {
            for (int n = 0; n < size; n++) {
                arr[m][n] = sc.nextFloat();
            }
        }
        return arr;
    }

    /**
     * Calculates size of each vector in a matrix
     *
     * @param arr matrix of vectors
     * @return array of vector sizes
     */
    private static double[] CalcSizes(float[][] arr) {
        double sizes[] = new double[arr.length];
        double sum;
        for (int m = 0; m < arr.length; m++) {
            sum = 0;
            for (int n = 0; n < arr.length; n++) {
                sum += Math.pow(arr[m][n], 2);
            }
            sizes[m] = Math.sqrt(sum);
        }
        return sizes;
    }

    /**
     * Transforms vectors in matrix to normal form
     *
     * @param arr matrix to transform
     * @param sizes array of vector sizes
     */
    private static void Normalize(float[][] arr, double[] sizes) {
        for (int m = 0; m < arr.length; m++) {
            for (int n = 0; n < arr.length; n++) {
                arr[m][n] /= sizes[m];
            }
        }
    }

    /**
     * Checks if the every dot product equals zero
     *
     * @param array matrix to check
     * @return boolean (true if avery dot product equals zero)
     */
    private static boolean DotProductsAllZeros(float[][] array) {
        double prod;

        for (int lineA = 0; lineA < array.length - 1; lineA++) {
            for (int lineB = lineA + 1; lineB < array.length; lineB++) {
                prod = 0;
                for (int n = 0; n < array.length; n++) {
                    prod += (array[lineA][n] * array[lineB][n]);
                }
                
                if (prod != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if specified element is in the array
     *
     * @param a the array to arr in
     * @param a the element to find
     * @return boolean (true if element is in array)
     */
    private static boolean IsInArray(double[] arr, int a) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == a) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints matrix
     *
     * @param array Matrix to print
     */
    private static void PrintMatrix(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
