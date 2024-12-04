/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.potromon.entidades;

import com.mycompany.potromon.persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramon
 */
public class Entrenador {

    /**
     * @return the idGenero
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * @param idGenero the idGenero to set
     */
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    
    public static List<Entrenador> getAll(){
        List<Entrenador> entrenadores = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(
            "SELECT e.id_entrenador, e.nombre AS nombre, e.apodo AS apodo, g.genero AS genero " +
            "FROM Entrenador e " +
            "JOIN Genero g ON e.genero = g.id_genero");
            
            while(rs.next()){
                Entrenador e = new Entrenador();
                
                e.setId(rs.getInt("id_entrenador"));
                e.setNombre(rs.getString("nombre"));
                e.setApodo(rs.getString("apodo"));
                //e.setGenero(rs.getString("genero"));
                
                
                entrenadores.add(e);
    }
            } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return entrenadores;
    }
    
    public static Entrenador getById(int id) {
        Entrenador e = new Entrenador();
        try {
            Connection conexion = Conexion.obtener();
            String query = ("SELECT e.id_entrenador, e.nombre AS nombre, e.apodo AS apodo, g.genero AS genero " +
            "FROM Entrenador e " +
            "JOIN Genero g ON e.genero = g.id_genero " +
            "WHERE id_entrenador = ?");
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                e.setId(rs.getInt("id_entrenador"));
                e.setNombre(rs.getString("nombre"));
                e.setApodo(rs.getString("apodo"));
                //e.setGenero(rs.getString("genero"));
            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return e;
    }
    
    public static boolean save(String nombre, String apodo, String genero) {
    boolean resultado = false;
    String consulta = "INSERT INTO Entrenador (nombre, apodo, genero) VALUES (?, ?, ?)";

    try (Connection conexion = Conexion.obtener();
         PreparedStatement statement = conexion.prepareStatement(consulta)) {

        statement.setString(1, nombre);
        statement.setString(2, apodo);
        statement.setString(3, genero);

        statement.execute();
        resultado = statement.getUpdateCount() == 1;

    } catch (Exception ex) {
        System.err.println("Ocurrió un error al guardar el Entrenador: " + ex.getMessage());
    }

    return resultado;
}
    
    public static boolean delete(int id){
        boolean resultado = false;
        try{
            Connection conexion = Conexion.obtener();
            String consulta = "DELETE FROM Entrenador WHERE id_entrenador=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, id);
            
            statement.execute();
            resultado = statement.getUpdateCount() == 1;
            conexion.close();
        }catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public static boolean edit(int id, String nombre, String apodo, String genero) {
    
        boolean resultado = false;
        String consulta = "UPDATE Entrenador SET nombre = ?, apodo = ?, genero = ?" +
                      "WHERE id_entrenador = ?";

        try (Connection conexion = Conexion.obtener();
             PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setString(1, nombre);
            statement.setString(2, apodo);
            statement.setString(3, genero);
            statement.setInt(4, id);

            statement.execute();
            resultado = statement.getUpdateCount() == 1;
        
        } catch (Exception ex) {
            System.err.println("Ocurrió un error al actualizar el Entrenador: " + ex.getMessage());
    }

    return resultado;
}
    public static Entrenador findById(int id) {
    List<Entrenador> entrenadores = getAll(); 
    for (Entrenador e : entrenadores) {
        if (e.getId() == id) {
            return e;
        }
    }
    return null;
}
    
    
    @Override
    public String toString() {
        return this.getNombre();
}
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apodo
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * @param apodo the apodo to set
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    
        private int id;
	private String nombre;
	private String apodo;
	private int idGenero;
}
