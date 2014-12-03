/**
 * present逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.presentbl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

	
	public PresentPO voToPO(PresentVO vo) {
		
		String id = vo.id;
		
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

	public PresentVO poToVO(PresentPO po) {
		String id = po.getId();
		String time = po.getTime();
		String customerId = po.getCustomerId();
		
		String customerName = po.getCustomerName();
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
	
	public String createId(){
	Date date=new Date();
	SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMdd");
	String time=myFmt.format(date);
		ArrayList<PresentVO> presentList=show();
		if(presentList.isEmpty()){
			return "ZPD-"+time+"-00001";
		}else{
			String max=presentList.get(presentList.size()-1).id;
			String day=max.substring(4,max.length()-5);
			if(day.compareTo(time)<0){
			    return "ZPD-"+time+"-00001";
			}
			String oldMax=max.substring(max.length()-5);
			int maxInt=Integer.parseInt(oldMax);
			String pattern="00000";
			 java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			 String maxStr=df.format(maxInt+1);
			 return "ZPD-"+time+"-"+maxStr;
		}
	}
	
	public ResultMessage create(PresentVO vo) {
		Date date=new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd/");
		String time=myFmt.format(date);
		vo.time=time;
		PresentPO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPresentData().insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	public ResultMessage update(PresentVO vo) {
		String time=getById(vo.id).time;
		vo.time=time;
		//会不会出错？
		//TODO
		PresentPO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPresentData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public PresentVO getById(String id){
		PresentPO po=null;
		try {
			po = DataFactoryImpl.getInstance().getPresentData().getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		PresentVO vo=poToVO(po);
		return vo;
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

	public ArrayList<PresentVO> show(){
		ArrayList<PresentVO> result=new ArrayList<PresentVO>();
		ArrayList<PresentPO> temp=new ArrayList<PresentPO>();
		try {
			temp=DataFactoryImpl.getInstance().getPresentData().findByStatus(DocumentStatus.FAILED.ordinal());
			for(int i=0;i<temp.size();i++){
				result.add(poToVO(temp.get(i)));
			}
			
			temp=DataFactoryImpl.getInstance().getPresentData().findByStatus(DocumentStatus.PASSED.ordinal());
			for(int i=0;i<temp.size();i++){
				result.add(poToVO(temp.get(i)));
			}
			
			temp=DataFactoryImpl.getInstance().getPresentData().findByStatus(DocumentStatus.NONCHECKED.ordinal());
			for(int i=0;i<temp.size();i++){
				result.add(poToVO(temp.get(i)));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage approve(PresentVO vo){
//	if(vo.documentType==DocumentType.PRESENT){
//			for(PresentLineItemVO present:vo.list){
//				String id=present.id;
//				//
//			}
//		}
		//该库存，改商品
	return null;
	}

	
	
	
	public static void main(String[] args) {
		String id=new Present().createId();
		System.out.println(id);
	}

}
