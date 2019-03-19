

public class App 
{
    public static void main( String[] args )
    {

//        Blockchain coin = new Blockchain("genesis block");
//
//        coin.createTransaction(new Transaction(1, 2, 100));
//        coin.createTransaction(new Transaction(2, 1, 100));
//        coin.createTransaction(new Transaction(1, 2, 200));
//
//        System.out.println("Start mining");
//        coin.minePendingTransactions(2);
//
//        System.out.println("balance of 2 is " + coin.getBalanceOfAddr(2));

        KeyGenerator gen = new KeyGenerator();

        System.out.println(gen.getPrivateKey() + "\n\n" + gen.getPublicKey());
    }
}
