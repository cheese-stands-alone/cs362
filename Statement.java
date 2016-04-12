import com.cs362.account.Account;
import java.util.ArrayList;

public class Statement {
    private List<Account> accountList;
    public Statement() {accountList = new ArrayList<Account>();}
    public void addAccount(Account account) {
        accountList.add(account);
    }
}