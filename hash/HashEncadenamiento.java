package hash;
import java.util.Scanner;
//usamos un array list porque no hay que fijarle tamaño a los arreglos
//el arreglo crece dependiento las palabras que vayan a ese index
import java.util.ArrayList;
//Hash encadenamiento: en el index puede guardar varias palabras 
public class HashEncadenamiento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start = sc.nextLine();
        String[] datitos = start.split(" ");
        int N = Integer.parseInt(datitos[0]);
        int M = Integer.parseInt(datitos[1]);
        int palabrasEncontradas = 0;
        int palabrasGuardadas = 0;
        long H = 0;
        int B = 911382323;
        int MOD = 1000000007;
        int ans = 0;
        //tabla hash con encadenamiento 
        ArrayList<String>[] tablaHash = new ArrayList[N];
        //creo un array list para cada index
        for (int i = 0; i < N; i++) {
            tablaHash[i] = new ArrayList<String>();
        }
        //guardo palabras
        long inicioInsercion = System.nanoTime();
        for (int i = 0; i < N; i++) {
            String palabra = sc.nextLine();
            int index = buscarPosicionEncadenamiento(tablaHash, palabra, N);
            if (index != -1) {
                tablaHash[index].add(palabra);
                palabrasGuardadas++;
            }
        }
        long finInser = System.nanoTime();
        long inicioBusq = System.nanoTime();
        //busco palabras
        for(int i =0 ; i< M;i++){
            String palabra = sc.nextLine();
            int index = hashing(palabra, tablaHash.length);
            if(tablaHash[index].contains(palabra)){
                palabrasEncontradas++;
                ans = 1;
            }else{ans = 0;}
            H = (H * B + (ans + 1)) % MOD;
        }
        long finBusq = System.nanoTime();
        long tiempoInser = finInser - inicioInsercion;
        long tiempoBusqueda = finBusq - inicioBusq;
        System.out.println("tiempo insercion: " + tiempoInser);
        System.out.println("tiempo busqueda: " + tiempoBusqueda);
        System.out.println("elementos guardados: " + palabrasGuardadas);
        sc.close();
    }
  // funcion de hashing del powerpoint
    public static int hashing(String palabra, int largo) {
        long hash = 0;
        int p = 151;

        for (int i = 0; i < palabra.length(); i++) {
            hash = hash * p + palabra.charAt(i);
        }

        return (int) (Math.abs(hash) % largo);
    }
    public static int buscarPosicionEncadenamiento(ArrayList<String>[] tablaHash, String palabra, int N) {
        int index = hashing(palabra, tablaHash.length);
        //si la palabra ya esta en la lista, devuelvo -1 para indicar que no existe
        if (tablaHash[index].contains(palabra)) {
            return -1;
        }
        //sino devuelvo el index donde se puede insertar la palabra
        return index;
    }

}
