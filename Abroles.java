import java.util.Scanner;
public class Abroles {
    Scanner sc = new Scanner(System.in);
}
class Nodo{
    Nodo[] hijos;
    int dato;

    public void Nodo(int Nodos, int dato){
        this.hijos = new Nodo[Nodos];
        this.dato = dato;
    }
}