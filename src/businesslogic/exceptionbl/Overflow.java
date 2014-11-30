/**
 * overflow逻辑类
 */

package businesslogic.exceptionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ExceptionLineItemPO;
import po.ExceptionPO;
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
