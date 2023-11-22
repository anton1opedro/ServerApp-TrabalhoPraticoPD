package pt.isec.pd.as.pd.database.espetaculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/espetaculos")
public class EspetaculosController
{
    @Autowired private EspetaculosService service;

    @GetMapping
    public ResponseEntity<List<Espetaculos>> getAllEspetaculos() {
        List<Espetaculos> espetaculosList = service.listAllEspetaculos();
        return ResponseEntity.ok().body(espetaculosList);
    }


    @GetMapping("/")
    public List<Espetaculos> searchEspetaculos(@RequestParam(required = false) String pais,
                                               @RequestParam(required = false) String local,
                                               @RequestParam(required = false) String tipo,
                                               @RequestParam(required = false) String data_hora) {
        return service.searchEspetaculos(pais, local, tipo, data_hora);
    }

    @GetMapping("/check")
    public boolean checkEspetaculos (@RequestParam int idEspetaculo) {
        if (service.checkEspetaculos(idEspetaculo)) {
            return true;
        } else {
            return false;
        }
    }
}

