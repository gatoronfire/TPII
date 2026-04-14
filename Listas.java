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

    public Lista(Nodo N){
        this.primero = N;
        this.cursor = N;
        this.anterior = null;
    }
}