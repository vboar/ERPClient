/**
 * Payment逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentPO;
import po.TransferLineItemPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.AccountVO;
import vo.PaymentVO;
import vo.TransferLineItemVO;
import businesslogic.accountbl.Account;
import businesslogic.customerbl.CustomerController;
import businesslogic.logbl.Log;
import businesslogic.loginbl.Login;
import dataservice.datafactoryservice.DataFactoryImpl;

//oneoneO
public class Payment {
	
	public ResultMessage createLog(String content){	
		//TODO
		return null;
	}
	
	//真逻辑开始
	public ResultMessage create(PaymentVO vo) {
		String time=Time.getCurrentTime();
		ArrayList<TransferLineItemPO> transferlist=new ArrayList<TransferLineItemPO>();
		for(int i=0;i<vo.transferList.size();i++){
			TransferLineItemVO t=vo.transferList.get(i);
			TransferLineItemPO temp=new TransferLineItemPO(t.bankAccount,t.account,t.remark);
			transferlist.add(temp);
		}
		
		try {
			DataFactoryImpl.getInstance().getPaymentData().insert(new PaymentPO(vo.id,time,
					vo.customerId,vo.customerName,Login.currentUserId,transferlist,
					vo.total,vo.approvalState.ordinal(),vo.isWriteOff,vo.documentType.ordinal()));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		//???
		Log l=new Log();
		l.add("Add payment successfully");
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage approve(ArrayList<TransferLineItemVO> transferlist,String id,String customerId,double total){	
		//修改公司账户金额，修改客户应收应付
		Account acc=new Account();
		CustomerController c=new CustomerController();
		
		for(int i=0;i<transferlist.size();i++){
		AccountVO temp;
		temp =acc.findByAccount(transferlist.get(i).bankAccount);
		temp.balance=temp.balance-transferlist.get(i).account;
		acc.update(temp);
		}
		
		c.updateByPayment(customerId, total);
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<PaymentVO> show(){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().show());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PaymentVO> findById(String id){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findById(id));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PaymentVO> findByTime(String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByTime(time1, time2));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PaymentVO> findByCustomer(String customerId){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByCustomer(customerId));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PaymentVO> findByStatus(int status){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByStatus(status));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PaymentVO> findByOperator(String operator){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().findByOperator(operator));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
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
