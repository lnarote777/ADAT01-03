package org.example

class Menu (private val fileManager: FileManager) {
    fun menu(){

        while (true){

            Consola.mostrarMensaje("\nElige una opción:\n\t 1- Listado empleados\n\t 2- modificar salario\n\t 0- Salir")
            val option = Consola.pedirEntero("->", false)

            when (option) {
                0 -> break
                1 -> {
                    val empleados = fileManager.lecturaXML()
                    empleados.forEach { Consola.mostrarEmpleado(it) }
                }
                2 -> {
                    val id = Consola.pedirEntero("Id del emppleado -> ", false)
                    val salario = Consola.pedirDouble("Nuevo salario -> ", false)
                    fileManager.modificarSalario(id, salario)
                }
                else -> Consola.mostrarMensaje("Opción no válida")
            }


        }

    }
}