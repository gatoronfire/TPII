import java.util.Scanner;
 
public class Listas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        Lista l = new Lista();
        for (int i = 0; i < N; i++) {
            String operacion = sc.nextLine();
            if (operacion.startsWith("INSERT ")) {
                l.INSERT(Integer.parseInt(operacion.substring(7)));
            } else if (operacion.startsWith("PUSH_BACK ")) {
                l.PUSH_BACK(Integer.parseInt(operacion.substring(10)));
            } else if (operacion.startsWith("PUSH_FRONT ")) {
                l.PUSH_FRONT(Integer.parseInt(operacion.substring(11)));
            } else if (operacion.startsWith("DELETE ")) {
                l.DELETE(Integer.parseInt(operacion.substring(7)));
            } else if (operacion.equals("POP_BACK")) {
                l.POP_BACK();
            } else if (operacion.equals("POP_FRONT")) {
                l.POP_FRONT();
            } else if (operacion.equals("ERASE")) {
                l.ERASE();
            } else if (operacion.equals("TOP")) {
                l.TOP();
            } else if (operacion.equals("MOVE")) {
                l.MOVE();
            } else if (operacion.equals("END")) {
                l.END();
            } else if (operacion.equals("PRINT")) {
                l.PRINT();
            } else if (operacion.equals("PRINT_ALL")) {
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
 
    public Lista() {
        this.primero = null;
        this.cursor = null;
    }
 
    // Insertar en la posición siguiente al cursor
    public void INSERT(int dato) {
        // Si la lista está vacía o cursor es null, insertar al final
        if (primero == null || cursor == null) {
            PUSH_BACK(dato);
            return;
        }
        Nodo nuevo = new Nodo(dato, cursor, cursor.sig);
        if (cursor.sig != null) {
            cursor.sig.ant = nuevo;
        }
        cursor.sig = nuevo;
    }
 
    // Insertar al principio; el cursor no cambia (salvo lista vacía)
    public void PUSH_FRONT(int dato) {
        Nodo nuevo = new Nodo(dato, null, primero);
        if (primero != null) {
            primero.ant = nuevo;
        }
        primero = nuevo;
        // Si la lista estaba vacía, el cursor apunta al único nodo
        if (cursor == null && primero.sig == null) {
            cursor = primero;
        }
    }
 
    // Insertar al final; el cursor no cambia (salvo lista vacía)
    public void PUSH_BACK(int dato) {
        if (primero == null) {
            primero = new Nodo(dato, null, null);
            cursor = primero;
            return;
        }
        Nodo aux = primero;
        while (aux.sig != null) {
            aux = aux.sig;
        }
        Nodo nuevo = new Nodo(dato, aux, null);
        aux.sig = nuevo;
    }
 
    // Eliminar el primer elemento con valor x; cursor queda en el siguiente
    public void DELETE(int x) {
        Nodo aux = primero;
        while (aux != null) {
            if (aux.dato == x) {
                Nodo siguiente = aux.sig;

                // Desenlazar aux
                if (aux.ant != null) {
                    aux.ant.sig = aux.sig;
                } else {
                    // Era el primero
                    primero = aux.sig;
                }
                if (aux.sig != null) {
                    aux.sig.ant = aux.ant;
                }

                cursor = siguiente; // puede ser null si era el último
                return;
            }
            aux = aux.sig;
        }
    }
 
    // Eliminar el último elemento
    public void POP_BACK() {
        if (primero == null) return;

        if (primero.sig == null) {
            // Un solo nodo
            primero = null;
            cursor = null;
            return;
        }

        Nodo aux = primero;
        while (aux.sig != null) {
            aux = aux.sig;
        }
        // aux es el último
        if (cursor == aux) {
            cursor = aux.ant;
        }
        aux.ant.sig = null;
    }
 
    // Eliminar el primer elemento
    public void POP_FRONT() {
        if (primero == null) return;

        if (cursor == primero) {
            cursor = primero.sig;
        }
        if (primero.sig != null) {
            primero.sig.ant = null;
        }
        primero = primero.sig;
    }
 
    // Eliminar el elemento apuntado por el cursor; cursor avanza al siguiente
    public void ERASE() {
        if (cursor == null) return;

        Nodo siguiente = cursor.sig;

        if (cursor.ant != null) {
            cursor.ant.sig = cursor.sig;
        } else {
            primero = cursor.sig;
        }
        if (cursor.sig != null) {
            cursor.sig.ant = cursor.ant;
        }

        cursor = siguiente; // null si era el último (END retornará 1)
    }
 
    public void TOP() {
        cursor = primero;
    }
 
    public void MOVE() {
        if (cursor != null) {
            cursor = cursor.sig;
        }
    }
 
    public void END() {
        System.out.println(cursor == null ? 1 : 0);
    }
 
    public void PRINT() {
        if (cursor != null) {
            System.out.println(cursor.dato);
        }
    }
 
    public void PRINT_ALL() {
        if (primero == null) {
            System.out.println();
            return;
        }
        StringBuilder sb = new StringBuilder();
        Nodo aux = primero;
        while (aux != null) {
            sb.append(aux.dato);
            if (aux.sig != null) sb.append(" ");
            aux = aux.sig;
        }
        System.out.println(sb.toString());
    }
}