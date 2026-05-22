import java.util.Scanner;

public class Abroles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        //pedir los numeros enteros separados por espacios 
        String padresString = sc.nextLine();
        //dividir el texto usando espacios y guarda los numeros como un arreglo de strings
        String[] partes = padresString.split(" ");
        int[] padres = new int[(N-1)];


        for(int i= 0;i < partes.length; i++ ){
            //convertir cada string a entero y guardarlo en el arreglo de padres
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
            //agregar el hijo con los paramtros del nodo padre y el nodo hijo
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

    public static void agregarHijo(Nodo padre, Nodo hijo){
        if(padre.hijos[0] == null){
            padre.hijos[0] = hijo;
        }else{
            for(int i=1; i < padre.hijos.length; i++){
                //busca una posicion vacia
                if(padre.hijos[i].dato < hijo.dato){
                    //agrega el hijo
                    for(int j = i; j > 0; j--){
                        
                    }
                    //le asigna el padre al hijo
                }
            }
        }
         hijo.padre = padre;   
    }
    //Preorden: visitar el nodo actual, luego recorrer los hijos de izquierda a derecha
    public void preorden(Nodo nodo){
    // caso base,donde la funcion corta
    if(nodo == null){
        return;
    }
    // visitar el nodo actual
    System.out.println(nodo.dato);
    // recorrer hijos
    for(int i = 0; i < nodo.hijos.length; i++){
        if(nodo.hijos[i] != null){
            //la funcion se llama a si misma para recorrer el hijo
            preorden(nodo.hijos[i]);
            }
        }
    }
    //Postorden: recorrer los hijos de izquierda a derecha, luego visitar el nodo actual
    public void postorden(Nodo nodo){
        // caso base,donde la funcion corta
        if(nodo == null){
            return;
        }
        // recorrer hijos
        for(int i = 0; i < nodo.hijos.length; i++){
            if(nodo.hijos[i] != null){
                //la funcion se llama a si misma para recorrer el hijo
                postorden(nodo.hijos[i]);
                }
            }
        // visitar el nodo actual
        System.out.println(nodo.dato);
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