package market.rest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Asignatura {
    
    @Id
    @Column
    private String id;
    @Column
    private String nombre;

    public Asignatura(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
