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
            String nombres = sc.nextLine();  
            equipos.PUSH(nombres);
        }
        int R = sc.nextInt();
        for (int i = 0; i < R+1; i++) {
            String respuestas = sc.nextLine();  
            if(respuestas.equals("BIEN")){
                String ganador = equipos.TOP();
                equipos.POP();
                equipos.PUSH(ganador);
            }else{
                if(respuestas.equals("MAL")){
                    equipos.POP();
                }
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
        if (cola[fin] != null) {
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
        String tope = cola[inicio];
        return tope;
    }
    public void ALL(){
        for(int i = 0; i < cola.length; i++){
            System.out.println(cola[i]);
        }
    }

    public void POP() {
        if (cola[inicio] == null) {
            return;
        }
        cola[inicio] = null;
        if ((inicio + 1) == cola.length) {
            inicio = 0;
            return;
        }
        inicio++;
    }
}

