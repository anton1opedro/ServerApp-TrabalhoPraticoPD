package pt.isec.pd.as.pd;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import pt.isec.pd.as.pd.seguranca.RsaKeysProperties;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;

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

    //@Bean(initMethod = "startServer", destroyMethod = "closeServerSocket")
    //@Bean
//    public void notificationServer() {
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(8000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        NotificationServer notificationServer = new NotificationServer(serverSocket);
//        System.out.println("AQUI NOTIFICATION SERVER");
//        notificationServer.run();
//    }

//    public void NotificationServer notificationServer() {
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(8000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        NotificationServer notificationServer = new NotificationServer(serverSocket);
//        notificationServer.startServer();
//    }


    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Por favor forneça o caminho da diretoria de armazenamento  da base de dados!");
            System.exit(1);
        }

        String databaseDirectory = args[0];
        String databaseUrl = "jdbc:sqlite:" + databaseDirectory + "/PD-2022-23-TP-EE.db";
        SpringApplication app = new SpringApplication(PdTpApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.datasource.url", databaseUrl));

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        NotificationServer notificationServer = new NotificationServer(serverSocket);
//        notificationServer.startServer();
        new Thread(new NotificationServer(serverSocket)).start();
        System.out.println("AQUI NA MAIN");
        app.run(args);

//        ServerSocket serverSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(8000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        NotificationServer notificationServer = new NotificationServer(serverSocket);
//        notificationServer.startServer();
    }

}
