import java.util.Scanner;
public class Pila {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // cantidad de operaciones a realizar 
        int N = sc.nextInt();
        sc.nextLine();
        // crear la pila(arreglo)
        //la cantidad de elementos depende de la cantidad de operaciones
        int [] pila = new int[N];
        int tope = -1;
        for (int i = 0; i<N ; i++){
            String operacion = sc.next();
        //solo las funciones push y pop me modifican el tope
        if (operacion.equals("PUSH")) {
            //pedirle al usuario el valor y modificar el tope
            int x = sc.nextInt();
            tope = push(pila, tope, x);            
        }
        if (operacion.equals("POP")) {
            tope = pop(tope);
            
        }
        // top, size y empty solo imprimen 
        if (operacion.equals("TOP")) {
            System.out.println(top(pila, tope));
            
        }
        if (operacion.equals("SIZE")){
            System.out.println(size(tope));
        }
        if (operacion.equals("EMPTY")) {
          System.out.println(empty(tope));
            
        }
        }

        sc.close();
    }
    //crear las funciones(operaciones)
    public static int push (int[] pila, int tope, int x){
        tope = tope +1;
        pila[tope]=x;
        return tope;

    }
    public static int empty (int tope){
        if (tope == -1) {
            return 1;
        }else {
            return 0;
        }

    }
    public static int pop (int tope){
        tope = tope -1;
        return tope;

    }
    public static int top (int[] pila ,int tope){
        return pila[tope];

    }
    public static int size (int tope){
        return tope +1;

    }
}