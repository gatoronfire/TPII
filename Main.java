import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int cantidad = sc.nextInt();
        sc.nextLine();
        Pila mipila = new Pila(cantidad);
        for(int i =0; i < cantidad; i++){
            String N = sc.nextLine();
            if(N.contains("POP")){
                mipila.POP();
            }
            if(N.contains("PUSH")){
                String valorStr = N.substring(5).trim();
                mipila.PUSH(Integer.parseInt(valorStr));
            }
            if(N.contains("MAX")){
                mipila.MAX();
            }
            if(N.contains("SIZE")){
                mipila.SIZE();
            }
            if(N.contains("TOP")){
                mipila.TOP();
            }
            if(N.contains("EMPTY")){
                mipila.EMPTY();
            }
        }

        sc.close();
    }
}


class Pila {
        private int[] elementos;
        private int top;
        private int cantidad;
        private int[] max;
        private int[] neg;
        
        public Pila(int cantidad){
            this.cantidad = cantidad;
            this.elementos = new int[cantidad];
            this.top = -1;
            this.max = new int [cantidad];
            this.neg = new int [cantidad];
        }

        public void PUSH(int x){
            top ++;
            elementos[top] = x;
            if(top ==0){
                max[top] =x;
            }else{
                if( x > max[top -1]){
                    max[top] = x;
                }else{
                    max[top] = max[top - 1];
                }
            }
            
        }

        public void TOP(){
            System.out.println(elementos[top]);
        }
        public void EMPTY(){
            if(top > -1){
                System.out.println(0);
            }else{
                System.out.println(1);
            }
        }
        public void SIZE(){
            if(top == -1){
                System.out.println(0);
            }else{
System.out.println(top + 1);
            }
            
        }
        public void MAX(){
            System.out.println(max[top]);
        }
        public void POP(){
            top--;
        }
        public void ALL(){
            for(int i =0; i<=top; i++){
                System.out.println(elementos[i]);
            }
        }
  
    }
