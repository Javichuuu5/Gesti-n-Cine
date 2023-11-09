const val TAM = 5
const val LIBRE = false
const val OCUPADO = true
const val PRECIO = 5

typealias SALA = Array<BooleanArray>

fun main(){
    var sala: SALA = Array(TAM){ BooleanArray(TAM) {LIBRE} }
    var opcion = -1
    println("Bienvenido al cine")
    println("==================")
    println()
    do {
        println("Bienvenido al cine")
        println("Pulse 1 para visualizar la sala")
        println("Pulse 2 para comprar una entrada")
        println("Pulse 3 para devolver una entrada")
        println("Pulse 4 para comprobar la recaudación")
        println("Pulse 5 para salir")
        println("Indique su acción: ")
        opcion = readln().toIntOrNull() ?: - 1
        when(opcion){
            1 -> mostrarSala(sala)
            2 -> comprarEntrada(sala)
            3 -> devolverEntrada(sala)
            4 -> recaudacion(sala)
            5 -> println("Hasta luego")
            else -> println("Opción no válida")
        }
        println()
    }while (opcion != 5)
}
/**
 * Nos permite saber la recaudación de la sala en función de los asientos ocupados
 * @param sala la sala de cine 
 */
fun recaudacion(sala: SALA) {
    println()
    println("Recaudación")
    var result = 0
    for (i in sala.indices){
        for (j in sala[i].indices){
            if(sala[i][j] == OCUPADO){
                result += PRECIO
            }
        }
    }
    println("El total reacaudado es $result €")
    println()

}

/**
 * Nos permite devolver una entrada, dejando así la posición del asiento libre
 * @param sala La matriz que representa la sala de cine
 */
fun devolverEntrada(sala: SALA) {
    var fila: Int = -1
    var columna: Int = -1
    var isCorrect = true
    println()
    do {
        println("Ingrese la fila y la columna del asiento que quiere comprar (nºde Fila;nº de columna, ejemplo 1;1)")
        val input = readln().trim().split(";").toTypedArray()
        if (input.size != 2) {
            println("No has introducido un asiento válido")
            isCorrect = false
        } else {
            fila = readln().trim().toIntOrNull() ?: -1
            columna = readln().trim().toIntOrNull() ?: -1
            if (fila !in (1..TAM)) {
                println("Fila incorrecta, debe ser un valor entre 1 y $TAM")
                isCorrect = false
            }
            if (columna !in (1..TAM)) {
                println("Columna incorrecta, debe ser un valor entre 1 y $TAM")
                isCorrect = false
            }
        }

    } while (!isCorrect)
    fila--
    columna--
    if (sala[fila][columna] == LIBRE) {
        println("No hay una entrada asignada a esa butaca")
    }else{
        sala[fila][columna] = LIBRE
        println("Entrada devuelta correctamente")
    }
    println()
}

/**
 * Permite comprar una entrada de la sala de cine, asignando la posición elegida como ocupada
 * @param La matriz que representa la sala de cine
 */
fun comprarEntrada(sala: SALA) {
   println()
    if(!hayAsientoLibre(sala)){
        println("La sala está completa")
        return
    }
    var fila: Int = -1
    var columna: Int = -1
    var isCorrect = true
    do{
        println("Ingrese la fila y la columna del asiento que quiere comprar (nºde Fila;nº de columna, ejemplo 1;1)")
        val input = readln().trim().split(";").toTypedArray()
        if(input.size != 2){
            println("No has introducido un asiento válido")
            isCorrect = false
        }else{
            fila = readln().trim().toIntOrNull() ?: - 1
            columna = readln().trim().toIntOrNull() ?: - 1
            if(fila !in (1..TAM)){
                println("Fila incorrecta, debe ser un valor entre 1 y $TAM")
                isCorrect = false
            }
            if(columna !in (1..TAM)){
                println("Columna incorrecta, debe ser un valor entre 1 y $TAM")
                isCorrect = false
            }
        }

    }while (!isCorrect)
    fila--
    columna--
    sala[fila][columna] = OCUPADO
    println()
}

/**
 * Comprueba si hay asientos libres en la sala
 * @param Sala La matriz que representa la sala de cine
 * @return true si hay al menos 1 asiento libre y false si todos se encuentran ocupados
 */
fun hayAsientoLibre(sala: SALA): Boolean {
    for(fila in sala.indices){
        for(columna in sala[fila].indices){
            if(sala[fila][columna] == LIBRE){
                return true
            }
        }
    }
    return false
}

/**
 * Muestra la sala con las butacas ocupadas y libres
 * @param sala La matriz que representa la sala de cine
 */
fun mostrarSala(sala: SALA) {
    println()
    println("Estado de la sala de cine")
    for (fila in sala.indices) {
        for (columna in sala[fila].indices) {
            if (sala[fila][columna] == OCUPADO) {
                print("| X |")
            } else {
                print("| _ |")
            }
        }
        println()
    }
    println()
}