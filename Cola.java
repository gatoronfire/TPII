import java.util.Scanner;
// TAD COLA -- LOGICA FIFO
public class Cola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        cola c = new cola(N); 



        // loop para recorrer el arreglo en funcion de N 
        for (int i = 0; i < N; i++) {
            String operacion = sc.nextLine();
            // debe agregar un elemento al FINAL de la cola 
            if (operacion.contains("PUSH")) {
                
            }
            // debe eliminar un elemento del INICIO de la cola
            if (operacion.contains("POP")) {
                
            }
            //debe imprimir la cantidad de elementos de la cola
            if (operacion.contains("SIZE")) {
                System.out.println(c.SIZE());
                
            }
            //debe indicar si la cola esta vacia o no 
            if (operacion.contains("EMPTY")) {
                System.out.println(c.EMPTY());
            }
            // debe mostrar el elemento en el tope de la cola
            if (operacion.contains("TOP")) {
                System.out.println(c.TOP());
            }
            
        }
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
            return (cola[cabeza]);
        }
        public Boolean EMPTY(){

        }
        public Integer SIZE(){
            return(size);
        }
        public void POP(){
        }
    }