package prog2.bodega_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//representa la tabla licores
@Getter
@Setter
@Entity
@Table(name = "licores")
public class Licor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private String marca;
    private String foto;

    // Constructor vacío (Obligatorio para estándares web)
    public Licor() {
    }

    // Constructor completo:
    public Licor(String tipo, String marca, String foto) {
        this.tipo = tipo;
        this.marca = marca;
        this.foto = foto;
    }
}
