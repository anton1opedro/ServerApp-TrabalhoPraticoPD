package pt.isec.pd.as.pd.database.espetaculos;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name = "espetaculo")
public class Espetaculos
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, name = "data_hora")
    private String datahora;

    @Column(nullable = false)
    private int duracao;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private String localidade;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String classificacao_etaria;

    @Column(nullable = false)
    @Value("${some.key:0")
    private int visivel;

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData_hora() {
        return datahora;
    }

    public void setData_hora(String data_hora) {
        this.datahora = data_hora;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClassificacao_etaria() {
        return classificacao_etaria;
    }

    public void setClassificacao_etaria(String classificacao_etaria) {
        this.classificacao_etaria = classificacao_etaria;
    }

    public int getVisivel() {
        return visivel;
    }

    public void setVisivel(int visivel) {
        this.visivel = visivel;
    }
}
