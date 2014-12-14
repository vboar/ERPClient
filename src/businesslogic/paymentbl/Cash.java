/**
 * Cash类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.CashPO;
import po.ClauseLineItemPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.AccountVO;
import vo.CashVO;
import vo.ClauseLineItemVO;
import businesslogic.accountbl.Account;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Cash{

	public String createId(){
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMdd");
		String time=myFmt.format(date);
			ArrayList<CashVO> presentList=show();
			if(presentList.isEmpty()){
				return "XJFYD-"+time+"-00001";
			}else{
				String max=presentList.get(presentList.size()-1).id;
				String day=max.substring(4,max.length()-5);
				if(day.compareTo(time)<0){
				    return "XJFYD-"+time+"-00001";
				}
				String oldMax=max.substring(max.length()-5);
				int maxInt=Integer.parseInt(oldMax);
				String pattern="00000";
				 java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
				 String maxStr=df.format(maxInt+1);
				 return "XJFYD-"+time+"-"+maxStr;
			}
	}
	
	public ResultMessage createLog(String content){	
		//TODO
		return null;	
	}
	
	public ResultMessage add(CashVO vo){
		vo.time=Time.getCurrentTime();
		try {
			DataFactoryImpl.getInstance().getCashDataService().insert(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ArrayList<CashVO> findById(String id){
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		ArrayList<CashPO> temp=new ArrayList<CashPO>();
		try {
			temp=DataFactoryImpl.getInstance().getCashDataService().findById(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++){
			result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	public ArrayList<CashVO> findByStatus(int status){
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		ArrayList<CashPO> temp=new ArrayList<CashPO>();
		try {
			temp=DataFactoryImpl.getInstance().getCashDataService().findByStatus(status);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++){
			result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	public ArrayList<CashVO> findByTime(String time1,String time2){
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		ArrayList<CashPO> temp=new ArrayList<CashPO>();
		try {
			temp=DataFactoryImpl.getInstance().getCashDataService().findByTime(time1, time2);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++){
			result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	public ArrayList<CashVO> show(){
		ArrayList<CashVO> result=new ArrayList<CashVO>();
		ArrayList<CashPO> temp=new ArrayList<CashPO>();
		try {
			temp=DataFactoryImpl.getInstance().getCashDataService().show();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++){
			result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	//修改公司账户余额
	public ResultMessage approve(CashVO vo){
		Account a=new Account();
		AccountVO avo=a.findByAccount(vo.bankAccount);
		avo.balance=avo.balance-vo.total;
		a.update(avo);
		return ResultMessage.SUCCESS;
	}
	public CashPO voToPo(CashVO vo) {
		ArrayList<ClauseLineItemPO> clauseList=voListTOpoList(vo.clauseList);
		CashPO result=new CashPO(vo.id,vo.time,vo.operator,vo.bankAccount,clauseList,vo.total,
				vo.approvalState.ordinal(),vo.isWriteOff,vo.documentType.ordinal());
		
		return result;
	}

	public ArrayList<ClauseLineItemPO> voListTOpoList(ArrayList<ClauseLineItemVO> clauseList) {
		ArrayList<ClauseLineItemPO> result=new ArrayList<ClauseLineItemPO>();
		for(int i=0;i<clauseList.size();i++){
			ClauseLineItemVO temp=clauseList.get(i);
			result.add(new ClauseLineItemPO(temp.name,temp.account,temp.remark));
		}
		return null;
	}

	public CashVO poToVo(CashPO po){
		ArrayList<ClauseLineItemVO> clauseList=poListTOvoList(po.getClauseList());
		CashVO result=new CashVO(po.getId(),po.getOperatorId(),po.getBankAccount(),po.getTime(),
				clauseList,po.getTotal(),DocumentStatus.values()[po.getDocumentStatus()],
				po.isWriteOff(),DocumentType.values()[po.getDocumentType()]);
		
		return result;
	}

	public ArrayList<ClauseLineItemVO> poListTOvoList(ArrayList<ClauseLineItemPO> clauseList) {
		ArrayList<ClauseLineItemVO> result=new ArrayList<ClauseLineItemVO>();
		for(int i=0;i<clauseList.size();i++){
			ClauseLineItemPO temp=clauseList.get(i);
			result.add(new ClauseLineItemVO(temp.getName(),temp.getAccount(),temp.getRemark()));
		}
		return result;
	}
	
	public CashVO getById(String id){
		CashVO result=null;
		
		try {
			result=poToVo(DataFactoryImpl.getInstance().getCashDataService().getById(id));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return result;
	}
}
