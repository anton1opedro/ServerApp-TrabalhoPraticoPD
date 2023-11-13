package pt.isec.pd.as.pd.database.reservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasService
{
    @Autowired private
    ReservasRepository connection;

    public List<Reservas> listAllReservas() {
        return (List<Reservas>) connection.findAll();
    }

    public Reservas create(Reservas reserva){
        return connection.save(reserva);
    }

    public List<Reservas> getReservationsAwaitingPaymentConfirmation() {
        return connection.findByPago(0);
    }

    public List<Reservas> getPaidReservations() {
        return connection.findByPago(1);
    }

//    public boolean deleteReservation(Long id, String username) {
//        Optional<Reservas> reserva = connection.findById(id);
//        if (reserva.isPresent() && reserva.get().getUser().getUsername().equals(username) && reserva.get().getPago() == 0) {
//            connection.deleteById(id);
//            return true;
//        }
//        return false;
//    }
}
