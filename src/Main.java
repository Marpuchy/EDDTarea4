import java.util.Scanner;

/**
 * Al introducir un número, el programa crea una lista de todos los primos desde el 0 hasta ese número
 *
 * @author Marc Ibáñez Puchol
 * @version 1.0
 * @since 14/02/2024
 */

public class Main {

    /**
     * Este es el método de la clase main
     *
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int dato = teclado.nextInt();
        //inicializamos correctamente el vector
        //int vector[] = new int[dato]; incorrecto
        int[] vector = new int[dato]; //correcto
        System.out.println("\nVector inicial hasta : " + dato);

        //Extraemos el metodo repetido para imprimir el vector
        ImprimirVector(vector);

        vector = GenerarPrimos(dato);
        System.out.println("\nVector de primos hasta: " + dato);

        ImprimirVector(vector);

    }

    /**
     * Este es el metodo ImprimirVector
     *
     * @param vector array de int que representa el vector que debe recorrer e imprimir
     */

    public static void ImprimirVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(i + 1 + "\t");
        }
    }

    // Generar números primos de 1 a max

    /**
     * Este es el metodo GenerarPrimos
     *
     * @param max valor que corresponde al valor máximo al que debe llegar el array
     * @return <p>Array de int con los primos generados durante el método</p>
     */
    public static int[] GenerarPrimos(int max) {
        // int i, j; incorrecto, una sola declaración por línea
        // correcto
        int i;
        int j;

        if (max >= 2) {

            // Declaraciones
            //Cambiamos el nombre del tamaño del array llamado "dim" a tamañoArray para que sea más entendible
            int tamañoArray = max + 1; // Tamaño del array
            boolean[] esPrimo = new boolean[tamañoArray];

            // Inicializar el array
            for (i = 0; i < tamañoArray; i++)
                esPrimo[i] = true;

            // Eliminar el 0 y el 1, que no son primos
            esPrimo[0] = esPrimo[1] = false;

            // Criba
            Criba(tamañoArray, esPrimo);

            // ¿Cuántos primos hay?
            int cuenta = ContarPrimos(tamañoArray, esPrimo);

            // Rellenar el vector de números primos
            return RellenarDePrimos(cuenta, tamañoArray, esPrimo);

        } else { // max < 2
            return new int[0];
            // Vector vacío
        }
    }

    /**
     * Este es el metodo Criba
     *
     * @param tamañoMaximo valor que corresponde al valor máximo al que debe llegar el array
     * @param esPrimo      booleano que corresponde a si el número es primo o no
     */
    public static void Criba(int tamañoMaximo, boolean[] esPrimo) {
        int i;
        for (i = 2; i < Math.sqrt(tamañoMaximo) + 1; i++) {
            if (esPrimo[i]) {
                // Eliminar los múltiplos de i
                EliminarMultiplosDeUno(i, tamañoMaximo, esPrimo);
            }
        }
    }

    /**
     * Este es el metodo EliminarMultiplosDeUno
     *
     * @param i            variable contador
     * @param tamañoMaximo valor que corresponde al valor máximo al que debe llegar el array
     * @param esPrimo      booleano que corresponde a si el número es primo o no
     */
    public static void EliminarMultiplosDeUno(int i, int tamañoMaximo, boolean[] esPrimo) {
        int j;
        for (j = 2 * i; j < tamañoMaximo; j += i)
            esPrimo[j] = false;
    }

    /**
     * Este es el metodo ContarPrimos
     *
     * @param tamañoMaximo valor que corresponde al valor máximo al que debe llegar el array
     * @param esPrimo      booleano que corresponde a si el número es primo o no
     * @return <p>valor de el número de primos contados</p>
     */
    public static int ContarPrimos(int tamañoMaximo, boolean[] esPrimo) {
        int i;
        int cuenta = 0;
        for (i = 0; i < tamañoMaximo; i++) {
            if (esPrimo[i])
                cuenta++;
        }
        return cuenta;
    }

    /**
     * Este es el metodo RellenarDePrimos
     *
     * @param cuenta valor que corresponde al número de primos que hay
     * @param tamañoMaximo valor que corresponde al valor máximo al que debe llegar el array
     * @param esPrimo      booleano que corresponde a si el número es primo o no
     * @return <p>Array de int ya rellenado con los primos correspondientes</p>
     */
    public static int[] RellenarDePrimos(int cuenta, int tamañoMaximo, boolean[] esPrimo) {
        int j;
        int i;
        int[] primos = new int[cuenta];
        for (i = 0, j = 0; i < tamañoMaximo; i++) {
            if (esPrimo[i])
                primos[j++] = i;
        }
        return primos;
    }
}