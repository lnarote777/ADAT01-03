package org.example

object Consola {
    fun mostrarMensaje(mensaje:String, salto: Boolean = true){
        if(salto) println(mensaje) else print(mensaje)
    }

    fun mostrarEmpleado(empleado: Empleado) = println(empleado)

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