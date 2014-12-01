/**
 * PurchaseReturn
 * author oenoenO
 * date 2014/11/14
 */
package businesslogic.purchasebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PurchasePO;
import util.DocumentType;
import util.ResultMessage;
import vo.PurchaseVO;
import dataservice.datafactoryservice.DataFactoryImpl;

public class PurchaseReturn {
Purchase purchase=new Purchase();
	
	public ResultMessage add(PurchaseVO vo) {
		PurchasePO po = purchase.voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPurchaseData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(PurchaseVO vo){
		return purchase.update(vo);
	}
		//TODO
	public ResultMessage approve(PurchaseVO vo) {
		return null;

	}

	//下面是purchasereturn专用方法
		// ---------------------------------------------------------------------------------------------------
		public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
			ArrayList<PurchasePO> poList = null;
			try {
				poList = DataFactoryImpl.getInstance().getPurchaseData()
						.findByTime(time1, time2);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ArrayList<PurchasePO> poList2 =new ArrayList<PurchasePO>();
			for(PurchasePO po:poList){
				if(po.getDocumentType()==7){
					poList2.add(po);
				}
			}
			ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

			return voList;

		}
	//TODO
		
		public ArrayList<PurchaseVO> findByCommodityName(String commodityName) {
			return null;
		}

		public ArrayList<PurchaseVO> findByCustomer(String customer) {
			ArrayList<PurchasePO> poList = null;
			try {
				poList = DataFactoryImpl.getInstance().getPurchaseData()
						.findByCustomer(customer);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ArrayList<PurchasePO> poList2 =new ArrayList<PurchasePO>();
			for(PurchasePO po:poList){
				if(po.getDocumentType()==7){
					poList2.add(po);
				}
			}
			ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

			return voList;
		}

		public ArrayList<PurchaseVO> findByStorage(String Storage) {
			ArrayList<PurchasePO> poList=null;
			try {
				poList = DataFactoryImpl.getInstance()
						.getPurchaseData().findByStorage(Storage);
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			ArrayList<PurchasePO> poList2 =new ArrayList<PurchasePO>();
			for(PurchasePO po:poList){
				if(po.getDocumentType()==7){
					poList2.add(po);
				}
			}
			ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

			return voList;
		}
		
		public ArrayList<PurchaseVO> findByStatus(int status) {
			ArrayList<PurchaseVO> result=new ArrayList<PurchaseVO>();
			ArrayList<PurchasePO> temp=new ArrayList<PurchasePO>();
			Purchase p=new Purchase();
			try {
				temp=DataFactoryImpl.getInstance().getPurchaseData().findByStatus(status);
				for(int i=0;i<temp.size();i++){
					if(temp.get(i).getDocumentType()==DocumentType.PURCHASERETURN.ordinal()){
						result.add(p.poToVO(temp.get(i)));
					}
				}
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			return result;
		}
		public ArrayList<PurchaseVO> show() {
			ArrayList<PurchasePO> poList = null;
			try {
				poList = DataFactoryImpl.getInstance().getPurchaseData().show();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ArrayList<PurchasePO> poList2 =new ArrayList<PurchasePO>();
			for(PurchasePO po:poList){
				if(po.getDocumentType()==7){
					poList2.add(po);
				}
			}
			ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

			return voList;
		}
	
	public ResultMessage addLog(String content){
		//TODO
		return null;
	}

}
