package semestralka;

import java.util.Scanner;

/**
 * minimal input value = 3,743392E-23 maximal input value = 3,4028235E38
 */
/**
 * 29. Checks if the system of n-vectors with n-elements is orthogonal and if
 * so, trarnsforms the vectors to normal form.
 *
 * @author Petr Kaštánek
 */
public class KastanekPetrSemestralProject {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice, nInit = 0 , sizeInit = 0, handsInit = 0, pocetInit = 2;
        float[][] matrix = null;
        double [] sizes = null;
        boolean end = false, isMatrix = true, isOrthogonal = true;
        while (!end) {
            choice = menu();
            switch (choice) {
                case 1:
                    snowmen(choice, sizeInit, handsInit, pocetInit);
                    break;
                case 2:
                    semesrtal(nInit, matrix, sizes, isMatrix, isOrthogonal);
                    break;
                case 0:
                    end = true;
                    break;
            }
        }
    }

    private static int menu() {
        System.out.println("*******************");
        System.out.println("*  Vyberte úlohu  *");
        System.out.println("* 1. Vánoční      *");
        System.out.println("* 2. Semestrílní  *");
        System.out.println("* 0. Exit         *");
        System.out.println("*******************");
        int a = sc.nextInt();
        return a;
    }

    private static void semesrtal(int count, float[][] matrix, double[] sizes, boolean isMatrix, boolean isOrthogonal) {
        while (true) {
            //input
            System.out.println("Zadej pocet vektoru: (Vstup <= 0 ukončí tuto část):");
            count = sc.nextInt();
            if (count <= 0) {     //ends the program
                break;
            }
            System.out.println("Zadej vektory: ");
            matrix = createSquareMatrix(count);     //creates the matrix
            sizes = calcSizes(matrix);       //creates array of the vector sizes

            //calc
            if (sizes.length == 1) {        //checks if the user input generates matrix
                isMatrix = false;
            } else {
                isOrthogonal = (!isInArray(sizes, 0) && dotProductsAllZeros(matrix)); //checks if matrix is orthogonal
            }

            //out
            System.out.println();
            if (isMatrix && isOrthogonal) {
                System.out.println();
                printMatrix(matrix);
                System.out.println("System je ortogonalni.");
                System.out.println();
                normalize(matrix, sizes);       //transforms the matrix into normal form
                printMatrix(matrix);
            } else if (isMatrix) {
                System.out.println();
                printMatrix(matrix);
                System.out.println("Systém není ortogonální.");
            } else {
                System.out.println("Není soubor vektorů.");
            }
            System.out.println();
        }
    }

    private static void snowmen(int choice, int size, int hands, int pocet) {
        int[] menu = new int[3];
        boolean endAll = false;
        do {
            next();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    menu = snowmenMenu(size, hands, pocet);
                    size = menu[0];
                    hands = menu[1];
                    pocet = menu[2];
                    fix(size, hands, pocet);
                    boolean end = false;
                    do {
                        hatMenu();
                        int hat = sc.nextInt();

                        switch (hat) {
                            case 1:
                                noHat(pocet);
                                end = true;
                                break;
                            case 2:
                                hat(pocet);
                                end = true;
                                break;
                            case 3:
                                christmasHat(pocet);
                                end = true;
                                break;
                            default:
                                System.out.println("Neplatná volba");
                                break;
                        }
                    } while (!end);

                    head(pocet);
                    balls(size, hands, pocet);
                    break;
                case 2:
                    System.out.println("Veselé listopadové vánoce!");
                    endAll = true;
                    break;
                default:
                    System.out.println("Neplatná volba");
                    break;
            }
        } while (!endAll);
    }

//Methods for semestral project
    /**
     * Creates square matrix with size a*a using user input
     *
     * @param size size of the matrix
     * @return matrix a*a filled with user inputed numbers
     */
    private static float[][] createSquareMatrix(int size) {
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
    private static double[] calcSizes(float[][] arr) {
        double sizeArr[] = new double[arr.length];
        double sum;
        for (int m = 0; m < arr.length; m++) {
            sum = 0;
            for (int n = 0; n < arr.length; n++) {
                sum += Math.pow(arr[m][n], 2);
            }
            sizeArr[m] = Math.sqrt(sum);
        }
        return sizeArr;
    }

    /**
     * Transforms vectors in matrix to normal form by dividing each element by
     * the vector size
     *
     * @param arr matrix to transform
     * @param sizes array of vector sizes
     */
    private static void normalize(float[][] arr, double[] sizes) {
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
    private static boolean dotProductsAllZeros(float[][] array) {
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
    private static boolean isInArray(double[] arr, int a) {
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
    private static void printMatrix(float[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

//Methods for christmas project
    private static void next() {
        System.out.println("*****************************");
        System.out.println("*  Chceš stavět sněhuláky?  *");
        System.out.println("* 1. Ano                    *");
        System.out.println("* 2. Ne                     *");
        System.out.println("*****************************");
    }

    private static int[] snowmenMenu(int size, int hands, int pocet) {
        int[] menu = new int[3];
        System.out.println("Neplatné hodnoty (záporné nebo menší než požadované) se samy upraví");
        System.out.printf("Zadej počet sněhuláků (min. 1, max. 100 - více se nevejde na výstup): ");
        pocet = sc.nextInt();
        System.out.printf("Kolik budou mít sněhuláci koulí (min. 2, max. aby to netrvalo dlouho): ");
        size = sc.nextInt();
        System.out.println("Na kolikáte kouli (ze shora, bez hlavy) budou ruce (alespoň 1, max. počet koulí - 1): ");
        hands = sc.nextInt();
        menu[0] = size;
        menu[1] = hands;
        menu[2] = pocet;
        return menu;
    }

    private static void fix(int size, int hands, int pocet) {
        if (pocet < 0) {
            pocet = Math.abs(pocet);
        }
        if (pocet > 100) {
            pocet = 100;
        }
        if (pocet < 1) {
            pocet = 1;
        }

        if (size < 0) {
            size = Math.abs(size);
        }
        if (size < 2) {
            size = 2;
        }

        if (hands < 0) {
            hands = Math.abs(hands);
        }
        if (hands < 1) {
            hands = 1;
        }
        if (hands > size - 1) {
            hands = size - 1;
        }
    }

    private static void hatMenu() {
        System.out.println("**************************");
        System.out.println("*  Co bude mít na hlavě  *");
        System.out.println("* 1. Nic                 *");
        System.out.println("* 2. Klobouk             *");
        System.out.println("* 3. Vánoční čepici      *");
        System.out.println("**************************");
    }

    private static void hat(int pocet) {
        System.out.printf("Výška klobouku (max. 10): ");
        System.out.println(pocet);
        int hatHeight = sc.nextInt();
        if (hatHeight < 0) {
            hatHeight = Math.abs(hatHeight);
        }
        if (hatHeight > 10) {
            hatHeight = 10;
        }
        if (hatHeight < 1) {
            hatHeight = 1;
        }
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("             ___________             ");
        }
        System.out.printf("\n");
        for (int i = 1; i < hatHeight; i++) {
            for (int j = 1; j <= pocet; j++) {
                System.out.printf("              |       |              ");
            }
            System.out.printf("\n");
        }
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("           \\__|_______|__/           ");
        }
        System.out.printf("\n");
    }

    private static void christmasHat(int pocet) {
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("                    ______           ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("                   /      \\          ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("                  /     _  \\         ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("                 /     /  \\_\\        ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("                /     /   /  \\       ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("             __|_____|__  \\__/       ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("            |___________|            ");
        }
        System.out.printf("\n");
    }

    private static void noHat(int pocet) {
        for (int i = 1; i <= pocet; i++) {
            System.out.printf("              X X X X X              ");
        }
        System.out.printf("\n");
    }

    private static void head(int pocet) {
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("            X           X            ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("          X   _       _   X          ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("        X    |_|     |_|    X        ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("        X                   X        ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("        X         /\\        X        ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("          X    \\_____/    X          ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("            X           X            ");
        }
        System.out.printf("\n");
        for (int j = 1; j <= pocet; j++) {
            System.out.printf("              X X X X X              ");
        }
        System.out.printf("\n");
    }

    public static void balls(int size, int hands, int pocet) {
        for (int i = 2; i <= size; i++) {
            if (i == hands + 1) {
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("    X X     X           X     X X    ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("   X    X X       o       X X     X  ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("    X                           X    ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("      X           o           X      ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("        X                   X        ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("          X       o       X          ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("            X           X            ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("              X X X X X              ");
                }
                System.out.printf("\n");
            } else {
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("            X           X            ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("          X       o       X          ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("        X                   X        ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("        X         O         X        ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("        X                   X        ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("          X       o       X          ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("            X           X            ");
                }
                System.out.printf("\n");
                for (int j = 1; j <= pocet; j++) {
                    System.out.printf("              X X X X X              ");
                }
                System.out.printf("\n");
            }

        }
        System.out.printf("\n");
    }
}
