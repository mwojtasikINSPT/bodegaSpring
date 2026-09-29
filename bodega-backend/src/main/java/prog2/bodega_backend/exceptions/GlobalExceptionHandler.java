package prog2.bodega_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja las excepciones de recursos no encontrados y devuelve 404
    @ExceptionHandler(LicorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarLicorNoEncontrado(LicorNotFoundException ex) {
        return ex.getMessage();
    }
    
    //maneja errores de validación y devuelve 400
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarDatoInvalido(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
