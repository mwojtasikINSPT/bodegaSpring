package prog2.bodega_backend.exceptions;

public class LicorNotFoundException extends RuntimeException {

    public LicorNotFoundException(Integer id) {
        super("No se encontró el licor con id: " + id);
    }
}
