package pt.isec.pd.as.pd.seguranca;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//@Configuration
//@ConfigurationProperties(prefix = "rsa")
//@ConstructorBinding
@Component
public class RsaKeysProperties {
    public RSAPublicKey publicKey;
    public RSAPrivateKey privateKey;

    public RsaKeysProperties() {
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
    }
    public RSAPrivateKey getPrivateKey(){
        return this.privateKey;
    }
    public RSAPublicKey getPublicKey(){
        return this.publicKey;
    }

}
