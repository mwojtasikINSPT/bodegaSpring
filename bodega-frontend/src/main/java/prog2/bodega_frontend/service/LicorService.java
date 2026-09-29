package prog2.bodega_frontend.service;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import prog2.bodega_frontend.model.Licor;

// Gestiona la comunicación HTTP con el backend.
@Service
public class LicorService {

    private final RestClient restClient;

    // Configura el cliente HTTP con la URL base del backend.
    public LicorService(RestClient.Builder builder) {
        // Define la dirección donde está funcionando el backend.
        this.restClient = builder
                .baseUrl("http://localhost:8080")
                .build();
    }

    public List<Licor> buscarLicores() {
        // Realiza una petición GET al endpoint /licores.
        return restClient.get()
                .uri("/licores")
                .retrieve()
                // Convierte la respuesta JSON en una lista de objetos Licor.
                .body(new ParameterizedTypeReference<List<Licor>>() {
                });
    }

    public List<Licor> buscarLicoresPorTipo(String tipo) {
        // Realiza una petición GET al endpoint /licores enviando el tipo como parámetro.
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/licores")
                .queryParam("tipo", tipo)
                .build())
                .retrieve()
                // Convierte la respuesta JSON en una lista de objetos Licor.
                .body(new ParameterizedTypeReference<List<Licor>>() {
                });
    }

    public Licor buscarPorId(Integer id) {
        // Realiza una petición GET al endpoint /licores/{id}.
        return restClient.get()
                .uri("/licores/{id}", id)
                .retrieve()
                // Convierte la respuesta JSON en un objeto Licor.
                .body(Licor.class);
    }

    public Licor guardarLicor(Licor licor) {
        // Realiza una petición POST al endpoint /licores enviando el licor en el cuerpo.
        return restClient.post()
                .uri("/licores")
                .body(licor)
                .retrieve()
                // Convierte la respuesta JSON en un objeto Licor.
                .body(Licor.class);
    }

    public Licor actualizarLicor(Integer id, Licor licor) {
        // Realiza una petición PUT al endpoint /licores/{id} enviando el licor en el cuerpo.
        return restClient.put()
                .uri("/licores/{id}", id)
                .body(licor)
                .retrieve()
                // Convierte la respuesta JSON en un objeto Licor.
                .body(Licor.class);
    }

    public void eliminarLicor(Integer id) {
        // Realiza una petición DELETE al endpoint /licores/{id}.
        restClient.delete()
                .uri("/licores/{id}", id)
                .retrieve()
                .toBodilessEntity(); //No espero contendio en la respuesta
    }

}
