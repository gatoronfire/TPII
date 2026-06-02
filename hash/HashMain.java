package hash;

import java.util.Scanner;

public class HashMain {
    //se usa para guardar el largo de la tabla
    static int largo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Acá vamos a llamar a los otros hashes
        String start = sc.nextLine();
        String[] datitos = start.split(" ");
        int N = Integer.parseInt(datitos[0]);
        int M = Integer.parseInt(datitos[1]);
        //contador de palabras encontradas en la tabla hash
        int total = 0;

        String[] tablaHash = new String[N];
        largo = N;

        //loop para cargar las palabras a la tabla
        for (int i = 0; i < N; i++) {
            // leo la palabra que me pasaron
            String palabra = sc.nextLine();
            // creo el index basado en la palabra
            int index = hashing(palabra);
            // si ese lugar esta ocupado (colision) llamo al hashing que se me canta
            if (tablaHash[index] != null) {
                // asigno un nuevo index usando (en este caso) el hash lienal
                int nuevoHash = hashLineal(tablaHash, index);
                // guardo la palabra en el nuevo hash
                tablaHash[nuevoHash] = palabra;
                // salto al siguiente loop
                continue;
            }
            // si la posicion esta libre guardo la palabra en esa posicion
            tablaHash[index] = palabra;
        }
        //loop para buscar las palabras
        for(int i =0 ; i< M;i++){
            //leo la palabra que me pasaron
            String palabra = sc.nextLine();
            //creo el hash de la palabra
            int index = hashing(palabra);
            //chequeo si esta en la tabla con solo el primer hash
            if(tablaHash[index].equals(palabra)){
                total++;
                continue;
            }
            int nuevoHash = hashLineal(tablaHash, index);
            if(tablaHash[nuevoHash].equals(palabra)){
                total++;
                continue;
            }
        }
        //imprimo la cantidad de palabras encontradas
        System.out.println(total);
        sc.close();
    }

    // funcion de hashing del powerpoint
    public static int hashing(String palabra) {
        int p = 151;
        long result = 0;
        int potencia = 1;

        for (int i = 0; i < palabra.length(); i++) {

            result = (result + palabra.charAt(i)) * i;

            potencia = (potencia * p);
        }
        result = result % largo;
        System.out.println(result);
        return (int) result;
    }


    public static Integer hashLineal(String[] tabla, int hashInicial) {

        // busco desde hashInicial hasta el final
        for (int i = hashInicial; i < tabla.length; i++) {
            if (tabla[i] == null) {
                return i;
            }
        }

        // si no encontre, busco desde el principio
        for (int i = 0; i < hashInicial; i++) {
            if (tabla[i] == null) {
                return i;
            }
        }

        // tabla llena
        return null;
    }

    public static Integer hashCuadratico(String[] tabla, int hashInicial){
        int c1 = 1; //constante 1 para el calculo cuadratico
        int c2 = 1; //constante 2 para el calculo cuadratico
        int i = 0;
        int indexCuadratico = (hashInicial + c1*i + (c2*Math.powExact(i, 2))) % largo;
        //h(k,i) = (h'(k) + c1*i + c2 *i^2) % m
        //h(k,i) es la funcion hash original
        //i son los intentos, i = (0;m-1)
        // basicamente es la posicion inicial + constante1 * intento + constante 2 * intento ^2
        while (i < (largo-1) && tabla[indexCuadratico] != null ) {
            i++;
            indexCuadratico = (hashInicial + c1*i + (c2*Math.powExact(i, 2))) % largo;
            if(tabla[indexCuadratico] == null){
                return indexCuadratico;
            }
        } 
        return null;
    }

    
}
