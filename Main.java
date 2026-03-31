import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //hay un proximo int?
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        //leer el primer int que me dice la cantidad de operaciones
        int cantidad = sc.nextInt();
        //limpiar el buffer del scanner
        sc.nextLine();
        //creo la pila con la cantidad de operaciones en caso que todas sean push
        Pila mipila = new Pila(cantidad);
        //loop por la cantidad de operaciones
        for(int i =0; i < cantidad; i++){
            //leo la linea que tiene formato "operacion-valor"
            String N = sc.nextLine();
            //use if porque no me acordaba como usar switch 
            if(N.contains("POP")){
                mipila.POP();
            }
            if(N.contains("PUSH")){
                //extraigo el valor del push, lo convierto a int y lo paso a la funcion push
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

//clase pila (quien lo iba a decir?)
//la clase tiene las funciones que se usan en las pilas
class Pila {
    //arreglo de los elementos de la pila (datos principales)
        private int[] elementos;
        //tope de la pila
        private int top;
        //tamaño maximo de la pila
        private int cantidad;
        //arreglo para guardar el maximo en cada nivel de la pila, asi no tengo que recorrer la pila cada vez que quiero saber el maximo
        private int[] max;
        //era para ver los numeros negativos de algun ejercicio, no se uso
        private int[] neg;
        
        //establecer el tamaño de la pila y crear los arreglos necesarios
        public Pila(int cantidad){
            this.cantidad = cantidad;
            this.elementos = new int[cantidad];
            this.top = -1;
            this.max = new int [cantidad];
            this.neg = new int [cantidad];
        }
        //funcion push. suma 1 al tope y agrega x en la posicion tope. tambien compara x con el maximo y actualiza el arreglo max
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
        //funcion top para imprimir el elemento en el tope de la pila
        public void TOP(){
            System.out.println(elementos[top]);
        }
        //chequea si la lista esta vacia
        public void EMPTY(){
            if(top > -1){
                System.out.println(0);
            }else{
                System.out.println(1);
            }
        }
        //devuelve el tamaño de la pila (tope+1)
        public void SIZE(){
            if(top == -1){
                System.out.println(0);
            }else{
System.out.println(top + 1);
            }
            
        }
        //imprime el utlimo elemento del arreglo max que guarda el elemento mas grande de la pila normal
        public void MAX(){
            System.out.println(max[top]);
        }
        //resta 1 al tope entonces no podes acceder al elemento anterior
        public void POP(){
            top--;
        }
        //imprime toda la pila. se uso para debuggear
        public void ALL(){
            for(int i =0; i<=top; i++){
                System.out.println(elementos[i]);
            }
        }
  
    }
