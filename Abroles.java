import java.util.Scanner;
import java.util.Arrays;

public class Abroles {
    public void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String padresString = sc.nextLine();
        String[] partes = padresString.split(" ");
        int[] padres = new int[(N-1)];
        for(int i= 0;i < partes.length; i++ ){
            padres[i] = Integer.parseInt(partes[i]);
        }
        for(int i= 0;i < (N-1); i++ ){
            padres[i] = Integer.parseInt(partes[i]);
        }

        //crear un arreglo con N nodos
        Nodo[] nodos = new Nodo[N];

        //crear los nodos y agregarlos al arreglo
        for(int i=0; i< N; i++){
            Nodo nuevo = new Nodo(N, i+1, null);
            nodos[i] = nuevo;
        }

        for(int i= 0;i < N-1; i++ ){
            agregarHijo(nodos[padres[i]-1], nodos[i+1]);
        }

    }
    //funcion para ver cada nodo y su hijo
    public void verhijos(Nodo[] nodos){
        for (int i = 0; i < nodos.length; i++) {
            System.out.println("Nodo " + (i+1) + ":");
            for (int j = 0; j < nodos[i].hijos.length; j++) {
                if(nodos[i].hijos[j] != null){
                    System.out.println(nodos[i].hijos[j].dato);
                }
                
            }
        }
    }

    public void agregarHijo(Nodo padre, Nodo hijo){
            for(int i=0; i < padre.hijos.length; i++){
                if(padre.hijos[i] == null){
                    padre.hijos[i] = hijo;
                    hijo.padre = padre;
                    return;
                }
            }
        }

}

class Nodo {
    Nodo[] hijos;
    int dato;
    Nodo padre;

    public Nodo(int Nodos, int dato, Nodo Padre) {
        this.hijos = new Nodo[Nodos];
        this.dato = dato;
        this.padre = padre;
    }
}