import java.util.Scanner;

public class Abroles {
    public static String listadoFinal = "";
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
        preorden(nodos[0]);
        printAndReset();
        inorden(nodos[0]);
        printAndReset();
        postorden(nodos[0]);
        printAndReset();
        pornivel(nodos[0], nodos.length);
        printAndReset();
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
    //imprime los valores sin espacio 
    //reinicia el acumulador de datos 
    public static void printAndReset(){
        System.out.println(listadoFinal.trim());
        listadoFinal = "";
    }
    //FUNCION AGREGAR HIJO(los menores a la izquierda, los mayores a la derecha)
    //CONCEPTUALMENTE DEBO : 
    // buscar donde debe ir el hijo dependiendo del valor
    // mover los mayores una posicion a la derecha
    // insertar el nuevo hijo 
    public static void agregarHijo(Nodo padre, Nodo hijo){
        //encontrar la cantidad de hijos, mientras no me salga del arreglo y haya hijos
        int cantidad = 0;
        while(cantidad < padre.hijos.length &&
             padre.hijos[cantidad] != null){
            cantidad++;
        }
        //chequear que haya espacio
        if(cantidad>= padre.hijos.length){
            System.out.println("No se puede agregar mas hijos");
            return;
        }
        //encontrar donde va a ir el hijo 
        int posicion = 0;
            //mientras el dato del actual sea menor al q quiero insertar 
        while(posicion < cantidad && padre.hijos[posicion].dato < hijo.dato){
            posicion++;
        } 
        //mover los hijos a la derecha empezando por atras
        for(int i = cantidad; i > posicion; i--){
            //guarda en el actual lo que tenia el anterior
            padre.hijos[i] = padre.hijos[i-1];
        }  
        //insertar el nuevo hijo
        padre.hijos[posicion] = hijo;
        //le asigna el padre al hijo
        hijo.padre = padre;
    }
    //Preorden: visitar el nodo actual, luego recorrer los hijos de izquierda a derecha
    public static void preorden(Nodo nodo){
    // caso base,donde la funcion corta
    if(nodo == null){
        return;
    }
    // visitar el nodo actual
    listadoFinal += " " + nodo.dato;
    // recorrer hijos
    for(int i = 0; i < nodo.hijos.length; i++){
        if(nodo.hijos[i] != null){
            //la funcion se llama a si misma para recorrer el hijo
            preorden(nodo.hijos[i]);
            }
        }
    }
    //Postorden: recorrer los hijos de izquierda a derecha, luego visitar el nodo actual
    public static void postorden(Nodo nodo){
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
        listadoFinal += " " + nodo.dato;
    }
    //Por nivel: logica FIFO, por ende debemos usar una cola 
    public static void pornivel(Nodo nodo , int N){
        //creo la cola
        Cola1 cola = new Cola1(N);
        //encolar la raiz
        cola.encolar(nodo);
        //mientras la cola no este vacia
        while(!cola.estaVacia()){
            //desencolo el primero
            Nodo actual = cola.desencolar();
            //visito el nodo actual 
            listadoFinal += actual.dato + " ";
            //encolo sus hijos
            for(int i = 0; i < actual.hijos.length; i++){
                if(actual.hijos[i] != null){
                    cola.encolar(actual.hijos[i]);
                }
            }  
        }
    }  

    // recorro el primero subarbol, luego la raiz, luego el resto de los subarboles de izquierda a derecha 
    public static void inorden(Nodo nodo){
        //caso base
        if(nodo == null){
            return;
        }
        //encontrar la cantidad de hijos totales
        int cantidad = 0;
        while(cantidad < nodo.hijos.length && nodo.hijos[cantidad] != null){
            cantidad++;
        }
        //recorrer el primer subarbol
        if(cantidad > 0){
            inorden(nodo.hijos[0]);
        }
        //visitar la raiz
        listadoFinal += " " + nodo.dato;
        //recorrer el resto de los subarboles 
        for(int i = 1; i < cantidad; i++){
            inorden(nodo.hijos[i]);
        }
    }    

}

class Nodo {
    Nodo[] hijos;
    int dato;
    Nodo padre;

    public Nodo(int Nodos, int dato, Nodo padre) {
        this.hijos = new Nodo[Nodos];
        this.dato = dato;
        this.padre = padre;
    }
}

class Cola1 {
     Nodo[] datos;
     int inicio; 
     int fin;

    public Cola1(int N) {
        this.datos = new Nodo[N*2];
        this.inicio = 0;
        this.fin = 0;
    }
    public void encolar(Nodo nodo){
        datos[fin] = nodo;
        fin++;
    }
    public Nodo desencolar(){
        Nodo aux = datos[inicio];
        inicio++;
        return aux;
    }
    public boolean estaVacia(){
        return inicio == fin;
    }
    
}
