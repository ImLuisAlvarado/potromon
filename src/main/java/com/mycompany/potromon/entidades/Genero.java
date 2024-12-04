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
     * Lista para apartado de estadisticas de los Potromons.
     */

public class Genero {

    
    public static List<Genero> getAll(){
        List<Genero> generos = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(
            "SELECT g.id_genero, g.genero AS genero " +
            "FROM Genero g ");
            
            while(rs.next()){
                Genero g = new Genero();
                
                g.setId(rs.getInt("id_genero"));
                g.setGenero(rs.getString("genero"));
                
                generos.add(g);
    }
            } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return generos;
    }
    
    public static Genero getGeneroById(int idGenero) {
        Genero genero = null;
        String consulta = "SELECT id_genero, genero FROM Genero WHERE id_genero = ?";

        try (Connection conexion = Conexion.obtener();
             PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setInt(1, idGenero);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                genero = new Genero();
                genero.setId(rs.getInt("id_genero"));
                genero.setGenero(rs.getString("genero"));
            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error al obtener el género: " + ex.getMessage());
        }

        return genero;
    }
    
    @Override
    public String toString() {
        return this.getGenero();
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
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    private int id;
    private String genero;
    
}