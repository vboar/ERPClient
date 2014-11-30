/**
 * present逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.presentbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PresentLineItemPO;
import po.PresentPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PresentLineItemVO;
import vo.PresentVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Present {

	public ResultMessage create(PresentVO vo) {
		PresentPO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPresentData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	public ResultMessage update(PresentVO vo) {
		PresentPO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPresentData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public PresentPO getById(String id){
		PresentPO po=null;
		try {
			po = DataFactoryImpl.getInstance().getPresentData().getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return po;
	}
	
	public ArrayList<PresentVO> findById(String id){
		try {
			ArrayList<PresentPO> poList=DataFactoryImpl.getInstance().getPresentData().findById(id);
			ArrayList<PresentVO> voList=poListToVOList(poList);
			return voList;
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return null;
				
	}
	
	public ArrayList<PresentVO> findByStatus(DocumentStatus status){
		ArrayList<PresentPO> poList;
		try {
			poList = DataFactoryImpl.getInstance().getPresentData().findByStatus(status.ordinal());
			ArrayList<PresentVO> voList=poListToVOList(poList);
			return voList;
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<PresentVO> findByCustomerId(String customerId){
		ArrayList<PresentPO> poList=null;
	try {
		DataFactoryImpl.getInstance().getPresentData().findByCustomerId(customerId);
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	ArrayList<PresentVO> voList=poListToVOList(poList);
	return voList;
	}
	
	public ArrayList<PresentVO> findByTime(String time1,String time2){
		ArrayList<PresentPO> poList=null;
	try {
		DataFactoryImpl.getInstance().getPresentData().findByTime(time1, time2);
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	ArrayList<PresentVO> voList=poListToVOList(poList);
	return voList;
	}
	
	
	public ResultMessage approve(PresentVO vo){
	return null;
	}

	private PresentPO voToPO(PresentVO vo) {
		// String id, String time, String customerId, String customerName,
		// ArrayList<PresentLineItemPO> list, int documentStatus, int
		// documentType,
		// boolean isWriteoff
		String id=null;
		if( vo.id==null){
			 id="";
			//TODO
		}else{
		 id = vo.id;
		}
		String time = vo.time;
		String customerId = vo.customerId;
		String customerName = vo.customerName;
		ArrayList<PresentLineItemPO> list = Utility
				.presentVOListToPOlist(vo.list);
		int documentStatus = vo.documentStatus.ordinal();
		int documentType = vo.documentType.ordinal();
		boolean isWriteoff = vo.isWriteoff;
		PresentPO po = new PresentPO(id, time, customerId, customerName, list,
				documentStatus, documentType, isWriteoff);
		return po;
	}

	private PresentVO poToVO(PresentPO po) {
		String id = po.getId();
		String time = po.getTime();
		String customerId = po.getCustomerId();
		
		String customerName = po.getCustomerId();
		ArrayList<PresentLineItemVO> list = Utility.presentPOListToVOList(po
				.getList());
		DocumentStatus documentStatus = DocumentStatus.values()[po
				.getDocumentStatus()];
		DocumentType documentType = DocumentType.values()[po.getDocumentType()];
		boolean isWriteoff = po.isWriteoff();
		PresentVO vo = new PresentVO(id, time, customerId, customerName, list,
				documentStatus, documentType, isWriteoff);
		return vo;
	}
	
	private ArrayList<PresentVO> poListToVOList(ArrayList<PresentPO> poList){
		ArrayList<PresentVO> voList=new ArrayList<PresentVO>();
		for(PresentPO po:poList){
			PresentVO vo=poToVO(po);
			voList.add(vo);
		}
		return voList;
	}

}
