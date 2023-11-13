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
    private Long id;

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

    public static class ReservaRequest {
        private int idEstpetaculo;
        private int idLugar;
        private Double preco;
        private String fila;
        private String assento;

        public int getIdEstpetaculo() {
            return idEstpetaculo;
        }

        public int getIdLugar() {
            return idLugar;
        }

        public void setIdLugar(int idLugar) {
            this.idLugar = idLugar;
        }

        public void setIdEstpetaculo(int idEstpetaculo) {
            this.idEstpetaculo = idEstpetaculo;
        }

        public Double getPreco() {
            return preco;
        }

        public void setPreco(Double preco) {
            this.preco = preco;
        }

        public String getFila() {
            return fila;
        }

        public void setFila(String fila) {
            this.fila = fila;
        }

        public String getAssento() {
            return assento;
        }

        public void setAssento(String assento) {
            this.assento = assento;
        }
    }
}

