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
import com.google.gson.Gson;

    /**
     * Lista para apartado de info de los Potromons.
     */

public class Potromon {

    /**
     * @return the entrenador
     */
    public Entrenador getEntrenador() {
        return entrenador;
    }

    /**
     * @param entrenador the entrenador to set
     */
    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

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

    /**
     * @return the habilidadPrincipal
     */
    public String getHabilidadPrincipal() {
        return habilidadPrincipal;
    }

    /**
     * @param habilidadPrincipal the habilidadPrincipal to set
     */
    public void setHabilidadPrincipal(String habilidadPrincipal) {
        this.habilidadPrincipal = habilidadPrincipal;
    }

    /**
     * @return the habilidadSecundaria
     */
    public String getHabilidadSecundaria() {
        return habilidadSecundaria;
    }

    /**
     * @param habilidadSecundaria the habilidadSecundaria to set
     */
    public void setHabilidadSecundaria(String habilidadSecundaria) {
        this.habilidadSecundaria = habilidadSecundaria;
    }

    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * Deserializes a JSON string into a Delidelivery object.
     * @param json the JSON string to deserialize
     * @return the deserialized Delidelivery object
     **/
    public Potromon deserialize (String json){
        Potromon delidelivery = new Potromon();
        try{
             delidelivery = new Gson().fromJson(json, Potromon.class);
        }catch(Exception ex){
            System.err.println("Couldn't deserialize because of an error"+(ex.getMessage()));
    }
        return delidelivery;
}
    
    public static List<Potromon> getAll() {
    List<Potromon> potromones = new ArrayList<>();
    try {
        Connection conexion = Conexion.obtener();
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(
            "SELECT p.id_potromon, p.nombre AS nombre, p.apodo AS apodo, p.genero AS id_genero, " +
            "p.tipo AS tipo, p.altura AS altura, p.peso AS peso, p.puntaje_batalla AS puntaje_batalla, " +
            "p.habilidad_principal AS habilidad_principal, p.habilidad_secundaria AS habilidad_secundaria, " +
            "p.ciudad AS ciudad, p.descripcion AS descripcion, e.id_entrenador, e.nombre AS entrenador " +
            "FROM Potromon p " +
            "JOIN Entrenador e ON p.entrenador = e.id_entrenador");
        
        while (rs.next()) {
            Potromon p = new Potromon();
            p.setId(rs.getInt("id_potromon"));
            p.setNombre(rs.getString("nombre"));
            p.setApodo(rs.getString("apodo"));
            p.setIdGenero(rs.getInt("id_genero"));
            p.setTipo(rs.getString("tipo"));
            p.setAltura(rs.getDouble("altura"));
            p.setPeso(rs.getDouble("peso"));
            p.setPuntajeBatalla(rs.getInt("puntaje_batalla"));
            p.setHabilidadPrincipal(rs.getString("habilidad_principal"));
            p.setHabilidadSecundaria(rs.getString("habilidad_secundaria"));
            p.setCiudad(rs.getString("ciudad"));
            p.setDescripcion(rs.getString("descripcion"));

            // Obtener el id_entrenador
            int idEntrenador = rs.getInt("id_entrenador");
            if (idEntrenador != 0) {
                Entrenador entrenador = Entrenador.getById(idEntrenador);
                p.setEntrenador(entrenador); 
            }

            potromones.add(p);
        }
    } catch (Exception ex) {
        System.err.println("Ocurrió un error: " + ex.getMessage());
    }
    return potromones;
}
    
    public static Potromon getById(int id) {
    String query = ("SELECT p.id_potromon, p.nombre AS nombre, p.apodo AS apodo, p.genero AS id_genero, " +
            "p.tipo AS tipo, p.altura AS altura, p.peso AS peso, p.puntaje_batalla AS puntaje_batalla, " +
            "p.habilidad_principal AS habilidad_principal, p.habilidad_secundaria AS habilidad_secundaria, " +
            "p.ciudad AS ciudad, p.descripcion AS descripcion, e.nombre AS entrenador " +
            "FROM Potromon p " +
            "JOIN Entrenador e ON p.entrenador = e.id_entrenador "+
            "WHERE id_potromon = ? ");
    Potromon potromon = null;

    try (Connection conexion = Conexion.obtener();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            potromon = new Potromon();
            potromon.setId(rs.getInt("id_potromon"));
            potromon.setNombre(rs.getString("nombre"));
            potromon.setApodo(rs.getString("apodo"));
            potromon.setIdGenero(rs.getInt("id_genero"));
            potromon.setTipo(rs.getString("tipo"));
            potromon.setAltura(rs.getDouble("altura"));
            potromon.setPeso(rs.getDouble("peso"));
            potromon.setPuntajeBatalla(rs.getInt("puntaje_batalla"));
            potromon.setHabilidadPrincipal(rs.getString("habilidad_principal"));
            potromon.setHabilidadSecundaria(rs.getString("habilidad_secundaria"));
            potromon.setCiudad(rs.getString("ciudad"));
            potromon.setDescripcion(rs.getString("descripcion"));
        }
    } catch (Exception ex) {
        System.err.println("Error al obtener Potromon por ID: " + ex.getMessage());
    }

    return potromon;
}

    
    public static boolean save(String nombre, String apodo, int idGenero, String tipo, double altura, 
                           double peso, int puntajeBatalla, String habilidadPrincipal, 
                           String habilidadSecundaria, String ciudad, String descripcion, int idEntrenador) {
    boolean resultado = false;
    String consulta = "INSERT INTO Potromon (nombre, apodo, genero, tipo, altura, peso, puntaje_batalla, " +
                      "habilidad_principal, habilidad_secundaria, ciudad, descripcion, entrenador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conexion = Conexion.obtener();
         PreparedStatement statement = conexion.prepareStatement(consulta)) {

        statement.setString(1, nombre);
        statement.setString(2, apodo);
        statement.setInt(3, idGenero);
        statement.setString(4, tipo);
        statement.setDouble(5, altura);
        statement.setDouble(6, peso);
        statement.setInt(7, puntajeBatalla);
        statement.setString(8, habilidadPrincipal);
        statement.setString(9, habilidadSecundaria);
        statement.setString(10, ciudad);
        statement.setString(11, descripcion);
        statement.setInt(12, idEntrenador);

        statement.execute();
        resultado = statement.getUpdateCount() == 1;

    } catch (Exception ex) {
        System.err.println("Ocurrió un error al guardar el Potromon: " + ex.getMessage());
    }

    return resultado;
}
    
        public static boolean delete(int id){
        boolean resultado = false;
        try{
            Connection conexion = Conexion.obtener();
            String consulta = "DELETE FROM Potromon WHERE id_potromon=?";
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
    public static boolean edit(int id, String nombre, String apodo, int idGenero, String tipo, 
                           double altura, double peso, int puntajeBatalla, String habilidadPrincipal, 
                           String habilidadSecundaria, String ciudad, String descripcion) {
    String query = "UPDATE Potromon SET nombre = ?, apodo = ?, id_genero = ?, tipo = ?, " +
                   "altura = ?, peso = ?, puntaje_batalla = ?, habilidad_principal = ?, " +
                   "habilidad_secundaria = ?, ciudad = ?, descripcion = ? WHERE id = ?";

    try (Connection conexion = Conexion.obtener();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, nombre);
        statement.setString(2, apodo);
        statement.setInt(3, idGenero);
        statement.setString(4, tipo);
        statement.setDouble(5, altura);
        statement.setDouble(6, peso);
        statement.setInt(7, puntajeBatalla);
        statement.setString(8, habilidadPrincipal);
        statement.setString(9, habilidadSecundaria);
        statement.setString(10, ciudad);
        statement.setString(11, descripcion);
        statement.setInt(12, id);

        return statement.executeUpdate() > 0;
    } catch (Exception ex) {
        System.err.println("Error al editar el Potromon: " + ex.getMessage());
        return false;
    }
}

    
    public static Potromon findById(int id) {
    List<Potromon> potromones = getAll(); 
    for (Potromon p : potromones) {
        if (p.getId() == id) {
            return p;
        }
    }
    return null;
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
    private int idGenero;
    private String tipo;
    private double altura;
    private double peso;
    private int puntajeBatalla;
    private String habilidadPrincipal;
    private String habilidadSecundaria;
    private String ciudad;
    private String descripcion;
    private Entrenador entrenador;
    private byte[] imagen;
}
