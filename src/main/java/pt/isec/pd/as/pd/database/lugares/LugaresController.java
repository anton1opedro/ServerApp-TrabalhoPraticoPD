package pt.isec.pd.as.pd.database.lugares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isec.pd.as.pd.database.utilizadores.Users;

import java.util.List;

@RestController
@RequestMapping("/lugares")
public class LugaresController
{
    @Autowired
    LugaresService service;

//    @GetMapping
//    public String getLugares() {
//        List<Lugares> lugaresList = service.listAllLugares();
//        return lugaresList.toString();
//    }

    @GetMapping
    public ResponseEntity<List<Lugares>> getLugaresByEspetaculoId(@RequestParam int idEspetaculo) {
        List<Lugares> lugaresList = service.getLugaresByEspetaculoId(idEspetaculo);
        return ResponseEntity.ok().body(lugaresList);
    }

}
