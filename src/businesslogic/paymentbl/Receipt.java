/**
 * Receipt逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentPO;
import po.TransferLineItemPO;
import dataservice.datafactoryservice.DataFactoryImpl;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.AccountVO;
import vo.PaymentVO;
import vo.TransferLineItemVO;
import businesslogic.accountbl.Account;
import businesslogic.customerbl.Customer;

public class Receipt {
	
	public ResultMessage add(PaymentVO vo){
		try {
			DataFactoryImpl.getInstance().getPaymentData().insert(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<PaymentVO> show(){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().show();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.RECEIPT.ordinal())
				result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> findByTime(String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByTime(time1, time2);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.RECEIPT.ordinal()){
				result.add(poToVo(temp.get(i)));
			}
		}
		return result;
	}
	
	public ArrayList<PaymentVO> findByStatus(int status){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByStatus(status);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.RECEIPT.ordinal()){
				result.add(poToVo(temp.get(i)));
			}
		}
		return result;
	} 
	
	public ArrayList<PaymentVO> findById(String id){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findById(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.RECEIPT.ordinal()){
				result.add(poToVo(temp.get(i)));
			}
		}
		return result;
	} 
	
	public ArrayList<PaymentVO> findByCustomer(String customerId){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByCustomer(customerId);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.RECEIPT.ordinal()){
				result.add(poToVo(temp.get(i)));
			}
		}
		return result;
	} 
	
	public ArrayList<PaymentVO> findByOperator(String operator){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByOperator(operator);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.RECEIPT.ordinal()){
				result.add(poToVo(temp.get(i)));
			}
		}
		return result;
	} 
	
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
	
	public PaymentPO voToPo(PaymentVO vo){
		ArrayList<TransferLineItemPO> transferList=voListTOpoList(vo.transferList);
		PaymentPO result=new PaymentPO(vo.id,vo.time,vo.customerId,vo.customerName,
				vo.operatorId,transferList,vo.total,vo.approvalState.ordinal(),vo.isWriteOff,
				vo.documentType.ordinal());
		return result;
	}
	
	public ArrayList<TransferLineItemPO> voListTOpoList(ArrayList<TransferLineItemVO> transferList){
		ArrayList<TransferLineItemPO> result=new ArrayList<TransferLineItemPO>();
		for(int i=0;i<transferList.size();i++){
			TransferLineItemVO temp=transferList.get(i);
			result.add(new TransferLineItemPO(temp.bankAccount,temp.account,temp.remark));
		}
		return result;
	}
	
	//String id, String customerId, String customerName,
	//String operatorId, ArrayList<TransferLineItemVO> transferList,
	//double total, DocumentStatus approvalState,
	//DocumentType documentType
	public PaymentVO poToVo(PaymentPO po){
		ArrayList<TransferLineItemVO> transferList=poListTOvoList(po.getTransferList());
		PaymentVO result=new PaymentVO(po.getId(),po.getTime(),po.getCustomerId(),
				po.getCustomerName(),po.getOperatorId(),transferList,po.getTotal(),
				DocumentStatus.values()[po.getApprovalStatus()],po.getIsWriteOff(),
				DocumentType.values()[po.getDocumentType()]);
		return result;
	} 
	
	public ArrayList<TransferLineItemVO> poListTOvoList(ArrayList<TransferLineItemPO> transferList){
		ArrayList<TransferLineItemVO> result=new ArrayList<TransferLineItemVO>();
		for(int i=0;i<transferList.size();i++){
			TransferLineItemPO temp=transferList.get(i);
			result.add(new TransferLineItemVO(temp.getBankAccount(),temp.getAccount(),temp.getRemark()));
		}
		return result;
	}
}
