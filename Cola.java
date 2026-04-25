import java.util.Scanner;

// TAD COLA -- LOGICA FIFO
public class Cola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        Nola c = new Nola(N);
        int tamaño = 0;

        // loop para recorrer el arreglo en funcion de N
        for (int i = 0; i < N; i++) {
            String operacion = sc.nextLine();
            // debe agregar un elemento al FINAL de la cola
            if (operacion.contains("PUSH")) {
                String txt = operacion.replace("PUSH ", "");
                c.PUSH(txt);
                tamaño++;
            }
            // debe eliminar un elemento del INICIO de la cola
            if (operacion.contains("POP")) {
                c.POP();
                tamaño--;
            }
            // debe imprimir la cantidad de elementos de la cola
            if (operacion.contains("SIZE")) {
                System.out.println(tamaño);
            }
            // debe indicar si la cola esta vacia o no
            if (operacion.contains("EMPTY")) {
                if (tamaño > 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            }
            // debe mostrar el elemento en el tope de la cola
            if (operacion.contains("TOP")) {
                System.out.println(c.TOP());
            }

        }
    }

}

class Nola {
    private String[] cola;
    private int inicio;
    private int fin;

    public Nola(int N) {
        this.cola = new String[N];
        this.inicio = 0;
        this.fin = 0;
    }

    public void PUSH(String txt) {
        // la siguiente posicion esta libre?
        // quiere decir que si cola ya está llena, entonces no hace nada
        if (cola[fin] != null && fin == inicio) {
            return;
        }
        //si no lo agrega 
        cola[fin] = txt;
        //si llego al final de la cola el fin vuelve a ser 0(por la logica de arreglo circular)
        if ((fin + 1) == cola.length) {
            fin = 0;
            return;
        }
        //le va incrementando al fin hasta que se cumplan alguna de esas dos condiciones y se llene la cola 
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
