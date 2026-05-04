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
                l.TOP();
            }
            if (operacion.contains("MOVE")) {
                l.MOVE();
            }
            if (operacion.contains("END")) {
                l.END();
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
            // hay un nodo adelante?
            if (cursor.sig != null) {
                // creando el nodo siguiente que va a apuntar al siguiente del cursor
                Nodo siguienteNodo = cursor.sig;
                // creo el nuevo nodo, le meto el dato el cursor y apunto al siguiente
                Nodo nuevo_Nodo = new Nodo(dato, cursor, siguienteNodo);
                // el siguiente del cursor pasa a ser el nuevo nodo
                cursor.sig = nuevo_Nodo;
                siguienteNodo.ant = nuevo_Nodo;

            } else {
                // en caso de que este en al final de la lista
                Nodo nuevo_Nodo = new Nodo(dato, cursor, null);
                cursor.sig = nuevo_Nodo;
            }
        } else {
            // en caso de que la lista este vacia
            Nodo nuevo_Nodo = new Nodo(dato, null, null);
            primero = nuevo_Nodo;
            cursor = primero;
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
        }
        ;
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
        // Recorrer la lista hasta llegar al final de la lista o encontrar el elemento a
        // eliminar
        while (cursorAux != null) {
            // si el siguiente coincide con el valor a eliminar.
            if (cursorAux.dato == x) {
                // estoy en la mitad de la lista
                if (cursorAux.ant != null && cursorAux.sig != null) {
                    Nodo sig = cursorAux.sig;
                    cursorAux.ant.sig = cursorAux.sig;
                    cursorAux.sig.ant = cursorAux.ant;
                    cursor =sig;
                    return;
                }
                // estoy al inicio de la lista
                if (cursorAux == primero) {
                    POP_FRONT();
                    MOVE();
                    return;
                }
                // estoy al final
                if (cursorAux != primero && cursorAux.sig == null) {
                    POP_BACK();
                    MOVE();
                    return;
                }
            }
            // muevo el cursorAux al siguiente nodo
            cursorAux = cursorAux.sig;
        }
        ;
    }

    // eliminar el último elemento de la lista.
    public void POP_BACK() {
        Nodo cursorAuxiliar = cursor;
        // lista <= 1
        if (cursorAuxiliar == primero && cursorAuxiliar.sig == null) {
            primero = null;
            cursor = null;
            return;
        }
        // lista > 1
        while (cursorAuxiliar.sig != null) {
            cursorAuxiliar = cursorAuxiliar.sig;
        }
        cursorAuxiliar.ant.sig = null;
        if (cursorAuxiliar == cursor) {
            cursor = cursorAuxiliar.ant;
        }
    }

    // eliminar el primer elemento de la lista.
    public void POP_FRONT() {
        if (primero != null) {
            if (primero.sig != null) {
                primero.sig.ant = null;
                if (cursor == primero) {
                    cursor = primero.sig;
                }
                primero = primero.sig;
                return;
            } else {
                primero = null;
                cursor = null;
                return;
            }
        } else {
            return;
        }

    }

    // eliminar el elemento al cual apunta el cursor y avanzar una posición el
    // cursor.
    public void ERASE() {
        if (cursor.ant != null && cursor.sig != null) {
            Nodo siguiente = cursor.sig;
            cursor.ant.sig = cursor.sig;
            cursor.sig.ant = cursor.ant;
            cursor = siguiente;
            return;
        }
        if(cursor == primero){
            POP_FRONT();
            return;
        }
        if(cursor.sig == null){
            POP_BACK();
            return;
        }
    }

    // mover el cursor al primer elemento de la lista.
    public void TOP() {
        cursor = primero;
    }

    // avanzar una posición el cursor.
    public void MOVE() {
        cursor = cursor.sig;
    }

    // indicar si el cursor está posicionado más allá del último elemento de la
    // lista o no.
    public void END() {
        if (cursor.ant != null && cursor == null) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
        return;
    }

    // imprimir el valor correspondiente a la posición actual del cursor.
    public void PRINT() {
        System.out.println(cursor.dato);
    }

    // imprimir todos los elementos de la lista.
    public void PRINT_ALL() {
        // se hace un cursor auxiliar para no tocar el original y tambien imprimir el
        // primero
        String todos = "";
        Nodo cursorAuxiliar = primero;
        while (cursorAuxiliar != null) {
            if(cursorAuxiliar == primero){
                todos = todos + cursorAuxiliar.dato;
            }else{
                todos = todos + " " + cursorAuxiliar.dato;
            }
            
            cursorAuxiliar = cursorAuxiliar.sig;
        }
        System.out.println(todos);
    }
}