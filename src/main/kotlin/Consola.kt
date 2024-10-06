package org.example

/**
 * Objeto singleton que maneja la interacción con la consola para la entrada y salida de datos.
 */
object Consola {

    /**
     * Muestra un mensaje en la consola.
     *
     * @param mensaje El mensaje que se desea mostrar.
     * @param salto Indica si se debe agregar un salto de línea al final del mensaje.
     *              - `true`: Añade un salto de línea (equivalente a `println`).
     *              - `false`: No añade un salto de línea (equivalente a `print`).
     */
    fun mostrarMensaje(mensaje:String, salto: Boolean = true){
        if(salto) println(mensaje) else print(mensaje)
    }

    /**
     * Muestra la representación de un empleado en la consola.
     *
     * @param empleado La instancia de [Empleado] que se desea mostrar.
     */
    fun mostrarEmpleado(empleado: Empleado) = println(empleado)

    /**
     * Solicita al usuario que ingrese un número entero.
     *
     * @param mensaje El mensaje que se mostrará al usuario para solicitar la entrada.
     * @param salto Indica si se debe agregar un salto de línea después del mensaje.
     *              - `true`: Añade un salto de línea (equivalente a `println`).
     *              - `false`: No añade un salto de línea (equivalente a `print`).
     * @return El número entero ingresado por el usuario.
     *
     * @throws IllegalArgumentException Si el usuario no ingresa un número válido después de varios intentos.
     */
    fun pedirEntero(mensaje: String, salto: Boolean = true): Int{
        var numero = 0
        do {
            mostrarMensaje(mensaje, salto)
            try {
                numero = readln().toInt()
            }catch (e:Exception){
                mostrarMensaje("ERROR - Caracter inválido")
                numero = -1
            }
        }while (numero == -1)

        return numero
    }

    /**
     * Solicita al usuario que ingrese un número de punto flotante (Double).
     *
     * @param mensaje El mensaje que se mostrará al usuario para solicitar la entrada.
     * @param salto Indica si se debe agregar un salto de línea después del mensaje.
     *              - `true`: Añade un salto de línea (equivalente a `println`).
     *              - `false`: No añade un salto de línea (equivalente a `print`).
     * @return El número de punto flotante ingresado por el usuario.
     *
     * @throws IllegalArgumentException Si el usuario no ingresa un número válido después de varios intentos.
     */
    fun pedirDouble(mensaje: String, salto: Boolean = true): Double{
        var numero: Double = 0.0
            do{
            mostrarMensaje(mensaje, salto)
            try {
                numero = readln().toDouble()
            }catch (e:Exception){
                mostrarMensaje("ERROR - Caracter inválido")
                numero = -1.0
            }
        }while (numero == -1.0)

        return numero
    }
}