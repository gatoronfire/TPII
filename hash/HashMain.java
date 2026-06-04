package hash;
import java.util.Scanner;

public class HashMain {
    // se usa para guardar el largo de la tabla
    static int largo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Acá vamos a llamar a los otros hashes
        String start = sc.nextLine();
        String[] datitos = start.split(" ");
        int N = Integer.parseInt(datitos[0]);
        int M = Integer.parseInt(datitos[1]);
        // contador de palabras encontradas en la tabla hash
        int total = 0;

        int tamanioTabla = 131071;
        String[] tablaHash = new String[tamanioTabla];
        largo = tamanioTabla;
        int palabras = N;

        // loop para cargar las palabras a la tabla
        for (int i = 0; i < N; i++) {
            // leo la palabra que me pasaron
            String palabra = sc.nextLine();
            // busco la siguiente posicion vacia en la tabla
            //int pos = buscarPosicion(tablaHash, palabra);
            int pos = buscarPosicionCuadratica(tablaHash, palabra);
            // si la posicion de pos esta vacia
            if (tablaHash[pos] == null) {
                // guardo la palabra
                tablaHash[pos] = palabra;
            } else {
                // si esta repetida me devuelve la posicion donde esta, por ende no es null
                palabras--;
            }
        }
        //loop para buscar las palabras
        for (int i = 0; i < M; i++) {
            // leo la palabra que me pasaron
            String palabra = sc.nextLine();
            // busco la posicion
            int pos = buscarPosicionCuadratica(tablaHash, palabra);
            // si la posicion no esta vacia (porque encontre la misma palabra)
            if (tablaHash[pos] != null) {
                // aumento el total
                total++;
            }
        }
        // imprimo la cantidad de palabras encontradas
        System.out.println(palabras + " " + total);
        sc.close();
    }

    // funcion de hashing del powerpoint
    public static int hashing(String palabra) {
        long hash = 0;
        int p = 151;

        for (int i = 0; i < palabra.length(); i++) {
            hash = hash * p + palabra.charAt(i);
        }

        return (int) (Math.abs(hash) % largo);
    }

    public static int buscarPosicion(String[] tabla, String palabra) {
        //busco la primera posicion donde iria la palabra
        int index = hashing(palabra);
        // si la posicion del primer hashing esta ocupada y la palabra no esta en esa posicion
        // no es la misma que me pasaron
        //entonces si la posicion esta libre o si la palabra existe en esa posicion devuelvo el index
        while (tabla[index] != null && !tabla[index].equals(palabra)) {
            // cargo la siguiente posicion de index (modulo por el largo de la tabla para empezar de 0 si me paso)
            index = (index + 1) % largo;
        }

        // tabla llena
        return index;
    }

    public static Integer hashCuadratico(String[] tabla, int hashInicial) {
        int c1 = 1; // constante 1 para el calculo cuadratico
        int c2 = 1; // constante 2 para el calculo cuadratico
        int i = 0;
        int indexCuadratico = (hashInicial + c1 * i + (c2 * (i * i))) % largo;
        // h(k,i) = (h'(k) + c1*i + c2 *i^2) % m
        // h(k,i) es la funcion hash original
        // i son los intentos, i = (0;m-1)
        // basicamente es la posicion inicial + constante1 * intento + constante 2 *
        // intento ^2
        //busco indices mientras no me salga de la tabla y mientras la posicion este ocupada
        while (i < (largo - 1) && tabla[indexCuadratico] != null) {
            //voy cambiando el indice 
            i++;
            indexCuadratico = (hashInicial + c1 * i + (c2 * (i * i))) % largo;
            //si la tabla en ese indice esta vacia devuelvo el indice
            if (tabla[indexCuadratico] == null) {
                return indexCuadratico;
            }
        }
        return null;
    }


    public static int buscarPosicionCuadratica(String[] tabla, String palabra) {
        int hashInicial = hashing(palabra);
        int index = hashInicial;
        //variables para modificar el indice usando sondeo cuadratico
        int c1 = 1; 
        int c2 = 1; 
        int i = 0;
        //loopeo siempre que la posicion no sea null y la palabra no este repetida
        while (tabla[index] != null && !tabla[index].equals(palabra)) {
            i++;
            index = (index + c1 * i + (c2 * (i * i))) % largo;
        }
        //en caso de que la tabla este llena devuelvo null
        if (i >= largo) {
            return largo + 1;
        }
        return index;

    }
}
