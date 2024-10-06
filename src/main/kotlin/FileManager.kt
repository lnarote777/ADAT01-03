package org.example

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class FileManager() {
    fun leerFile(fichero: Path): List<Empleado>{
        val empleados = mutableListOf<Empleado>()
        val br = Files.newBufferedReader(fichero)

        br.use { reader ->
            reader.forEachLine { line ->
                val cabecera = line.split(",")

                if (cabecera[0] != "ID" && cabecera.size == 4){
                    val id = cabecera[0].toInt()
                    val apellido = cabecera[1]
                    val depart = cabecera[2]
                    val salario = cabecera[3].toDouble()

                    val empleado = Empleado(id, apellido, depart, salario)
                    empleados.add(empleado)
                }
            }

        }

        return empleados
    }
    fun escribirXML(empleados: List<Empleado>){
        val db = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val imp = db.domImplementation
        val document = imp.createDocument(null, "empeleados", null)

        for (empleado in empleados) {
            val elementoEmpleado = document.createElement("empleado")
            document.documentElement.appendChild(elementoEmpleado)

            val id = empleado.id
            val apellido = empleado.apellido
            val depart = empleado.depart
            val salario = empleado.salario.toString()

            if (id < 10){
                elementoEmpleado.setAttribute("id", "10$id")
            }else{
                elementoEmpleado.setAttribute("id", "1$id")
            }

            val elementoApellido = document.createElement("apellido")
            val elementoDepart = document.createElement("depart")
            val elementoSalario = document.createElement("salario")

            val textoApellido = document.createTextNode(apellido)
            val textoDepart = document.createTextNode(depart)
            val textoSalario = document.createTextNode(salario)

            elementoApellido.appendChild(textoApellido)
            elementoDepart.appendChild(textoDepart)
            elementoSalario.appendChild(textoSalario)

            elementoEmpleado.appendChild(elementoApellido)
            elementoEmpleado.appendChild(elementoDepart)
            elementoEmpleado.appendChild(elementoSalario)
        }

        val source = DOMSource(document)

        val result = StreamResult(Path.of("src/main/resources/datosEmpleados/empleadosXML.xml").toFile())

        val transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)

    }
//    fun modificarSalario(salario: Double){
//
//    }
//    fun lecturaXML(fichero: File): List<Empleado> {
//        val empleados = mutableListOf<Empleado>()
//
//        val dbf = DocumentBuilderFactory.newInstance()
//        val db = dbf.newDocumentBuilder()
//        val document = db.parse(fichero)
//
//        val root = document.documentElement
//        root.normalize()
//
//        val listaNodos = root.getElementsByTagName("empleado")
//
//        for (i in 0 until listaNodos.length){
//
//            val nodo = listaNodos.item(i)
//
//            if (nodo.nodeType == Node.ELEMENT_NODE){
//
//                val nodoElemento = nodo as Element
//
//                val elementoId = nodoElemento.getAttribute("id")
//                val elementoNombre = nodoElemento.getElementsByTagName("apellido")
//                val elementoDep = nodoElemento.getElementsByTagName("dep")
//                val elementoSalario = nodoElemento.getElementsByTagName("salario")
//
//                val textContentId = elementoId.toInt()
//                val textContentNombre = elementoNombre.item(0).textContent
//                val textContentDep = elementoDep.item(0).textContent
//                val textContentSalario = elementoSalario.item(0).textContent.toDouble()
//
//                val empleado = Empleado(textContentId, textContentNombre, textContentDep, textContentSalario)
//                empleados.add(empleado)
//            }
//        }
//
//        return empleados
//    }
}