/**
 * warning逻辑类
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.datafactoryservice.DataFactoryImpl;
import po.WarningLineItemPO;
import po.WarningPO;
import businesslogic.logbl.Log;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.WarningLineItemVO;
import vo.WarningVO;


public class Warning {
	public ResultMessage createLog(String content) {
		Log l=new Log();
		return l.add(content);
	}

	public ResultMessage create(WarningVO vo){
		vo.time=Time.getCurrentTime();
		try {
			DataFactoryImpl.getInstance().getWarningData().insert(voToPo(vo));
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	private WarningPO voToPo(WarningVO vo) {
		ArrayList<WarningLineItemPO> list=voListToPoList(vo.list);
		WarningPO result=new WarningPO(vo.id,vo.time,list,vo.type.ordinal());
		return result;
	}

	private ArrayList<WarningLineItemPO> voListToPoList(ArrayList<WarningLineItemVO> list) {
		ArrayList<WarningLineItemPO> result=new ArrayList<WarningLineItemPO>();
		for(int i=0;i<list.size();i++){
			result.add(new WarningLineItemPO(list.get(i).id,list.get(i).name,
					list.get(i).model,list.get(i).stockNumber,list.get(i).warningNumber));
		}
		return result;
	}

	public ArrayList<WarningVO> findByTime(String time1,String time2){
		ArrayList<WarningVO> result=new ArrayList<WarningVO>();
		ArrayList<WarningPO> temp=new ArrayList<WarningPO>();
		
		try {
			time1=Time.jdugeTime1(time1);
			time2=Time.jdugeTime2(time2);
			temp=DataFactoryImpl.getInstance().getWarningData().show(time1,time2);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		for(int i=0;i<temp.size();i++)
			result.add(poToVo(temp.get(i)));
		System.out.println("result"+result.get(0).list);
		return result;
	}

	public ArrayList<WarningVO> fingById(String id){
		ArrayList<WarningVO> result=new ArrayList<WarningVO>();
		ArrayList<WarningPO> temp=new ArrayList<WarningPO>();
		try {
			temp=DataFactoryImpl.getInstance().getWarningData().findById(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for(int i=0;i<temp.size();i++)
			result.add(poToVo(temp.get(i)));
		return result;
	}
	
	public WarningVO getById(String id){
		WarningPO temp=null;
		try {
			temp = DataFactoryImpl.getInstance().getWarningData().getById(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		WarningVO result=poToVo(temp);
		return result;
	}
	public WarningVO poToVo(WarningPO po) {
		ArrayList<WarningLineItemVO> list=poListToVoList(po.getList());
		System.out.println();
		WarningVO result=new WarningVO(po.getId(),po.getTime(),
				list,DocumentType.values()[po.getDocumentType()]);
		return result;
	}

	public ArrayList<WarningLineItemVO> poListToVoList(
			ArrayList<WarningLineItemPO> list) {
		 ArrayList<WarningLineItemVO> result=new  ArrayList<WarningLineItemVO>();
		 for(int i=0;i<list.size();i++)
			 result.add(new WarningLineItemVO(list.get(i).getId(),list.get(i).getName(),
					 list.get(i).getModel(),list.get(i).getStockNumber(),list.get(i).getWarningNumber()));
		return result;
	}
}
