package pt.isec.pd.as.pd.database.lugares;

import jakarta.persistence.*;
import pt.isec.pd.as.pd.database.espetaculos.Espetaculos;
import pt.isec.pd.as.pd.database.reservas.Reservas;


@Entity
@Table(name = "lugar")
public class Lugares
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "espetaculo_id", nullable = false)
    private Espetaculos espetaculo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservas reserva;

    @Column(nullable = false)
    private String fila;

    @Column(nullable = false)
    private String assento;

    @Column(nullable = false)
    private double preco;

    public Long getId() {
        return id;
    }

    public Espetaculos getEspetaculo() {
        return espetaculo;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}