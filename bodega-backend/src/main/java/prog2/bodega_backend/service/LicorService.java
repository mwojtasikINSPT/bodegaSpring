package prog2.bodega_backend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
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
        return licorRepository.save(licor);
    }

    public Licor actualizarLicor(Integer id, Licor licor) {
        licor.setId(id);
        return licorRepository.save(licor);
    }

    public void eliminarLicor(Integer id) {
        licorRepository.deleteById(id);
    }

    public Optional<Licor> buscarPorId(Integer id) {
        return licorRepository.findById(id);
    }
}
