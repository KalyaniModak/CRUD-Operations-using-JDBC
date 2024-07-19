package bankAccount;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

	public void addAccount(Account acc)
	{
		String query = "insert into account (account_no, accholder, balance, time_stamp, pin) values (?,?,?,?,?)" ;
		
			try(Connection conn = CreateDBConnection.createConnection();PreparedStatement ps = conn.prepareStatement(query) ;) {

				ps.setString(1, acc.getAccountNo());
				ps.setString(2, acc.getAccountHolder());
				ps.setDouble(3, acc.getBalance());
				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				ps.setInt(5, acc.getPin());
				
				int rs = ps.executeUpdate() ;
				
				if(rs==1)
				{
					System.out.println("Account created successfully!");
				}
				
			} catch (SQLException e) {
				System.out.println("Issue in creating account!");
			} 
			catch (NullPointerException e) {
				
			}	
	}
	
	public Account getAccountByID(int id)
	{
		String query = "select * from account where account_id = ? " ;
		
		try(Connection conn = CreateDBConnection.createConnection() ;PreparedStatement ps = conn.prepareStatement(query) ;) {
		
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			int accID = 0;
			String accNo = null;
			String accHolder = null;
			double balance = 0.0 ;
			Timestamp ts = null;
			int pin = 0;
			
			while (rs.next()) {
				accID = rs.getInt(1) ;
				accNo = rs.getString(2) ;
				accHolder = rs.getString(3) ;
				balance = rs.getDouble(4) ;
				ts = rs.getTimestamp(5) ;
				pin = rs.getInt(6) ;
				
			}
			
			return new Account(accID,accNo,accHolder,balance,ts,pin) ;
			
		} catch (SQLException e) {
			System.out.println("Issue in fetching the account");
			return null ;
		}
		catch(NullPointerException e)
		{
			return null ;
		}
	}
	
	public List<Account> getAllAccounts()
	{
		String query = "select * from account" ;
		
		List<Account> list = new ArrayList<Account>() ;
		
		try(Connection conn = CreateDBConnection.createConnection() ;PreparedStatement ps = conn.prepareStatement(query) ;) {
			
			ResultSet rs = ps.executeQuery() ;
			
			while (rs.next()) {
				list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4) , rs.getTimestamp(5), rs.getInt(6))) ;
			}
			
		} catch (SQLException e) {
			System.out.println("Issue in fetching account details!");
		}
		return list ;
	}
	
	public void deleteAcc(int id)
	{
		String query = "delete from account where account_id = ? " ;
		
		try (Connection conn = CreateDBConnection.createConnection(); PreparedStatement ps = conn.prepareStatement(query);){
			ps.setInt(1, id);
			
			int del = ps.executeUpdate() ;
			if (del==1) {
				System.out.println("Account deleted successfully!");
			}
			else {
				System.out.println("Entered id is wrong!");
			}
		} catch (SQLException e) {
			System.out.println("Issue while deleting data!");
		}
	}
	
	public void update(String tableName, String colName, String value, int id)
	{
		
		String query = "update "+tableName+" set "+colName+" = ? where account_id=?" ;

		try (Connection conn = CreateDBConnection.createConnection(); PreparedStatement ps = conn.prepareStatement(query);){
			
			ps.setString(1, value);
			ps.setInt(2, id);
			
			int res = ps.executeUpdate() ;
			
			if (res==1) {
				System.out.println("Account updated successfully!");
			}
			else {
				System.out.println("Issue in updating the account details!");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Issue in updating the account!");
		}
	}
		
	public boolean debit(String accHolder, String accno, int pin,double amt)
	{
		String balQuery =" select balance from account where accHolder = ? and account_no = ? and pin = ?" ;
		
		try(Connection conn = CreateDBConnection.createConnection() ;PreparedStatement ps = conn.prepareStatement(balQuery) ;)
		{
			ps.setString(1, accHolder);
			ps.setString(2, accno);
			ps.setInt(3, pin);
			
			ResultSet rs = ps.executeQuery() ;
			
			rs.next() ;
			
			double balance = rs.getDouble(1) ;
			
			if (balance - amt > 0) {
				String updateQuery = "update account set balance = ? where account_no = ? and pin = ? " ;
				
				PreparedStatement stmt = conn.prepareStatement(updateQuery) ;
				
				stmt.setDouble(1, balance-amt);
				stmt.setString(2, accno);
				stmt.setInt(3, pin);
				
				int update = stmt.executeUpdate() ;
				
				if(update==1)
				{
					System.out.println("Amount debited!");
					return true ;
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("Issue in debiting the amount!");
		}
		return false ;
	}
	
	public void transferAmount(String name, String accno, int myPin, double amt, String receiverName, String receiverAccno)
	{
		
		try(Connection conn = CreateDBConnection.createConnection(); CallableStatement call = conn.prepareCall("{call debitBalance(?,?,?,?)}");
				CallableStatement call2 = conn.prepareCall("{call creditBalnce(?,?,?)}"))
		{
			call.setString(2, accno);
			call.setInt(3, myPin);
			call.setDouble(4, amt);
			
			call.execute();
			double debit = call.getDouble(1) ;
			
			if (debit>0) {
				call2.setString(2, receiverAccno);
				call2.setDouble(3, amt);
				call2.execute() ;
				double credit = call2.getDouble(1) ;
			}
			else {
				System.out.println("Issue in debit");
			}
		}
		catch(SQLException e)
		{
			System.out.println("Issue in money transfer!");
		}
		
	}
	
}
