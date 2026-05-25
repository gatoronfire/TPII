import java.util.Scanner;
import java.util.ArrayList;

public class Abroles {
    public static StringBuilder listadoFinal = new StringBuilder();
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
            Nodo nuevo = new Nodo(i+1, null);
            nodos[i] = nuevo;
        }

        for(int i= 0;i < N-1; i++ ){
            //agregar el hijo con los paramtros del nodo padre y el nodo hijo
            agregarHijo(nodos[padres[i]-1], nodos[i+1]);
        }
        preorden(nodos[0]);
        printAndReset();
        inorden(nodos[0]);
        printAndReset();
        postorden(nodos[0]);
        printAndReset();
        pornivel(nodos[0]);
        printAndReset();
    }
    //funcion para ver cada nodo y su hijo
    public void verhijos(Nodo[] nodos){
        for (int i = 0; i < nodos.length; i++) {
            System.out.println("Nodo " + (i+1) + ":");
            for (int j = 0; j < nodos[i].hijos.size(); j++) {
                if(nodos[i].hijos.get(j) != null){
                    System.out.println(nodos[i].hijos.get(j).dato);
                }
                
            }
        }
    }
    //imprime los valores sin espacio 
    //reinicia el acumulador de datos 
    public static void printAndReset(){
    System.out.println(listadoFinal.toString().trim());
    listadoFinal = new StringBuilder();  // reiniciás igual que antes
    }
    //FUNCION AGREGAR HIJO(los menores a la izquierda, los mayores a la derecha)
    //CONCEPTUALMENTE DEBO : 
    // buscar donde debe ir el hijo dependiendo del valor
    // mover los mayores una posicion a la derecha
    // insertar el nuevo hijo 
    public static void agregarHijo(Nodo padre, Nodo hijo){
    int posicion = 0;
    while(posicion < padre.hijos.size() && padre.hijos.get(posicion).dato < hijo.dato){
        posicion++;
    }
    padre.hijos.add(posicion, hijo);  // ArrayList hace el "mover a la derecha" solo
    hijo.padre = padre;
}
    //Preorden: visitar el nodo actual, luego recorrer los hijos de izquierda a derecha
    public static void preorden(Nodo nodo){
    if(nodo == null) return;
    
    listadoFinal.append(" ").append(nodo.dato);
    
    for(int i = 0; i < nodo.hijos.size(); i++){
        preorden(nodo.hijos.get(i));
    }
}
    //Postorden: recorrer los hijos de izquierda a derecha, luego visitar el nodo actual
    public static void postorden(Nodo nodo){
    if(nodo == null) return;
    
    for(int i = 0; i < nodo.hijos.size(); i++){
        postorden(nodo.hijos.get(i));
    }
    
    listadoFinal.append(" ").append(nodo.dato);
}
    //Por nivel: logica FIFO, por ende debemos usar una cola 
    public static void pornivel(Nodo nodo){
    Cola1 cola = new Cola1();
    cola.encolar(nodo);
    
    while(!cola.estaVacia()){
        Nodo actual = cola.desencolar();
        listadoFinal.append(actual.dato).append(" ");
        
        for(int i = 0; i < actual.hijos.size(); i++){
            cola.encolar(actual.hijos.get(i));
        }
    }
}

    // recorro el primero subarbol, luego la raiz, luego el resto de los subarboles de izquierda a derecha 
    public static void inorden(Nodo nodo){
    if(nodo == null) return;
    
    int cantidad = nodo.hijos.size();
    
    // recorrer el primer subarbol
    if(cantidad > 0){
        inorden(nodo.hijos.get(0));
    }
    
    // visitar la raiz
    listadoFinal.append(" ").append(nodo.dato);
    
    // recorrer el resto de los subarboles
    for(int i = 1; i < cantidad; i++){
        inorden(nodo.hijos.get(i));
    }
}   

}

class Nodo {
    ArrayList<Nodo> hijos;
    int dato;
    Nodo padre;

    public Nodo( int dato, Nodo padre) {
        this.hijos = new ArrayList<>();
        this.dato = dato;
        this.padre = padre;
    }
}

class Cola1 {
    ArrayList<Nodo> datos;

    public Cola1() {
        this.datos = new ArrayList<>();
    }
    public void encolar(Nodo nodo){ datos.add(nodo); }
    public Nodo desencolar(){ return datos.remove(0); }
    public boolean estaVacia(){ return datos.isEmpty(); }
}
