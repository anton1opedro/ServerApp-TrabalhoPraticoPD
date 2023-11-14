package pt.isec.pd.as.pd.database.espetaculos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EspetaculosRepository extends JpaRepository<Espetaculos, Long>
{
    List<Espetaculos> findByPais(String pais);
    List<Espetaculos> findByLocal(String local);
    List<Espetaculos> findByTipo(String tipo);
    List<Espetaculos> findByDatahora(String date);

    Optional<Espetaculos> findById(int id);

    //boolean findById(Integer idEspetaculo);

}
