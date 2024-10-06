package org.example

data class Empleado(val id: Int, val apellido: String, val depart: String, var salario: Double){
    override fun toString(): String {
        return "ID: $id, Apellido: $apellido, Departamento: $depart, Salario: $salario"
    }
}