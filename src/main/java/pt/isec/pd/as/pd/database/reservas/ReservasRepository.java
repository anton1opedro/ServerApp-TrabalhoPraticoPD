package pt.isec.pd.as.pd.database.reservas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Long>
{
    List<Reservas> findByPago(int pago);

    List<Reservas> findByUserUsernameAndPago(String username, int pago);

    Optional<Reservas> findById(int id);

    List<Reservas> findByPagoAndAndUserId(int pago, int id_utilizador);

    void deleteById(int id);
}
