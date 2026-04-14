import java.util. Scanner;
public class Torneo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // cantidad de equipos
        int N = sc.nextInt();
        sc.nextLine();
        //guardar los equipos en el arreglo al principio
        Cola equipos = new Cola(N);



        for (int i = 0; i < N; i++) {
            String equipos = sc.nextLine();  
        }
        int R = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < R; i++) {
            String respuestas = sc.nextLine();            
        }
    }
}
class Cola {
    private String[] cola;
    private int inicio;
    private int fin;

    public Cola(int N) {
        this.cola = new String[N];
        this.inicio = 0;
        this.fin = 0;
    }

    public void PUSH(String txt) {
        // la siguiente posicion esta libre?
        if (cola[fin] != null && fin == inicio) {
            return;
        }
        cola[fin] = txt;
        // la siguiente posicion existe en el arreglo?
        if ((fin + 1) == cola.length) {
            fin = 0;
            return;
        }
        fin++;
    }

    public String TOP() {
        return (cola[inicio]);
    }

    public void POP() {
        if (cola[inicio] == null) {
            return;
        }
        cola[inicio] = null;
        if ((inicio + 1) == cola.length) {
            inicio = 0;
        } else {
            inicio++;
        }
    }
}

