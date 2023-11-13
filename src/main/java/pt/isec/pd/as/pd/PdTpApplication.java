package pt.isec.pd.as.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pt.isec.pd.as.pd.database.utilizadores", "pt.isec.pd.as.pd.database.espetaculos", "pt.isec.pd.as.pd.database.lugares", "pt.isec.pd.as.pd.database.reservas", "pt.isec.pd.as.pd.api.controller"})
public class PdTpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdTpApplication.class, args);
    }

}
