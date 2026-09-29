package prog2.bodega_frontend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Licor {
    private Integer id;
    private String tipo;
    private String marca;
    private String foto;
    
    //Sirve para representar el JSON que manda el backend
}