/**
 * PurchaseReturn
 * author oenoenO
 * date 2014/11/14
 */
package businesslogic.purchasebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PurchasePO;
import dataservice.datafactoryservice.DataFactoryImpl;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;
import vo.SaleVO;

public class PurchaseReturn {
Purchase purchase=new Purchase();
	
	public ResultMessage add(PurchaseVO vo) {
		
		ArrayList<CommodityLineItemVO> voListTemp=vo.saleList;
		for(CommodityLineItemVO covotemp:voListTemp){
			covotemp.number=0-covotemp.number;
		}
		vo.saleList=voListTemp;
		PurchasePO po = purchase.poToVO(vo);
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
	public ResultMessage approveSale(SaleVO vo) {
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

		
		

	
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	public ResultMessage updateCommodityByPurchase(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc=new MockCommodity();
		
		return mc.updateCommodityByPurchase(list);
	}
	
	public ResultMessage updateCustomerByPurchase(String name,double total){
		MockCustomer mc=new MockCustomer();
		
		return mc.updateCustomerByPurchase(name, total);
	}
	
	public ResultMessage addLog(String content){
		MockLog ml=new MockLog();
		
		return ml.addLog(content);
	}

}
