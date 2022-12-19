package semestralka;

import java.util.Scanner;

/**
 * minimal input value = 3,743392E-23
 * maximal input value = 3,4028235E38
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
        //init
        int count;
        float[][] matrix;
        double[] sizes;
        boolean isMatrix = true;
        boolean isOrthogonal = true;
        while (true) {

            //input
            System.out.println("Zadej pocet vektoru:");
            count = sc.nextInt();
            if (count <= 0) {     //ends the program
                break;
            }
            System.out.println("Zadej vektory:");
            matrix = CreateSquareMatrix(count);     //creates the matrix
            sizes = CalcSizes(matrix);       //creates array of the vector sizes

            //calc
            if (sizes.length == 1) {        //checks if the user input generates matrix
                isMatrix = false;
            } else {
                isOrthogonal = (!IsInArray(sizes, 0) && DotProductsAllZeros(matrix)); //checks if matrix is orthogonal
            }

            //out
            System.out.println();
            PrintMatrix(matrix);
            System.out.println();
            if (isMatrix && isOrthogonal) {
                System.out.println("System je ortogonalni.");
                System.out.println();
                Normalize(matrix, sizes);       //transforms the matrix into normal form
                PrintMatrix(matrix);
            } else if (isMatrix) {
                System.out.println("Systém není ortogonální.");
            } else {
                System.out.println("Není soubor vektorů.");
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
     * Transforms vectors in matrix to normal form by dividing each element by
     * the vector size
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
                for (int n = 0; n < array.length; n++) {        //no need to use array[0].length because the matrix is square
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
     * @param a the array to search
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
