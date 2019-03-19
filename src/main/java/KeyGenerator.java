import java.security.*;
import java.util.Base64;


// Bouncy Castle here
public class KeyGenerator {

    private String publicKey;
    private String privateKey;


    KeyGenerator() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");

            // Initialize KeyPairGenerator.
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            // Generate a private key and a public key.
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            Base64.Encoder encoder = Base64.getEncoder();

            this.privateKey = encoder.encodeToString(privateKey.getEncoded());
            this.publicKey =  encoder.encodeToString(publicKey.getEncoded());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }


}
