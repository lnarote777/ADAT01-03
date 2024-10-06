package org.example

import java.io.File
import java.nio.file.Path

fun main() {
    val fileEmpleados = Path.of("src/main/resources/datosEmpleados/empleados.csv")

    val fileManager = FileManager()

    val empleados = fileManager.leerFile(fileEmpleados)

    fileManager.escribirXML(empleados)

    val listaEmpleadosXML = fileManager.lecturaXML()
    listaEmpleadosXML.forEach{println(it)}
}