/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.potromon.entidades;

import com.mycompany.potromon.persistencia.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

    /**
     * Lista para apartado de info de los Potromons.
     */

public class Potromon {

    public static List<Potromon> getAll(){
        List<Potromon> potromones = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(
            "SELECT p.id AS id_potromon, p.nombre AS nombre, p.apodo AS apodo, g.genero AS genero " +
            "FROM Potromon p " +
            "JOIN Genero g ON p.genero = g.id_genero");
            
            while(rs.next()){
                Potromon p = new Potromon();
                p.setId(rs.getInt("id_potromon"));
                p.setNombre(rs.getString("nombre"));
                p.setApodo(rs.getString("apodo"));
                p.setGenero(rs.getInt("genero"));
                
                potromones.add(p);
    }
            } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return potromones;
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
    public int getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(int genero) {
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
    private int genero;
    private String tipo;
    private double altura;
    private double peso;
    private int puntajeBatalla;
    private String ciudad;
    private String descripcion;
}
