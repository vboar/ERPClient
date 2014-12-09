/**
 * overflow逻辑类
 */

package businesslogic.exceptionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ExceptionLineItemPO;
import po.ExceptionPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;
import businesslogic.commoditybl.Commodity;
import dataservice.datafactoryservice.DataFactoryImpl;


public class Overflow {
	
	//报益报损单审批通过，修改商品数量
	public ResultMessage approve(ExceptionVO vo){
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
	
	public ArrayList<ExceptionVO> findByTime(String time1,String time2){
		ArrayList<ExceptionVO> result=new ArrayList<ExceptionVO>();
		ArrayList<ExceptionPO> temp=new ArrayList<ExceptionPO>();
		
		try {
			temp=DataFactoryImpl.getInstance().getExceptionData().show(time1, time2);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getDocumentType()==DocumentType.OVERFLOW.ordinal())
				result.add(poToVo(temp.get(i)));
		}
		return result;
	}
	
	public ExceptionVO poToVo(ExceptionPO po) {
		ArrayList<ExceptionLineItemVO> list=poListToVoList(po.getList()); 
		ExceptionVO result=new ExceptionVO(po.getId(),po.getTime(),list,DocumentStatus.values()
				[po.getDocumentStatus()],DocumentType.values()[po.getDocumentType()],po.isWriteoff());
		return result;
	}

	public ArrayList<ExceptionLineItemVO> poListToVoList(ArrayList<ExceptionLineItemPO> list) {
		ArrayList<ExceptionLineItemVO> result=new ArrayList<ExceptionLineItemVO>();
		for(int i=0;i<list.size();i++){
			ExceptionLineItemPO temp=list.get(i);
			result.add(new ExceptionLineItemVO(temp.getId(),temp.getName(),
					temp.getModel(),temp.getSystemNumber(),temp.getActualNumber()));
		}
		return result;
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
	

}
