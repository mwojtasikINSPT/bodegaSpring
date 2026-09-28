package prog2.bodega_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import prog2.bodega_backend.model.Licor;

//permite acceder a MySQL
@Repository
public interface LicorRepository extends JpaRepository<Licor, Integer> {
    List<Licor> findByTipo(String tipo);
}
