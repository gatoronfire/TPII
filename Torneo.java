import java.util. Scanner;
public class Torneo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // cantidad de equipos
        int N = sc.nextInt();
        sc.nextLine();
        //guardar los equipos en el arreglo al principio
        Zola equipos = new Zola(N);

        for (int i = 0; i < N; i++) {
            String equipo = sc.nextLine(); 
            // lo guardo dentro del arreglo
            equipos.PUSH(equipo);
        }
        int R = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < R; i++) {
            String respuestas = sc.nextLine();
            if(respuestas.equals("BIEN")){
                equipos.PUSH(equipos.TOP());
            } 
            if(respuestas.equals("MAL")){
                equipos.POP();
            }         
        }
        System.out.println(equipos.TOP());
    }
}
class Zola {
    private String[] cola;
    private int inicio;
    private int fin;

    public Zola(int N) {
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
            return;
        } else {
            inicio++;
        }
    }
}

