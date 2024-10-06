package org.example

/**
 * Representa a un empleado con información básica como ID, apellido, departamento y salario.
 *
 * @property id El identificador único del empleado.
 * @property apellido El apellido del empleado.
 * @property depart El departamento al que pertenece el empleado.
 * @property salario El salario actual del empleado.
 */
data class Empleado(val id: Int, val apellido: String, val depart: String, var salario: Double){

    /**
     * Retorna una representación en cadena del empleado, incluyendo todos sus atributos.
     *
     * @return Una cadena con la informacion del empleado
     */
    override fun toString(): String {
        return "ID: $id, Apellido: $apellido, Departamento: $depart, Salario: $salario"
    }
}