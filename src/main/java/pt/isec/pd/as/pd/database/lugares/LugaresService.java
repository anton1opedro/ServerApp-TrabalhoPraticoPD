package pt.isec.pd.as.pd.database.lugares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugaresService
{
    @Autowired
    private LugaresRepository connection;

    public List<Lugares> listAllLugares(){ return (List<Lugares>) connection.findAll(); }

    public Lugares create (Lugares lugar) {
        return connection.save(lugar);
    }

    public List<Lugares> getLugaresByEspetaculoId(int idEspetaculo) {
        return connection.findByEspetaculoId(idEspetaculo);
    }
}
