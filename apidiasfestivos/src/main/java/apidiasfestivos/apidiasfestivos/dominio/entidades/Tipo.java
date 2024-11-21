package apidiasfestivos.apidiasfestivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tipo")  // Nombre de la tabla en minúsculas
@Entity
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private int id;

    @Column(name = "tipo") 
    private String tipos;

    public Tipo(int id, String tipos) {
        this.id = id;
        this.tipos = tipos;
    }

    // Constructor vacío
    public Tipo() {
    }

    // Getter y Setter en minúsculas
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

}
