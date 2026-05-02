import java.util.Scanner;

public class Listas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        Lista l = new Lista();
        for (int i = 0; i < N; i++) {
            String operacion = sc.nextLine();
            if (operacion.contains("INSERT")) {
                String txt = operacion.replace("INSERT ", "");
                l.INSERT(Integer.parseInt(txt));
            }
            if (operacion.contains("PUSH_BACK")) {
                String txt = operacion.replace("PUSH_BACK ", "");
                l.PUSH_BACK(Integer.parseInt(txt));

            }
            if (operacion.contains("PUSH_FRONT")) {
                String txt = operacion.replace("PUSH_FRONT ", "");
                l.PUSH_FRONT(Integer.parseInt(txt));
            }
            if (operacion.contains("DELETE")) {
                String txt = operacion.replace("DELETE ", "");
                l.DELETE(Integer.parseInt(txt));
            }
            if (operacion.contains("POP_BACK")) {
                l.POP_BACK();
            }
            if (operacion.contains("POP_FRONT")) {
                l.POP_FRONT();
            }
            if (operacion.contains("ERASE")) {
                l.ERASE();
            }
            if (operacion.contains("TOP")) {
                System.out.println(l.TOP());
            }
            if (operacion.contains("MOVE")) {
                l.MOVE();
            }
            if (operacion.contains("END")) {
                System.out.println(l.END());
            }
            if (operacion.equals("PRINT")) {
                l.PRINT();
            }
            if (operacion.equals("PRINT_ALL")) {
                l.PRINT_ALL();
            }

        }
        sc.close();
    }

}

class Nodo {
    int dato;
    Nodo ant;
    Nodo sig;

    public Nodo(int N, Nodo ant, Nodo sig) {
        this.dato = N;
        this.ant = ant;
        this.sig = sig;
    }
}

class Lista {
    Nodo primero;
    Nodo cursor;
    Nodo anterior;

    public Lista() {
        this.primero = null;
        this.cursor = null;
        this.anterior = null;
    }

    // insertar en la posicion siguiente del cursor
    public void INSERT(int dato) {
        // si la lista tiene algo

        if (primero != null) {       
        //que el primero no sea null, no significa que el cursor no pueda ser null, ya que el cursor se puede mover a lo largo de la lista, pero el primero siempre va a ser el mismo
            // si el cursor es null, lo posiciono en el primero 
            if (cursor == null) {
                cursor = primero;
            }
            // hay un nodo adelante?
            if (cursor.sig != null) {
                // creando el nodo siguiente que va a apuntar al siguiente del cursor
                Nodo siguienteNodo = cursor.sig;
                // creo el nuevo nodo, le meto el dato el cursor y apunto al siguiente
                Nodo nuevo_Nodo = new Nodo(dato, cursor, siguienteNodo);
                // el siguiente del cursor pasa a ser el nuevo nodo
                cursor.sig = nuevo_Nodo;
                siguienteNodo.ant = nuevo_Nodo;
                // el nuevo nodo pasa a ser el siguiente del cursor
                cursor = nuevo_Nodo;

            } else {
                // EN CASO DE QUE QUIERA INSERTAR AL FINAL DE LA LISTA.
                Nodo nuevo_Nodo = new Nodo(dato, cursor, null);
                cursor.sig = nuevo_Nodo;
                cursor = nuevo_Nodo;
            }
        } else { // EN CASO DE QUE LA LISTA ESTE VACIA
            Nodo nuevo_Nodo = new Nodo(dato, null, null);
            cursor = nuevo_Nodo;
            primero = nuevo_Nodo;

        }

    }

    // En caso de que agregue un elemento al principio de la lista
    public void PUSH_FRONT(int dato) {
        Nodo nuevo_nodo = new Nodo(dato, null, null);
        if (primero == null) {
            primero = nuevo_nodo;
            cursor = primero;
            return;
        }
        primero.ant = nuevo_nodo;
        nuevo_nodo.sig = primero;
        primero = nuevo_nodo;
        

    }

    public void PUSH_BACK(int dato) {
        // chequea si esta vacia
        if (primero == null) {
            primero = new Nodo(dato, null, null);
            cursor = primero;
            return;
        }

        // mientras el cursor tenga un nodo siguiente, el cursor se va a ir moviendo
        // hasta llegar al final de la lista
        Nodo cursorAux = cursor;
        while (cursorAux.sig != null) {
            cursorAux = cursorAux.sig;
        };
        // creo el nuevo nodo que va a pasar a ser el ultimo
        Nodo nuevo_nodo = new Nodo(dato, cursorAux, null);
        // el siguiente del cursorAux pasa a ser el nuevo nodo
        cursorAux.sig = nuevo_nodo;
    }

    // Eliminar el primer elemento con valor x y colocar el cursor en el siguiente
    // al eliminado
    public void DELETE(int x) {
        // creo un nuevo cursor para guardar el actual
        Nodo cursorAux = primero;
        while (cursorAux.sig != null) {
            if (cursorAux.dato == x) {
                //eliminar el nodo primero
                if (cursorAux.ant == null) {
                    primero = cursorAux.sig;
                    if (primero != null) {
                        primero.ant = null;
                    }
                }
                //eliminar el nodo ultimo
                else if (cursorAux.sig == null) {
                    cursorAux.ant.sig = null;
                }
                //eliminar el nodo del medio
                else {
                cursorAux.ant.sig = cursorAux.sig;
                cursorAux.sig.ant = cursorAux.ant;
                }
            // muevo el cursorAux al siguiente nodo
            cursor = cursorAux.sig;
            return;

        }
        cursorAux = cursorAux.sig;
    }     
}

    // eliminar el último elemento de la lista.
    public void POP_BACK() {
        //chequear si la lista esta vacia
        if (primero == null) {
            return;
        }
        Nodo cursorAuxiliar = this.cursor;
        while (cursorAuxiliar.sig != null) {
            cursorAuxiliar = cursorAuxiliar.sig;
        }
        //chequear si la lista tiene un solo elemento
        if (cursorAuxiliar.ant == null) {
            primero = null;
            cursor = null;
            return;
        }else{
            cursorAuxiliar.ant.sig = null;
        }
        
    }

    // eliminar el primer elemento de la lista.
    public void POP_FRONT() {
        //chequear si la lista esta vacia
        if (primero == null) {
            return;
        }
        //chequear si la lista tiene un solo elemento
        if (primero.sig == null) {
            primero = null;
            cursor = null;
            return;
        }else{ 
            primero = primero.sig;
           primero.sig.ant = null; 
        }
        
    }

    // eliminar el elemento al cual apunta el cursor y avanzar una posición el
    // cursor.
    public void ERASE() {
        //EN CASO DE QUE HAYA UN SOLO NODO
        if (cursor.ant == null && cursor.sig == null) {
            primero = null;
            cursor = null;
            return;
        } //EN CASO DE QUE QUIERA ELIMINAR EL PRIMERO
        else if (cursor.ant == null) {
            primero = cursor.sig;
            primero.ant = null;
            cursor = primero;
        } //EN CASO DE QUE QUIERA ELIMINAR EL ULTIMO
        else if (cursor.sig == null) {
            cursor.ant.sig = null;
            cursor = cursor.ant;
        }//EN CASO DE QUE QUIERA ELIMINAR UN NODO DEL MEDIO
        else {
            cursor.ant.sig = cursor.sig;
            cursor.sig.ant = cursor.ant;
            MOVE();
        }
    }

    // mover el cursor al primer elemento de la lista.
    public Integer TOP() {
        return primero.dato;

    }

    // avanzar una posición el cursor.
    public void MOVE() {
        //no permitir que el cursor salga de la lista
        //irse moviendo adentro de la lista
        if ( cursor != null && cursor.sig != null) {
            cursor = cursor.sig;
        }
    }

    // indicar si el cursor está posicionado más allá del último elemento de la
    // lista o no.
    public boolean END() {
        if (cursor.ant == null && cursor.sig == null) {
            return true;
        }
        return false;
    }

    // imprimir el valor correspondiente a la posición actual del cursor.
    public void PRINT() {
        System.out.println(cursor.dato);
    }

    // imprimir todos los elementos de la lista.
    public void PRINT_ALL() {
        // se hace un cursor auxiliar para no tocar el original y tambien imprimir el
        // primero
        Nodo cursorAuxiliar = primero;
        while (cursorAuxiliar != null) {
            System.out.println(cursorAuxiliar.dato);
            cursorAuxiliar = cursorAuxiliar.sig;
        }
    }
}