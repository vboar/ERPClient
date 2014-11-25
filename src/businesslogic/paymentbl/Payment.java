/**
 * Payment逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.datafactoryservice.DataFactoryImpl;
import po.AccountPO;
import po.PaymentPO;
import po.TransferLineItemPO;
import businesslogic.accountbl.Account;
import businesslogic.accountbl.MockLog;
import businesslogic.logbl.Log;
import businesslogic.loginbl.Login;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.PaymentVO;
import vo.TransferLineItemVO;

//oneoneO
public class Payment {

	MockCustomer customer;
	
	MockAccount account;
	
	public Payment(MockCustomer customer, MockAccount account){
		this.customer = customer;
		this.account = account;	
	}
	
	public Payment(){
		
	}
	
	public ResultMessage createLog(String content){	
		MockLog log = new MockLog(content);
		return log.add();
	}
	
	//真逻辑开始
	public ResultMessage add(PaymentVO vo) throws RemoteException{
		String time=Time.getCurrentTime();
		ArrayList<TransferLineItemPO> transferlist=new ArrayList<TransferLineItemPO>();
		for(int i=0;i<vo.transferList.size();i++){
			TransferLineItemVO t=vo.transferList.get(i);
			TransferLineItemPO temp=new TransferLineItemPO(t.bankAccount,t.account,t.remark);
			transferlist.add(temp);
		}
		
		DataFactoryImpl.getInstance().getPaymentData().insert(new PaymentPO(vo.id,time,
				vo.customerId,vo.customerName,Login.currentUserId,transferlist,
				vo.total,vo.approvalState.ordinal(),vo.isWriteOff,vo.documentType.ordinal()));

		//???
		Log l=new Log();
		l.add("Add payment successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(double money,String account) throws RemoteException{
		Account acc=new Account();
		AccountPO temp=acc.findByAccount(account).get(0);
		temp.setBalance(temp.getBalance()-money);
		acc.update(temp);
		return ResultMessage.SUCCESS;
	}

	public ArrayList<PaymentVO> findById(String id) throws RemoteException{
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findById(id));
		return result;
	}

	public ArrayList<PaymentVO> findByTime(String time1,String time2) throws RemoteException{
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByTime(time1, time2));
		return result;
	}
	
	public ArrayList<PaymentVO> findByCustomer(String customerId) throws RemoteException{
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByCustomer(customerId));
		return result;
	}
	
	public ArrayList<PaymentVO> findByOperator(String operator) throws RemoteException{
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByOperator(operator));
		return result;
	}
	
	public ArrayList<PaymentVO> poToVo(ArrayList<PaymentPO> po){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		for(int i=0;i<po.size();i++){
			ArrayList<TransferLineItemVO> temp=new ArrayList<TransferLineItemVO>();
			ArrayList<TransferLineItemPO> p=po.get(i).getTransferList();
			for(int j=0;j<p.size();j++){
				TransferLineItemPO t=p.get(j);
				temp.add(new TransferLineItemVO(t.getBankAccount(),t.getAccount(),t.getRemark()));
			}
			PaymentPO temp2=po.get(i);
			result.add(new PaymentVO(temp2.getId(),temp2.getCustomerId(),temp2.getCustomerName(),temp2.getOperatorId(),temp,temp2.getTotal(),DocumentStatus.values()[temp2.getApprovalStatus()],DocumentType.values()[temp2.getDocumentType()]));
		}
		
		return result;
	}
}
