/**
 * Receipt逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.AccountVO;
import vo.TransferLineItemVO;
import businesslogic.accountbl.Account;
import businesslogic.customerbl.Customer;

public class Receipt {
	
	public ResultMessage approve(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){
		Account a=new Account();
		Customer c=new Customer();
		
		for(int i=0;i<transferlist.size();i++){
			AccountVO temp;
			temp = a.findByAccount(transferlist.get(i).bankAccount);
			temp.balance=temp.balance+transferlist.get(i).account;
			a.update(temp);
		}
		
		c.updateByReceipt(customerId, total);
		return ResultMessage.SUCCESS;
	} 
}
