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
    public List<Reservas> getReservationsAwaitingPaymentConfitmation() {
        return service.getReservationsAwaitingPaymentConfirmation();
    }

    @GetMapping("/pagas")
    public List<Reservas> getPaidReservations() {
        return service.getPaidReservations();
    }

//    @DeleteMapping("/apaga-reserva/{id}")
//    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
//        if (service.deleteReservation(id)) {
//            return ResponseEntity.ok("Reservation deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping("/criar-reserva")
    public ResponseEntity<String> makeReservations(@RequestBody Reservas.ReservaRequest reservaRequest) {
        try {
            Reservas reserva = new Reservas();
            int idEspetaculo = reservaRequest.getIdEstpetaculo();
            //Espetaculos espetaculos = EspetaculosRepository.findById(idEspetaculo).orElseThrow(() -> new IllegalArgumentException("Invalid espetaculo ID: " + idEspetaculo));
            Optional<Espetaculos> espetaculosOptional = espetaculosService.findById(idEspetaculo);

            if (!espetaculosOptional.isEmpty()) {
                Espetaculos espetaculos = espetaculosOptional.get();

                reserva.setEspetaculo(espetaculos);

                Lugares lugar = new Lugares();
                lugar.setAssento(reservaRequest.getAssento());
                lugar.setFila(reservaRequest.getFila());

                Double preco = lugar.getPreco();

                reserva.addLugar(lugar);

                lugar.setPreco(reservaRequest.getPreco());
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                reserva.setData_hora(formattedDateTime);

                Reservas createdReserva = service.create(reserva);
                return ResponseEntity.ok("Reservation created successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Espetaculo não existe.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
