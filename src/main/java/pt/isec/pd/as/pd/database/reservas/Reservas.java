package pt.isec.pd.as.pd.database.reservas;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import pt.isec.pd.as.pd.database.espetaculos.Espetaculos;
import pt.isec.pd.as.pd.database.lugares.Lugares;
import pt.isec.pd.as.pd.database.utilizadores.Users;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reservas
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_utilizador", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_espetaculo", nullable = false)
    private Espetaculos espetaculo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "reserva_lugar",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_lugar")
    )
    private List<Lugares> lugares;

    @Column(nullable = false)
    private String data_hora;

    @Column(nullable = false)
    @Value("${some.key:0")
    private int pago;

    public void setEspetaculo(Espetaculos espetaculo) {
        this.espetaculo = espetaculo;
    }

    public Reservas() {
        this.lugares = new ArrayList<>();
    }

    public void addLugar(Lugares lugar) {
        this.lugares.add(lugar);
    } //TODO VER ONDE É INVOCADO O QUE ESTOU A PASSAR

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public static class ReservaRequest {
        private int getIdEspetaculo;
        private int idLugar;

        public int getIdEspetaculo() {
            return getIdEspetaculo;
        }

        public int getIdLugar() {
            return idLugar;
        }

        public void setIdLugar(int idLugar) {
            this.idLugar = idLugar;
        }

        public void setIdEstpetaculo(int idEstpetaculo) {
            this.getIdEspetaculo = idEstpetaculo;
        }

    }
}

