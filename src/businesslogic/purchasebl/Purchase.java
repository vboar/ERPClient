/**
 * Purchase
 * @author oenoneO
 * @date 2014/11/14
 */
package businesslogic.purchasebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityLineItemPO;
import po.PurchasePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;
import businesslogic.salebl.MockCommodity;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Purchase {

	public ResultMessage add(PurchaseVO vo) {

		PurchasePO po = poToVO(vo);
		try {
			DataFactoryImpl.getInstance().getPurchaseData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

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
			if(po.getDocumentType()==6){
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);
		return voList;

	}

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
			if(po.getDocumentType()==6){
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);
		
		return voList;
	}

	public ArrayList<PurchaseVO> findByStorage(String Storage) {
		ArrayList<PurchasePO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance()
					.getPurchaseData().findByStorage(Storage);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}ArrayList<PurchasePO> poList2 =new ArrayList<PurchasePO>();
		for(PurchasePO po:poList){
			if(po.getDocumentType()==6){
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

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
			if(po.getDocumentType()==6){
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;

	}
	
	

	public ResultMessage update(PurchaseVO vo) {
		PurchasePO po = poToVO(vo);
		try {
			DataFactoryImpl.getInstance().getPurchaseData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	// TODO
	public ResultMessage approve(Purchase vo) {
		return null;

	}
	
	//下面是purchasereturn专用方法
	// ---------------------------------------------------------------------------------------------------
	protected ArrayList<PurchaseVO> findByTime2(String time1, String time2) {
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
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;

	}
//TODO
	
	protected ArrayList<PurchaseVO> findByCommodityName2(String commodityName) {
		return null;
	}

	protected ArrayList<PurchaseVO> findByCustomer2(String customer) {
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
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;
	}

	protected ArrayList<PurchaseVO> findByStorage2(String Storage) {
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
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;
	}

	protected ArrayList<PurchaseVO> show2() {
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
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;
	}

	
	

	
	// ---------------------------------------------------------------------------------------------------



	public PurchaseVO poToVO(PurchasePO po) {
		// String id,String time,String customerId,String customerName,
		// String operatorId,String storage,ArrayList<CommodityLineItemPO>
		// saleList,double total,
		// String remark,int documentStatus,boolean isWriteOff,int documentType
		String id = po.getId();
		String time = po.getTime();
		String customerId = po.getCustomerId();
		String customerName = po.getCustomerName();
		String operatorId = po.getOperatorId();
		String storage = po.getStorage();
		ArrayList<CommodityLineItemVO> saleList = Utility.poListToVOList(po
				.getSaleList());
		double total = po.getTotal();
		String remark = po.getRemark();
		DocumentStatus documentStatus = DocumentStatus.values()[po
				.getDocumentStatus()];
		boolean isWriteOff = po.isWriteOff();
		DocumentType documentType = DocumentType.values()[po.getDocumentType()];
		PurchaseVO vo = new PurchaseVO(id, time, customerId, customerName,
				operatorId, storage, saleList, total, remark, documentStatus,
				isWriteOff, documentType);
		return vo;

	}

	public PurchasePO poToVO(PurchaseVO vo) {
		String id = vo.id;
		String time = vo.time;
		String customerId = vo.customerId;
		String customerName = vo.customerName;
		String operatorId = vo.operatorId;
		String storage = vo.storage;
		ArrayList<CommodityLineItemPO> saleList = Utility
				.voListToPOList(vo.saleList);
		double total = vo.total;
		String remark = vo.remark;
		int documentStatus = vo.documentStatus.ordinal();
		boolean isWriteOff = vo.isWriteOff;
		int documentType = vo.receiptType.ordinal();
		PurchasePO po = new PurchasePO(id, time, customerId, customerName,
				operatorId, storage, saleList, total, remark, documentStatus,
				isWriteOff, documentType);
		return po;

	}

	public ArrayList<PurchaseVO> poListToVoList(ArrayList<PurchasePO> poList) {
		ArrayList<PurchaseVO> voList = new ArrayList<PurchaseVO>();
		for (PurchasePO po : poList) {
			PurchaseVO vo = poToVO(po);
			voList.add(vo);
		}
		return voList;
	}

	// ---------------------------------------------------------------------------------------------------

	public ResultMessage updateCommodityByPurchase(
			ArrayList<CommodityLineItemVO> list) {
		MockCommodity mc = new MockCommodity();

		return mc.updateCommodityBySale(list);
	}

	public ResultMessage updateCustomerByPurchase(String name, double total) {
		MockCustomer mc = new MockCustomer();

		return mc.updateCustomerByPurchase(name, total);
	}

	public ResultMessage addLog(String content) {
		MockLog ml = new MockLog();

		return ml.addLog(content);
	}

}
