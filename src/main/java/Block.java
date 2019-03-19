import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Block {

    private String timestamp;
    private String data;
    private String previousHash;
    private String hash;
    private int nonce = 0;
    public static ArrayList<Transaction> transactions;

    Block(String timestamp, String data, String previousHash) {
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
    }

    Block(String timestamp, String data, ArrayList<Transaction> transactions, String previousHash) {
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
        this.transactions = transactions;
    }

    protected String getHash() {
        return this.hash;
    }

    protected String getPreviousHash() {
        return this.previousHash;
    }

    public String calculateHash() {
        // i dont need no convert data to String 'cause data is String
        String originalString =
                this.previousHash + this.timestamp + data + String.valueOf(nonce);

        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    // Proof-of-Work
    public void mineBlock(int difficulty) {
        // create a String full of zeros
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            sb.append("0");
        }
        String str = sb.toString();



        while (!this.hash.substring(0, difficulty).equals(str)) {
            this.nonce++;
            this.hash = this.calculateHash();
        }

        System.out.println("Block mined__________" + this.hash);
    }


}
