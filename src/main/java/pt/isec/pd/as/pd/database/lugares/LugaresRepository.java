package pt.isec.pd.as.pd.database.lugares;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isec.pd.as.pd.database.espetaculos.Espetaculos;

import java.util.List;
import java.util.Optional;

@Repository
public interface LugaresRepository extends JpaRepository<Lugares, Long> {
    Optional<Lugares> findById(Long id);
    List<Lugares> findByEspetaculoId(int idEspetaculo);
}
