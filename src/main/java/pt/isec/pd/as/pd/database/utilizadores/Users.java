package pt.isec.pd.as.pd.database.utilizadores;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Entity
@Table(name = "utilizador")
public class Users
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Value("${some.key:0")
    private int administrador;

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static class UserDTO {
        private String nome;
        private String username;
        private String password;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
