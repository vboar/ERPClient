/**
 * Payment逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import businesslogic.loginbl.Login;
import dataservice.datafactoryservice.DataFactoryImpl;

//oneoneO
public class Payment {
	
	public String createId(){
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMdd");
		String time=myFmt.format(date);
			ArrayList<PaymentVO> presentList=show();
			if(presentList.isEmpty()){
				return "FKD-"+time+"-00001";
			}else{
				String max=presentList.get(presentList.size()-1).id;
				String day=max.substring(4,max.length()-5);
				if(day.compareTo(time)<0){
				    return "FKD-"+time+"-00001";
				}
				String oldMax=max.substring(max.length()-5);
				int maxInt=Integer.parseInt(oldMax);
				String pattern="00000";
				 java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
				 String maxStr=df.format(maxInt+1);
				 return "FKD-"+time+"-"+maxStr;
			}
	}
	
	public ResultMessage createLog(String content){	
		//TODO
		return null;
	}
	
	public ResultMessage create(PaymentVO vo) {
		String time=Time.getCurrentTime();
		vo.id=createId();
		ArrayList<TransferLineItemPO> transferlist=new ArrayList<TransferLineItemPO>();
		for(int i=0;i<vo.transferList.size();i++){
			TransferLineItemVO t=vo.transferList.get(i);
			TransferLineItemPO temp=new TransferLineItemPO(t.name,t.bankAccount,t.account,t.remark);
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
		
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage approve(ArrayList<TransferLineItemVO> transferlist,String id,
			String customerId,double total){	
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
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();

		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().show();
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getDocumentType()==DocumentType.PAYMENT.ordinal()){
					result.add(poToVo(temp.get(i)));
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PaymentVO> findById(String id){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findById(id);
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getDocumentType()==DocumentType.PAYMENT.ordinal())
					result.add(poToVo(temp.get(i)));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PaymentVO> findByTime(String time1,String time2){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			time1=Time.jdugeTime1(time1);
			time2=Time.jdugeTime2(time2);
			temp=DataFactoryImpl.getInstance().getPaymentData().findByTime(time1, time2);
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getDocumentType()==DocumentType.PAYMENT.ordinal())
					result.add(poToVo(temp.get(i)));
			}
			
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<PaymentVO> findByCustomer(String customerId){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByCustomer(customerId);
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getDocumentType()==DocumentType.PAYMENT.ordinal())
					result.add(poToVo(temp.get(i)));
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PaymentVO> findByStatus(int status){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByStatus(status);
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getDocumentType()==DocumentType.PAYMENT.ordinal())
					result.add(poToVo(temp.get(i)));
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<PaymentVO> findByOperator(String operator){
		ArrayList<PaymentVO> result=new ArrayList<PaymentVO>();
		ArrayList<PaymentPO> temp=new ArrayList<PaymentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPaymentData().findByOperator(operator);
			
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getDocumentType()==DocumentType.PAYMENT.ordinal())
					result.add(poToVo(temp.get(i)));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//更新不通过引起的更新，只更新审批状态
	public ResultMessage update(PaymentVO vo){
		try {
			DataFactoryImpl.getInstance().getPaymentData().update(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	private PaymentPO voToPo(PaymentVO vo) {
		ArrayList<TransferLineItemPO> list=voListToPoList(vo.transferList);
		PaymentPO result=new PaymentPO(vo.id,vo.time,vo.customerId,vo.customerName,
				vo.operatorId,list,vo.total,vo.approvalState.ordinal(),vo.isWriteOff,vo.documentType.ordinal());
		return result;
	}

	private ArrayList<TransferLineItemPO> voListToPoList(
			ArrayList<TransferLineItemVO> list) {
		ArrayList<TransferLineItemPO> result=new ArrayList<TransferLineItemPO>();
		for(int i=0;i<list.size();i++){
			TransferLineItemVO temp=list.get(i);
			result.add(new TransferLineItemPO(temp.name,temp.bankAccount,temp.account,temp.remark));
		}
		return null;
	}

	public PaymentVO poToVo(PaymentPO po){
			ArrayList<TransferLineItemVO> temp=new ArrayList<TransferLineItemVO>();

			ArrayList<TransferLineItemPO> p=po.getTransferList();
		
			for(int j=0;j<p.size();j++){
				TransferLineItemPO t=p.get(j);
				temp.add(new TransferLineItemVO(t.getName(), t.getBankAccount(),t.getAccount(),t.getRemark()));
			}
			
			PaymentVO result=new PaymentVO(po.getId(),po.getTime(),po.getCustomerId(),po.getCustomerName(),po.getOperatorId(),
					temp,po.getTotal(),DocumentStatus.values()[po.getApprovalStatus()],
					po.getIsWriteOff(),DocumentType.values()[po.getDocumentType()]);
		
		return result;
	}
		
	public PaymentVO getById(String id){
		PaymentVO result=null;
		
		try {
			result=poToVo(DataFactoryImpl.getInstance().getPaymentData().getById(id));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return result;
	}
}
