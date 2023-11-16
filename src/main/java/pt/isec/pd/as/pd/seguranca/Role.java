package pt.isec.pd.as.pd.seguranca;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // types of roles
    private String authority;
    public Role(){
        super();
    }
    public Role(String authority){
        this.authority = authority;
    }

    public Role(Long i, String authority) {
        this.id = i;
        this.authority = authority;
    }


    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(Long id) {
        return this.id;
    }

}
