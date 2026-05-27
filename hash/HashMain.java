package hash;
import java.util.Scanner;
public class HashMain {
    static int largo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Acá vamos a llamar a los otros hashes
        String start = sc.nextLine();
        String[] datitos = start.split(" ");
        int N = Integer.parseInt(datitos[0]);
        // int M = Integer.parseInt(datitos[1]);

        String[] superTablaHashingProMax = new String[N];
        largo = N;

        for(int i =0; i < N; i++){
            //leo la palabra que me pasaron
            String palabra = sc.nextLine();
            //creo el index basado en la palabra
            int index = hashing(palabra);
            //si ese lugar esta ocupado (colision) llamo al hashing que se me canta
            if(superTablaHashingProMax[index] != null){
                colisionadoXPete();
                return;
            }
            //si la posicion esta libre guardo la palabra en esa posicion
            superTablaHashingProMax[index] = palabra;
        }
        sc.close();
    }
    //funcion de hashing del power
    public static int hashing(String palabra){
    int p = 151;
    long result = 0;
    long potencia = 1;

    for(int i = 0; i < palabra.length(); i++){

        result = (result + palabra.charAt(i) * potencia) % largo;

        potencia = (potencia * p) % largo;
    }

    return (int) result;
}
    //esta seria las funciones en caso de colision
    public static void colisionadoXPete(){
        //funcion que deberia tener cada archivo para lidiar con colision
    }
}
