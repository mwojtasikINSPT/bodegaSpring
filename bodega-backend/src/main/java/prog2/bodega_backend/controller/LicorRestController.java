package prog2.bodega_backend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prog2.bodega_backend.model.Licor;
import prog2.bodega_backend.repository.LicorRepository;
import prog2.bodega_backend.service.LicorService;

//endpoints que consumirá el frontend
@RestController
@CrossOrigin(origins = "*")
public class LicorRestController {

    private final LicorService licorService;

    public LicorRestController(LicorService licorService) {
        this.licorService = licorService;
    }

    @GetMapping("/buscarLicores")
    public List<Licor> buscarLicores(
            @RequestParam(name = "tipo", required = false) String tipo) {

        return licorService.buscarLicores(tipo);
    }

    @PostMapping("/agregarLicor")
    public Licor guardarLicor(@RequestBody Licor licor) {
        return licorService.guardarLicor(licor);
    }

    @PutMapping("/actualizarLicor/{id}")
    public Licor actualizarLicor(
            @PathVariable Integer id,
            @RequestBody Licor licor) {

        return licorService.actualizarLicor(id, licor);
    }

    @DeleteMapping("/eliminarLicor/{id}")
    public void eliminarLicor(@PathVariable Integer id) {
        licorService.eliminarLicor(id);
    }

    @GetMapping("/buscarLicor/{id}")
    public Optional<Licor> buscarPorId(@PathVariable Integer id) {
        return licorService.buscarPorId(id);
    }

}
