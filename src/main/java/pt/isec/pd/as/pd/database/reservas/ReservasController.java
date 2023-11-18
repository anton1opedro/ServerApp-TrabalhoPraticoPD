package pt.isec.pd.as.pd.database.reservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.as.pd.database.espetaculos.Espetaculos;
import pt.isec.pd.as.pd.database.espetaculos.EspetaculosRepository;
import pt.isec.pd.as.pd.database.espetaculos.EspetaculosService;
import pt.isec.pd.as.pd.database.lugares.Lugares;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservasController
{
    @Autowired private
    ReservasService service;

    @Autowired private
    EspetaculosService espetaculosService;

    @GetMapping
    public String getAllReservas(){
        List<Reservas> reservasList = service.listAllReservas();
        return reservasList.toString();
    }

    @GetMapping("/nao-pagas")
    public List<Reservas> getReservationsAwaitingPaymentConfitmation(@RequestParam int id_utilizador) {
        return service.getReservationsAwaitingPaymentConfirmation(id_utilizador);
    }

    @GetMapping("/pagas")
    public List<Reservas> getPaidReservations(@RequestParam int id_utilizador) {
        return service.getPaidReservations(id_utilizador);
    }

    @PutMapping("/{id}/pagamento")
    public ResponseEntity<String> payReservation(@PathVariable int id) {
        if (service.markReservationAsPaid(id)) {
            return ResponseEntity.ok("Reservation marked as paid.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Reservation could not be paid.");
        }
    }

    @DeleteMapping("/apaga-reserva/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        if (service.deleteReservationIfNotPaid(id)) {
            return ResponseEntity.ok("Reservation deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unable to delete the reservation.");
        }
    }

    @PostMapping("/criar-reserva")
    public ResponseEntity<String> criarReserva(
            @RequestBody Reservas.ReservaRequest reservaRequest,
            @RequestParam int idUtilizador) {

        try {
            Reservas createdReserva = service.makeReservation(reservaRequest, idUtilizador);
            return ResponseEntity.ok("Reservation created successfully with ID: " + createdReserva.getUser().getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
