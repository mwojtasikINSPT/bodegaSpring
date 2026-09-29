package prog2.bodega_backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import prog2.bodega_backend.exceptions.LicorNotFoundException;
import prog2.bodega_backend.model.Licor;
import prog2.bodega_backend.repository.LicorRepository;

//Lógica del CRUD
@Service
public class LicorService {

    private final LicorRepository licorRepository;

    public LicorService(LicorRepository licorRepository) {
        this.licorRepository = licorRepository;
    }

    public List<Licor> buscarLicores(String tipo) {

        if (tipo != null && !tipo.trim().isEmpty()) {
            return licorRepository.findByTipo(tipo.trim());
        }

        return licorRepository.findAll();
    }

    public Licor guardarLicor(Licor licor) {
        if (licor.getFoto() == null || licor.getFoto().isEmpty()) {
            licor.setFoto("noimage.png");
        }
        validarParaCrear(licor);
        return licorRepository.save(licor);
    }

    public Licor actualizarLicor(Integer id, Licor licor) {

        Licor existente = licorRepository.findById(id)
                .orElseThrow(() -> new LicorNotFoundException(id));

        boolean huboCambios = false;

        if (licor.getTipo() != null && !licor.getTipo().trim().isEmpty()) {
            existente.setTipo(licor.getTipo().trim());
            huboCambios = true;
        }

        if (licor.getMarca() != null && !licor.getMarca().trim().isEmpty()) {
            existente.setMarca(licor.getMarca().trim());
            huboCambios = true;
        }

        if (licor.getFoto() != null && !licor.getFoto().trim().isEmpty()) {
            existente.setFoto(licor.getFoto().trim());
            huboCambios = true;
        }

        if (!huboCambios) {
            return existente;
        }

        return licorRepository.save(existente);
    }

    public void eliminarLicor(Integer id) {

        if (!licorRepository.existsById(id)) {
            throw new LicorNotFoundException(id);
        }

        licorRepository.deleteById(id);
    }

    public Licor buscarPorId(Integer id) {
        return licorRepository.findById(id)
                .orElseThrow(() -> new LicorNotFoundException(id));
    }

    //Metodos Auxiliares
    private void validarParaCrear(Licor licor) {

        if (licor.getTipo() == null || licor.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo es obligatorio");
        }

        if (licor.getMarca() == null || licor.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("La marca es obligatoria");
        }

        licor.setTipo(licor.getTipo().trim());
        licor.setMarca(licor.getMarca().trim());

        if (licor.getFoto() != null) {
            licor.setFoto(licor.getFoto().trim());
        }
    }

}
