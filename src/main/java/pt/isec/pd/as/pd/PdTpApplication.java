package pt.isec.pd.as.pd;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pt.isec.pd.as.pd.seguranca.RsaKeysProperties;

@SpringBootApplication
@ComponentScan(basePackages = {
        "pt.isec.pd.as.pd.database.utilizadores",
        "pt.isec.pd.as.pd.database.espetaculos",
        "pt.isec.pd.as.pd.database.lugares",
        "pt.isec.pd.as.pd.database.reservas",
        "pt.isec.pd.as.pd.api.controller",
        "pt.isec.pd.as.pd.seguranca"

})
public class PdTpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdTpApplication.class, args);
    }

}
