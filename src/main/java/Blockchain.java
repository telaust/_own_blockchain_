import java.util.ArrayList;
import java.time.*;

public class Blockchain {

    private ArrayList<Block> blocks; // Chain declaration
    private final int difficulty = 2;

    private static ArrayList<Transaction> pendingTransactions; // implement like queue
    private final int miningReward = 100; // reward for mining success

    Blockchain(String initData) {

        blocks = new ArrayList<>();
        pendingTransactions = new ArrayList<>();
        createGenesis(initData);

    }

    private ArrayList<Block> createGenesis(String data) {
        blocks = new ArrayList<Block>();
        // Calculate time for timestamp field
        String stringDate = currentDate();

        blocks.add(new Block(stringDate, "Genesis Block", "0000"));

        return blocks;
    }

    public Block getLatestBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public ArrayList<Block> addBlock(String data) {
        String stringDate = currentDate();

        Block newBlock = new Block(stringDate, data, blocks.get(blocks.size() - 1).getHash() );
        newBlock.mineBlock(this.difficulty);

        blocks.add(newBlock);

        return blocks;
    }

    public void minePendingTransactions(String miningRewardAddr) { // wallet address
        Block block = new Block(currentDate(), "reward address", pendingTransactions, "0000");
        block.mineBlock(this.difficulty);

        System.out.println("block successfully mined......");
        blocks.add(block); // add to chain

        // "0" indicates that reward is getting out of system :: No Sender
        pendingTransactions.add(new Transaction("0", miningRewardAddr, this.miningReward));


    }


    public void createTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    public int getBalanceOfAddr(int address) {
        int balance = 0;

        for (Block block : blocks) { // Run over the blocks

            for (Transaction trans : block.transactions) { // Run over the transactions

                if (trans.getFromAddr().equals(address)) {
                    balance -= trans.getAmount();
                }

                if (trans.getToAddr().equals(address)) {
                    balance += trans.getAmount();
                }

            }



        }

        return balance;
    }



    public int size() {
        return blocks.size();
    }

    public String hashAt(int ind) {
        return blocks.get(ind).getHash();
    }

    public String prevHashAt(int ind) {
        return blocks.get(ind).getPreviousHash();
    }

    private String currentDate() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String strDate = date.toString();

        return strDate;
    }
}
