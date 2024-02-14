import java.util.Scanner;

public class Main {
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

    private static void ImprimirVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(i + 1 + "\t");
        }
    }

    // Generar números primos de 1 a max
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

    private static void Criba(int dim, boolean[] esPrimo) {
        int i;
        for (i = 2; i < Math.sqrt(dim) + 1; i++) {
            if (esPrimo[i]) {
                // Eliminar los múltiplos de i
                EliminarMultiplosDeUno(i, dim, esPrimo);
            }
        }
    }

    private static void EliminarMultiplosDeUno(int i, int dim, boolean[] esPrimo) {
        int j;
        for (j = 2 * i; j < dim; j += i)
            esPrimo[j] = false;
    }

    private static int ContarPrimos(int dim, boolean[] esPrimo) {
        int i;
        int cuenta = 0;
        for (i = 0; i < dim; i++) {
            if (esPrimo[i])
                cuenta++;
        }
        return cuenta;
    }

    private static int[] RellenarDePrimos(int cuenta, int dim, boolean[] esPrimo) {
        int j;
        int i;
        int[] primos = new int[cuenta];
        for (i = 0, j = 0; i < dim; i++) {
            if (esPrimo[i])
                primos[j++] = i;
        }
        return primos;
    }
}