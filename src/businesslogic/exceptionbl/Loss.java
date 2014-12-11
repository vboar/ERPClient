/**
 * loss逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.exceptionbl;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.ExceptionLineItemPO;
import po.ExceptionPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;
import businesslogic.commoditybl.Commodity;
import businesslogic.logbl.Log;
import dataservice.datafactoryservice.DataFactoryImpl;


public class Loss {
	public ResultMessage addLog(String content){
		Log l=new Log();
		return l.add(content);
	}
	
	public ResultMessage add(ExceptionVO vo){
		try {
			DataFactoryImpl.getInstance().getExceptionData().insert(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<ExceptionVO> show(String time1,String time2){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		ArrayList<ExceptionPO> temp=new ArrayList<ExceptionPO>();
		try {
			if(time1!=null&&time2!=null)
			temp=DataFactoryImpl.getInstance().getExceptionData().show(time1, time2);
			
			if(time1==null&&time2!=null)
				temp=DataFactoryImpl.getInstance().getExceptionData().show("1970/1/1/0/0/0",time2);
			
			if(time1!=null&&time2==null)
				temp=DataFactoryImpl.getInstance().getExceptionData().show(time1,Time.getCurrentTime());
			
			if(time1==null&&time2==null)
				temp=DataFactoryImpl.getInstance().getExceptionData().show("1970/1/1/0/0/0",Time.getCurrentTime());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.LOSS.ordinal())
				result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	public ArrayList<ExceptionVO> findById(String id){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		ArrayList<ExceptionPO> temp=new ArrayList<ExceptionPO>();
		try {
			temp=DataFactoryImpl.getInstance().getExceptionData().findById(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.LOSS.ordinal())
				result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}

	public ArrayList<ExceptionVO> findByStatus(int status){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		ArrayList<ExceptionPO> temp=new ArrayList<ExceptionPO>();
		try {
			temp=DataFactoryImpl.getInstance().getExceptionData().findByStatus(status);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.LOSS.ordinal())
				result.add(poToVo(temp.get(i)));
		}
		
		return result;
	}
	
	public ResultMessage approve(ExceptionVO vo){
		//修改单据状态
		try {
			DataFactoryImpl.getInstance().getExceptionData().update(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		Commodity c=new Commodity();
		c.approveException(vo);
	
		return ResultMessage.SUCCESS;
	}
	
	public ExceptionPO voToPo(ExceptionVO vo){
		ArrayList<ExceptionLineItemPO> l=new ArrayList<ExceptionLineItemPO>();
		for(int i=0;i<vo.list.size();i++){
			ExceptionLineItemVO temp=vo.list.get(i);
			l.add(new ExceptionLineItemPO(temp.id,temp.name,temp.model,temp.systemNumber,temp.actualNumber));
		}
		ExceptionPO result=new ExceptionPO(vo.id, vo.time, l,vo.status.ordinal(), vo.type.ordinal(), vo.isWriteoff);
		return result;
	}

	public ExceptionVO poToVo(ExceptionPO po) {
		ArrayList<ExceptionLineItemVO> l=new ArrayList<ExceptionLineItemVO>();
		for(int i=0;i<po.getList().size();i++){
			ExceptionLineItemPO temp=po.getList().get(i);
			l.add(new ExceptionLineItemVO(temp.getId(),temp.getName(),temp.getModel(),temp.getSystemNumber(),temp.getActualNumber()));
		}
		ExceptionVO result=new ExceptionVO(po.getId(),po.getTime(),l,DocumentStatus.values()[po.getDocumentStatus()],
				DocumentType.values()[po.getDocumentType()],po.isWriteoff());
		return result;
	}
	
	public ResultMessage update(ExceptionVO vo){
		try {
			DataFactoryImpl.getInstance().getExceptionData().update(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public String createId(){
		String result="";
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		String time=df.format(new Date());
		ArrayList<ExceptionVO> presentList=show("1970/1/1/0/0/0",Time.getCurrentTime());
		if(presentList.size()==0){
			return "BSD-"+time+"-00001";
		}else{
			ExceptionVO max=presentList.get(presentList.size()-1);
			String maxId=max.id;
			String maxTime=maxId.substring(4,12);
			if(maxTime.compareTo(time)<0){
				return "BSD-"+time+"-00001";
			}else{
				int maxnumber=Integer.parseInt(maxId.substring(13));
				DecimalFormat f=new DecimalFormat("00000");
				String newMax=f.format(maxnumber+1);
				result="BSD-"+time+"-"+newMax;
			}
		}
		return result;
	}
}
