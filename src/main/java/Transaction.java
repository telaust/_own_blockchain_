
// POJO

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Transaction {

    private String fromAddr;
    private String toAddr;
    private double amount;

    private String signature;

    Transaction(String fromAddr, String toAddr, double amount) {
        this.fromAddr = fromAddr;
        this.toAddr = toAddr;
        this.amount = amount;
    }

    public String calculateHash(){
        String originalString =
                (this.fromAddr) + (this.toAddr) + (this.amount);

        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    public void signTransaction(KeyGenerator signingKey) throws Exception {
        if ( !signingKey.getPublicKey().equals(this.fromAddr) )
            throw new Exception("you cannot sign transactions for other wallets.");


        String hash = calculateHash();
        signature  = signingKey.sign(hash, "Base64");

    }

    public boolean isValid() throws Exception{
        if (this.fromAddr.equals("0")) return true; // in the case of reward mining

        if (this.signature.equals(""))
            throw new Exception("No signature in this transaction");


    }

    public String getFromAddr() {
        return fromAddr;
    }

    public String getToAddr() {
        return toAddr;
    }

    public double getAmount() {
        return amount;
    }
}
