package pt.isec.pd.as.pd.database.reservas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Long>
{
    List<Reservas> findByPago(int pago);

    List<Reservas> findByUserUsernameAndPago(String username, int pago);

    void deleteById(Long id);
}
