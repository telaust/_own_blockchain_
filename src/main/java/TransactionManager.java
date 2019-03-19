
public class TransactionManager {

    private Transaction tr;
    private String fromAddr;
    private String toAddr;

    TransactionManager(Transaction tr, String fromAddr, String toAddr)
    {
        this.tr = tr;
        this.fromAddr = fromAddr;
        this.toAddr = toAddr;
    }


    // надо сделать , чтоб блок кода, отвечающий за перессылку транзакций был атомарный
    // ввести семафор для ликвидации дедлоков

}
