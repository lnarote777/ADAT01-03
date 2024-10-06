package org.example

/**
 * Clase que representa un menú interactivo para la gestión de empleados.
 *
 * @property fileManager Instancia de [FileManager] utilizada para operaciones de lectura y modificación de empleados.
 */
class Menu (private val fileManager: FileManager) {

    /**
     * Inicia el bucle del menú, mostrando opciones al usuario y ejecutando acciones según la selección.
     *
     * El menú ofrece las siguientes opciones:
     * 1. Listado de empleados
     * 2. Modificar salario de un empleado
     * 0. Salir del menú
     *
     * El método continuará ejecutándose hasta que el usuario seleccione la opción de salir.
     */
    fun menu(){

        while (true){

            Consola.mostrarMensaje("\nElige una opción:\n\t 1- Listado empleados\n\t 2- modificar salario\n\t 0- Salir")
            val option = Consola.pedirEntero("-> ", false)

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