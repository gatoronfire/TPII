import java.util.Scanner;
public class MaximoPila {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //operaciones a realizar
        int N = sc.nextInt();
        sc.nextLine();
        //crear la pila 
        int [] pila = new int[N];
        int tope = -1;
        //recorrer la pila N veces
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
        if (operacion.equals("MAX")) {
            //verificar que la pila no este vacia
            if (tope == -1){
                System.err.println("La pila esta vacia");
            }else{
                int max = pila[0];
                for(int j = 1; j<=tope; j++){
                    if (pila[j]>max) {
                        max=pila[j];
                        
                    }
                }
                System.err.println(max);
            }
            
        }
    }
   sc.close();
    }
    public static int push (int[] pila, int tope, int x){
        tope = tope +1;
        pila[tope]=x;
        return tope;

    }
    public static int pop (int tope){
        tope = tope -1;
        return tope;

    }
    
}
