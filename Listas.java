import java.util.Scanner;

public class Listas {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        sc.close();
    }
}

class Nodo{
    int dato;
    Nodo ant;
    Nodo sig;

    public Nodo(int N, Nodo ant, Nodo sig){
        this.dato = N;
        this.ant = ant;
        this.sig = sig;
    }
}
class Lista{
    Nodo primero;
    Nodo cursor;
    Nodo anterior;

    public Lista(){
        this.primero = null;
        this.cursor = null;
        this.anterior = null;
    }

    //insertar en la posicion siguiente  del cursor 
    public void INSERT ( int dato){
        //si la lista tiene algo
        if (this.primero != null) {
             //hay un nodo adelante?
            if (this.cursor.sig){
                //creando el nodo siguiente que va a apuntar al siguiente del cursor
                Nodo siguienteNodo = this.cursor.sig;
                //creo el nuevo nodo, le meto el dato el cursor y apunto al siguiente
                Nodo nuevo_Nodo = new Nodo(dato, this.cursor, siguienteNodo);
                //el siguiente del cursor pasa a ser el nuevo nodo
                this.cursor.sig = nuevo_Nodo;
                siguienteNodo.ant = nuevo_Nodo;
                
            }else{
            //en caso de que este en al final de la lista
            Nodo nuevo_Nodo = new Nodo(dato,this.cursor,null);
                
            }
        }else{
            //en caso de que la lista este vacia
            Nodo nuevo_Nodo = new Nodo(dato,null,null);
            this.cursor = nuevo_Nodo;
            this.primero = nuevo_Nodo;

        }
           
    }
    //En caso de que agregue un elemento al principio de la lista
    public void PUSH_FRONT(int dato){
        //el nodo siguiente pasa a ser el primero de la lista
        Nodo siguienteNodo = this.primero;
        //creo el nuevo nodo que va a pasar a ser el primero 
        Nodo nuevo_nodo = new Nodo(dato, null, siguienteNodo);
        //el primero pasa a ser el nuevo nodo
        this.primero = nuevo_nodo;
        siguienteNodo.ant = nuevo_nodo;
    }
    public void PUSH_BACK(int dato){
        //crear un nuevo cursor para guardar el actual
        Nodo cursorAux = this.cursor;
        //mientras el cursor tenga un nodo siguiente, el cursor se va a ir moviendo hasta llegar al final de la lista
        while (cursorAux.sig != null) {
            cursorAux = cursorAux.sig;
        };
        //creo el nuevo nodo que va a pasar a ser el ultimo
        Nodo nuevo_nodo = new Nodo(dato, cursorAux, null);
        //el siguiente del cursorAux pasa a ser el nuevo nodo
        cursorAux.sig = nuevo_nodo;

    }
    //Eliminar el primer elemento con valor x y colocar el cursor en el siguiente al eliminado
    public void DELETE(int x){
        //creo un nuevo cursor para guardar el actual
        Nodo cursorAux = this.cursor;
        //Recorrer la lista hasta llegar al final de la lista o encontrar el elemento a eliminar
        while (cursorAux.sig != null) {
            //si el siguiente coincide con el valor a eliminar. 
            if (cursorAux.sig.dato == x) {
                //apunto al siguiente del siguiente del cursorAUX
                cursorAux.sig = cursorAux.sig.sig;
            }
            //muevo el cursorAux al siguiente nodo
            cursorAux = cursorAux.sig;
        };
    }

    
}