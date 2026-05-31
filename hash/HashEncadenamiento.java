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
        int total = 0;
        //tabla hash con encadenamiento 
        ArrayList<String>[] tablaHash = new ArrayList[N];
        //creo un array list para cada index
        for (int i = 0; i < N; i++) {
            tablaHash[i] = new ArrayList<String>();
        }
        //guardo palabras
        for (int i = 0; i < N; i++) {
            String palabra = sc.nextLine();
            int index = hashing(palabra);
            tablaHash[index].add(palabra);
        }
        //busco palabras
        for(int i =0 ; i< M;i++){
            String palabra = sc.nextLine();
            int index = hashing(palabra);
            if(tablaHash[index].contains(palabra)){
                total++;
            }
        }
        System.out.println(total);
        sc.close();
    }
    // funcion de hashing del powerpoint
    public static int hashing(String palabra) {
        int p = 151;
        long result = 0;
        long potencia = 1;

        for (int i = 0; i < palabra.length(); i++) {

            result = (result + palabra.charAt(i) * potencia) % largo;

            potencia = (potencia * p) % largo;
        }

        return (int) result;
    }

}
