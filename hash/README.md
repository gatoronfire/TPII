Espero disfrute esta espectacular lectura de un ReadMe hecha con IA porque no me pude molestar en hacer la documentacion a mano.
# Trabajo Práctico - Implementación de Tablas Hash en Java

# Descripción

Este trabajo práctico implementa distintas estrategias de resolución de colisiones en tablas hash utilizando Java.

Se desarrollaron dos implementaciones:

1. **Hashing cerrado**

   * Sondeo lineal
   * Sondeo cuadrático

2. **Hashing con encadenamiento**

   * Utilizando `ArrayList`

El objetivo es almacenar palabras, realizar búsquedas y comparar distintos métodos de resolución de colisiones.

---

# Archivos del Proyecto

| Archivo                   | Descripción                                                      |
| ------------------------- | ---------------------------------------------------------------- |
| `HashMain.java`           | Implementación de hashing cerrado con sondeo lineal y cuadrático |
| `HashEncadenamiento.java` | Implementación de hashing con encadenamiento                     |

---

# Compilación

## Compilar ambos archivos

```bash id="sjysdd"
javac HashMain.java HashEncadenamiento.java
```

---

# Ejecución

## Hashing cerrado

```bash id="l2v55e"
java HashMain
```

## Hashing con encadenamiento

```bash id="r69x6h"
java HashEncadenamiento
```

También puede ejecutarse utilizando un archivo de entrada:

```bash id="p3f5fi"
java HashMain < input.txt
```

o

```bash id="7fk8j9"
java HashEncadenamiento < input.txt
```

---

# Formato de Entrada

Ambos programas utilizan el mismo formato de entrada.

```txt id="7e7zjl"
N M
palabra1
palabra2
...
palabraN
consulta1
consulta2
...
consultaM
```

Donde:

* `N` = cantidad de palabras a insertar
* `M` = cantidad de búsquedas

---

# Ejemplo de Entrada

```txt id="z8wz2g"
5 4
casa
perro
gato
mesa
silla
gato
auto
mesa
perro
```

---

# Salida del Programa

Ambos programas imprimen:

```txt id="kh4qpn"
palabras_guardadas palabras_encontradas checksum
```

---

# Significado de la Salida

| Valor                  | Descripción                             |
| ---------------------- | --------------------------------------- |
| `palabras_guardadas`   | cantidad de palabras únicas almacenadas |
| `palabras_encontradas` | cantidad de búsquedas exitosas          |
| `checksum`             | valor hash acumulado de las búsquedas   |

---

# Ejemplo de Salida

```txt id="zsvz8r"
5 3 123456789
```

---

# Función Hash

Ambos programas utilizan hashing polinomial.

La función utilizada es:

```java id="9a2bza"
hash = hash * p + palabra.charAt(i)
```

Con:

```java id="z5lh3w"
p = 151
```

La posición final se obtiene aplicando módulo sobre el tamaño de la tabla.

---

# Parte 1 - Hashing Cerrado (`HashMain.java`)

## Estructura Utilizada

```java id="i4p6tp"
String[] tablaHash
```

La tabla tiene tamaño:

```java id="hy0kg5"
131071
```

Número primo cercano a (2^{17}), útil para reducir colisiones.

---

# Resolución de Colisiones

## Sondeo Lineal

Método:

```java id="g7sfd5"
buscarPosicionLineal()
```

Cuando ocurre una colisión:

```txt id="3aswqn"
index + 1
index + 2
index + 3
...
```

hasta encontrar:

* una posición vacía
* o la misma palabra

---

## Sondeo Cuadrático

Método:

```java id="9aq7f4"
buscarPosicionCuadratica()
```

Utiliza:

```txt id="rwwk8v"
index + c1*i + c2*i²
```

Con:

```java id="0nsgzw"
c1 = 1
c2 = 1
```

Actualmente el programa utiliza sondeo lineal por defecto.

Para probar sondeo cuadrático reemplazar:

```java id="f17s8w"
buscarPosicionLineal()
```

por:

```java id="j4u7xz"
buscarPosicionCuadratica()
```

---

# Parte 2 - Hashing con Encadenamiento (`HashEncadenamiento.java`)

## Estructura Utilizada

Se utiliza un arreglo de listas dinámicas:

```java id="jchm1m"
ArrayList<String>[] tablaHash
```

Cada posición de la tabla puede almacenar múltiples palabras.

---

# Resolución de Colisiones

Cuando dos palabras generan el mismo índice hash:

* ambas se almacenan en la misma lista (`ArrayList`)
* no es necesario buscar otra posición libre

Ejemplo conceptual:

```txt id="7cd4o7"
Index 5:
["gato", "mesa", "auto"]
```

---

# Inserción

La función:

```java id="3xj7e6"
buscarPosicionEncadenamiento()
```

verifica si la palabra ya existe.

* Si existe → retorna `-1`
* Si no existe → retorna el índice donde insertar

---

# Checksum

Ambos programas calculan un checksum utilizando:

```java id="6j0lx8"
H = (H * B + (ans + 1)) % MOD;
```

Donde:

| Variable | Valor        |
| -------- | ------------ |
| `B`      | `911382323`  |
| `MOD`    | `1000000007` |

`ans` vale:

* `1` si la palabra fue encontrada
* `0` si no fue encontrada

El checksum permite validar los resultados de las búsquedas.

---

# Comparación Entre Métodos

| Método            | Ventaja                | Desventaja           |
| ----------------- | ---------------------- | -------------------- |
| Sondeo lineal     | Implementación simple  | Produce clustering   |
| Sondeo cuadrático | Reduce clustering      | Más complejo         |
| Encadenamiento    | Maneja bien colisiones | Mayor uso de memoria |

---

# Observaciones

* Las palabras repetidas no se insertan nuevamente.
* `HashMain` utiliza direccionamiento abierto.
* `HashEncadenamiento` utiliza listas dinámicas (`ArrayList`).
* Ambos programas comparten la misma función hash para facilitar la comparación.

---

# Autor

Trabajo práctico realizado en Java para el análisis e implementación de tablas hash y métodos de resolución de colisiones.

