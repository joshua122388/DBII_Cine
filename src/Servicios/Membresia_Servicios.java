/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import entidades.Membresia;
import java.util.List;

/**
 *
 * @author Joshua
 */
public interface Membresia_Servicios {
    void insertar(Membresia membresia) throws Exception;
    void actualizar(Membresia membresia) throws Exception;
    void eliminar(int id) throws Exception;
    Membresia buscarPorID(int id) throws Exception;
    List<Membresia> listarTodos() throws Exception;
}
