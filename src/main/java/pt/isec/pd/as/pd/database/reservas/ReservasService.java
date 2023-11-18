package pt.isec.pd.as.pd.database.reservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isec.pd.as.pd.database.espetaculos.Espetaculos;
import pt.isec.pd.as.pd.database.espetaculos.EspetaculosRepository;
import pt.isec.pd.as.pd.database.espetaculos.EspetaculosService;
import pt.isec.pd.as.pd.database.lugares.Lugares;
import pt.isec.pd.as.pd.database.lugares.LugaresRepository;
import pt.isec.pd.as.pd.database.utilizadores.Users;
import pt.isec.pd.as.pd.database.utilizadores.UsersRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservasService
{
    @Autowired private
    ReservasRepository connection;

    @Autowired private
    UsersRepository usersConnection;

    @Autowired private
    EspetaculosRepository espetaculosConnection;

    @Autowired private
    LugaresRepository lugaresConnection;


    public List<Reservas> listAllReservas() {
        return (List<Reservas>) connection.findAll();
    }

    public Reservas create(Reservas reserva){
        return connection.save(reserva);
    }

    public List<Reservas> getReservationsAwaitingPaymentConfirmation(int id_utilizador) {
        return connection.findByPagoAndAndUserId(0, id_utilizador);
    }

    public List<Reservas> getPaidReservations(int id_utilizador) {
        return connection.findByPagoAndAndUserId(1, id_utilizador);
    }

    public boolean markReservationAsPaid(int id) {
        Optional<Reservas> reservaOptional = connection.findById(id);

        if (reservaOptional.isPresent()) {
            Reservas reserva = reservaOptional.get();
            if (reserva.getPago() == 1) {
                return false;
            } else {
                reserva.setPago(1);
                connection.save(reserva);
                return true;
            }
        } else {
            return false;
        }
    }

    @Transactional
    public boolean deleteReservationIfNotPaid(int id) {
        Optional<Reservas> reservaOptional = connection.findById(id);

        if (reservaOptional.isPresent()) {
            Reservas reserva = reservaOptional.get();

            if (reserva.getPago() == 0) {
                connection.deleteById(id);
                return true;
            } else {
                return false; // Reservation is already paid, cannot delete
            }
        } else {
            return false; // Reservation not found
        }
    }

    public Reservas makeReservation(Reservas.ReservaRequest reservaRequest, int idUtilizador) {
        Reservas reserva = new Reservas();
        int idEspetaculo = reservaRequest.getIdEspetaculo();
        int idLugar = reservaRequest.getIdLugar();

        Optional<Espetaculos> espetaculosOptional = espetaculosConnection.findById(idEspetaculo);

        if (espetaculosOptional.isPresent()) {
            Espetaculos espetaculos = espetaculosOptional.get();
            reserva.setEspetaculo(espetaculos);

            Optional<Lugares> lugaresOptional = lugaresConnection.findById(idLugar);
            if (lugaresOptional.isPresent()) {
                Lugares lugar = lugaresOptional.get();

                // Add the user to the reservation
                Optional<Users> userOptional = usersConnection.findById(idUtilizador);
                userOptional.ifPresent(reserva::setUser);

//                Double preco = lugar.getPreco();

                reserva.addLugar(lugar);

//                lugar.setPreco(reservaRequest.getPreco());
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                reserva.setData_hora(formattedDateTime);

                reserva.setPago(0);
                return create(reserva);
            } else {
                throw new IllegalArgumentException("Lugar não existe.");
            }
        } else {
            throw new IllegalArgumentException("Espetaculo não existe.");
        }
    }
}
