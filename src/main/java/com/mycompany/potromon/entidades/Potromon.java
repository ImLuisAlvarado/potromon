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
     * Lista para apartado de info de los Potromons.
     */

public class Potromon {

    /**
     * @return the entrenador
     */
    public String getEntrenador() {
        return entrenador;
    }

    /**
     * @param entrenador the entrenador to set
     */
    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public static List<Potromon> getAll(){
        List<Potromon> potromones = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(
            "SELECT p.id_potromon, p.nombre AS nombre, p.apodo AS apodo, g.genero AS genero, " +
            "p.tipo AS tipo, p.altura AS altura, p.peso AS peso, p.puntaje_batalla AS puntaje_batalla, " +
            "p.ciudad AS ciudad, p.descripcion AS descripcion, e.nombre AS entrenador " +
            "FROM Potromon p " +
            "JOIN Genero g ON p.genero = g.id_genero "+
            "JOIN Entrenador e ON p.entrenador = e.id_entrenador");
            
            while(rs.next()){
                Potromon p = new Potromon();
                
                p.setId(rs.getInt("id_potromon"));
                p.setNombre(rs.getString("nombre"));
                p.setApodo(rs.getString("apodo"));
                p.setGenero(rs.getString("genero"));
                p.setTipo(rs.getString("tipo"));
                p.setAltura(rs.getDouble("altura"));
                p.setPeso(rs.getDouble("peso"));
                p.setPuntajeBatalla(rs.getInt("puntaje_batalla"));
                p.setCiudad(rs.getString("ciudad"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setEntrenador(rs.getString("entrenador"));
                
                
                potromones.add(p);
    }
            } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return potromones;
    }
    
    public static Potromon getById(int id) {
        Potromon p = new Potromon();
        try {
            Connection conexion = Conexion.obtener();
            String query = ("SELECT p.id_potromon, p.nombre AS nombre, p.apodo AS apodo, g.genero AS genero, " +
            "p.tipo AS tipo, p.altura AS altura, p.peso AS peso, p.puntaje_batalla AS puntaje_batalla, " +
            "p.ciudad AS ciudad, p.descripcion AS descripcion, e.nombre AS entrenador " +
            "FROM Potromon p " +
            "JOIN Genero g ON p.genero = g.id_genero "+
            "JOIN Entrenador e ON p.entrenador = e.id_entrenador "+"WHERE id_potromon = ?");
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_potromon"));
                p.setNombre(rs.getString("nombre"));
                p.setApodo(rs.getString("apodo"));
                p.setGenero(rs.getString("genero"));
                p.setTipo(rs.getString("tipo"));
                p.setAltura(rs.getDouble("altura"));
                p.setPeso(rs.getDouble("peso"));
                p.setPuntajeBatalla(rs.getInt("puntaje_batalla"));
                p.setCiudad(rs.getString("ciudad"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setEntrenador(rs.getString("entrenador"));
            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return p;
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

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the puntajeBatalla
     */
    public int getPuntajeBatalla() {
        return puntajeBatalla;
    }

    /**
     * @param puntajeBatalla the puntajeBatalla to set
     */
    public void setPuntajeBatalla(int puntajeBatalla) {
        this.puntajeBatalla = puntajeBatalla;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    private int id;
    private String nombre;
    private String apodo;
    private String genero;
    private String tipo;
    private double altura;
    private double peso;
    private int puntajeBatalla;
    private String ciudad;
    private String descripcion;
    private String entrenador;
}
