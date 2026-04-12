import java.util.Scanner;

public class Cola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        cola c = new cola(N); 
    }
}

class cola {
    private String[] cola;
    private int cabeza;
    private int fin;
    private int size;

        public cola(int N) {
            this.cola = new String[N];
            this.cabeza = 0;
            this.fin = 0;
            this.size = 0;
        }
        public void PUSH(){
        }
        public String TOP(){
        }
        public Boolean EMPTY(){
        }
        public String SIZE(){
        }
        public void POP(){
            tope = tope -1;
        }
    }