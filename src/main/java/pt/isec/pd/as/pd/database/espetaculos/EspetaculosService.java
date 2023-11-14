package pt.isec.pd.as.pd.database.espetaculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EspetaculosService
{
    @Autowired
    private EspetaculosRepository connection;

    public List<Espetaculos> listAllEspetaculos() {
        return (List<Espetaculos>) connection.findAll();
    }

    public List<Espetaculos> searchEspetaculos(String pais, String local, String tipo, String data_hora) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if (data_hora != null) {
                date = formatter.parse(data_hora);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parse exception
        }

        if (pais != null) {
            return connection.findByPais(pais);
        } else if (local != null) {
            return connection.findByLocal(local);
        } else if (tipo != null) {
            return connection.findByTipo(tipo);
        } else if (date != null) {
            return connection.findByDatahora(data_hora);
        } else {
            // No search criteria provided, return all shows
            return listAllEspetaculos();
        }
    }

    public boolean checkEspetaculos(int idEspetaculo) {
        if (!(connection.findById(idEspetaculo)).isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public Optional<Espetaculos> findById(int id) {
        return connection.findById(id);
    }

    public Espetaculos adiciona(Espetaculos espetaculo){
        return connection.save(espetaculo);
    }
}
