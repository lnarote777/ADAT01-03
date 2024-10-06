package org.example

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * Clase responsable de gestionar las operaciones de lectura y escritura de datos de empleados
 * en diferentes formatos de archivo, así como la modificación de información de los empleados.
 */
class FileManager() {

    /**
     * Lee un archivo CSV y convierte cada línea válida en una instancia de [Empleado].
     *
     * @param fichero La ruta del archivo CSV a leer.
     * @return Una lista de objetos [Empleado] obtenidos del archivo.
     */
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

    /**
     * Escribe una lista de empleados en un archivo XML.
     *
     * @param empleados La lista de objetos [Empleado] que se van a escribir en el archivo XML.
     */
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

            if (id.toString().length < 3){
                if (id < 10){
                    elementoEmpleado.setAttribute("id", "10$id")
                }else{
                    elementoEmpleado.setAttribute("id", "1$id")
                }
            }else {
                elementoEmpleado.setAttribute("id", id.toString())
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

    /**
     * Modifica el salario de un empleado específico identificado por su ID.
     *
     * @param id El ID del empleado cuyo salario se desea modificar.
     * @param salario El nuevo salario que se asignará al empleado.
     */
    fun modificarSalario(id: Int, salario: Double){
        val empleados = lecturaXML()
        val empleado = empleados.find { it.id == id }

        if (empleado != null) {
            empleado.salario = salario
            escribirXML(empleados)
        }else {
            Consola.mostrarMensaje("No se encontró ningún empleado")
        }
    }

    /**
     * Lee el archivo XML de empleados y devuelve una lista de objetos [Empleado].
     *
     * @return Una lista de objetos [Empleado] obtenidos del archivo XML.
     */
    fun lecturaXML(): List<Empleado> {
        val empleados = mutableListOf<Empleado>()
        val fichero = File("${System.getProperty("user.dir")}/src/main/resources/datosEmpleados/empleadosXML.xml")
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val document = db.parse(fichero)

        val root = document.documentElement
        root.normalize()

        val listaNodos = root.getElementsByTagName("empleado")

        for (i in 0 until listaNodos.length){

            val nodo = listaNodos.item(i)

            if (nodo.nodeType == Node.ELEMENT_NODE){

                val nodoElemento = nodo as Element

                val elementoId = nodoElemento.getAttribute("id")
                val elementoNombre = nodoElemento.getElementsByTagName("apellido")
                val elementoDep = nodoElemento.getElementsByTagName("depart")
                val elementoSalario = nodoElemento.getElementsByTagName("salario")

                val textContentId = elementoId.toInt()
                val textContentApellido = elementoNombre.item(0).textContent
                val textContentDep = elementoDep.item(0).textContent
                val textContentSalario = elementoSalario.item(0).textContent.toDouble()

                val empleado = Empleado(textContentId, textContentApellido, textContentDep, textContentSalario)
                empleados.add(empleado)
            }
        }

        return empleados
    }
}