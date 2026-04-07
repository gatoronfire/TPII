import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //hay un proximo int?
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        putos
        //leer el primer int que me dice la cantidad de operaciones
        int cantidad = sc.nextInt();
        sc.nextLine();
        //creo la pila TAD PILA
        Pila mipila = new Pila(cantidad);
        Pila max = new Pila(cantidad);
        max.PUSH(Integer.MIN_VALUE);
        //loop por la cantidad de operaciones
        for(int i =0; i < cantidad; i++){
            //leo la linea que tiene formato "operacion-valor"
            String N = sc.nextLine();
            if(N.contains("POP")){
                mipila.POP();
            }
            // como te pasan push numero , hay que separar el string del numero 
            if(N.contains("PUSH")){
                //extraigo el valor del push, lo convierto a int y lo paso a la funcion push
                // Acá basicamente rompo en 2 el push numero, y creo un subtring con la cadena de numeros
                String valorStr = N.substring(5).trim();
                // acá convierto esa string en un numero 
                mipila.PUSH(Integer.parseInt(valorStr));
                if (max.TOP() > Integer.parseInt(valorStr)) {
                    max.PUSH(max.TOP());
                }
                else{
                  max.PUSH(Integer.parseInt(valorStr));  
                }
                
            }
            if(N.contains("SIZE")){
                System.out.println(mipila.SIZE());
            }
            if(N.contains("TOP")){
                
                System.out.println(mipila.TOP());
            }
            if(N.contains("EMPTY")){
                System.out.println(mipila.EMPTY());
            }
        }

        sc.close();
    }
}

//la clase tiene las funciones que se usan en las pilas
class Pila {
    //arreglo de los elementos de la pila (datos principales)
        private int[] elementos;
        //tope de la pila
        private int top;
        //tamaño maximo de la pila
        private int cantidad;
        
        //establecer el tamaño de la pila y crear los arreglos necesarios
        public Pila(int cantidad){
            this.cantidad = cantidad;
            this.elementos = new int[cantidad];
            this.top = -1;
        }
        //funcion push. suma 1 al tope y agrega x en la posicion tope. tambien compara x con el maximo y actualiza el arreglo max
        public void PUSH(int x){
            // agraga elementos a la pila
            top ++;
            elementos[top] = x;
        }
        //funcion top para imprimir el elemento en el tope de la pila
        public Integer TOP(){
            return (elementos[top]);
        }
        //chequea si la lista esta vacia
        public Boolean EMPTY(){
            if(top > -1){
                return false;
            }else{
                //pila vacia
                return true; 
            }
        }
        //devuelve el tamaño de la pila (tope+1)
        public Integer SIZE(){
            if(top == -1){
                // devuelve 0 cuando la pila esta vacia
                return 0;
            }else{
                return (top + 1);
            }
            
        }
        //resta 1 al tope entonces no podes acceder al elemento anterior
        public void POP(){
            top--;
        }
    }