package org.example

import java.io.File
import java.nio.file.Path

fun main() {
    val fileEmpleados = Path.of("src/main/resources/datosEmpleados/empleados.csv")

    val fileManager = FileManager()
    val menu = Menu(fileManager)

    val empleados = fileManager.leerFile(fileEmpleados)
    fileManager.escribirXML(empleados)

    menu.menu()
}