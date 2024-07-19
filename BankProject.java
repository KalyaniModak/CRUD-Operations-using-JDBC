package bankAccount;

import java.sql.Timestamp;
import java.util.List;

public class BankProject {

	static AccountDAO adao = new AccountDAO() ;
	
	public static void main(String[] args) {
		
//		adao.addAccount(new Account(0, "987654321", "Raju", 10000.00, new Timestamp(System.currentTimeMillis()), 1234));
//		adao.addAccount(new Account(0, "123459021", "Kaju", 30000.00, new Timestamp(System.currentTimeMillis()), 9784));
//		adao.addAccount(new Account(0, "357609001", "Moju", 18000.00, new Timestamp(System.currentTimeMillis()), 9082));
//		adao.addAccount(new Account(0, "6754309886", "Noju", 25000.00, new Timestamp(System.currentTimeMillis()), 9862));
//		adao.addAccount(new Account(0, "9182348533", "Waju", 12500.00, new Timestamp(System.currentTimeMillis()), 4523));

//		System.out.println(adao.getAccountByID(1)) ;
		
//		List<Account> ls = adao.getAllAccounts() ;
		
//		System.out.println(ls);
		
//		adao.deleteAcc(5);
//		for (Account acc : ls) {
//			System.out.println(acc);
//		}
		
//		adao.debit("Raju", "987654321", 1234, 300);
		
		adao.transferAmount("Raju", "987654321", 1234, 3000.00, "Moju", "357609001");
		
		adao.update("account","accHolder","Raju", 1);
		
	}
	
}
