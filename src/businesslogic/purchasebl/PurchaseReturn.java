/**
 * PurchaseReturn
 * author oenoenO
 * date 2014/11/14
 */
package businesslogic.purchasebl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogic.commoditybl.Commodity;
import businesslogic.customerbl.Customer;
import businesslogic.utilitybl.Utility;
import po.CommodityPO;
import po.PurchasePO;
import util.DocumentType;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PurchaseVO;
import dataservice.datafactoryservice.DataFactoryImpl;

public class PurchaseReturn {
	Purchase purchase = new Purchase();

	public String createId() {
		return purchase.createReturnId();
	}

	public ResultMessage add(PurchaseVO vo) {
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = myFmt.format(date);
		vo.time = time;
		PurchasePO po = purchase.voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPurchaseData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(PurchaseVO vo) {
		return purchase.update(vo);
	}

	public PurchaseVO getById(String id) {

		return purchase.getById(id);

	}

	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		if (time1 == null || time1.equals("")) {
			time1 = "1970/1/1 00:00:00";
		}
		if (time2 == null || time2.equals("")) {
			time2 = Utility.getCurrentTime();
		}

		ArrayList<PurchasePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getPurchaseData()
					.findByTime(time1, time2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<PurchasePO> poList2 = new ArrayList<PurchasePO>();
		for (PurchasePO po : poList) {
			if (po.getDocumentType() == 7) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

		return voList;

	}

	public ArrayList<PurchaseVO> findByCommodityName(String commodityName) {
		ArrayList<PurchaseVO> voList = show();
		ArrayList<PurchaseVO> result = new ArrayList<PurchaseVO>();

		for (PurchaseVO vo : voList) {
			for (CommodityLineItemVO commodity : vo.saleList) {
				if (commodity.name.equals(commodityName)) {
					result.add(vo);
				}
			}
		}
		return result;
	}

	public ArrayList<PurchaseVO> findByCustomer(String customer) {
		ArrayList<PurchasePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getPurchaseData()
					.findByCustomer(customer);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<PurchasePO> poList2 = new ArrayList<PurchasePO>();
		for (PurchasePO po : poList) {
			if (po.getDocumentType() == 7) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

		return voList;
	}

	public ArrayList<PurchaseVO> findByStorage(String Storage) {
		ArrayList<PurchasePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getPurchaseData()
					.findByStorage(Storage);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		ArrayList<PurchasePO> poList2 = new ArrayList<PurchasePO>();
		for (PurchasePO po : poList) {
			if (po.getDocumentType() == 7) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

		return voList;
	}

	public ArrayList<PurchaseVO> findByStatus(int status) {
		ArrayList<PurchaseVO> result = new ArrayList<PurchaseVO>();
		ArrayList<PurchasePO> temp = new ArrayList<PurchasePO>();
		Purchase p = new Purchase();
		try {
			temp = DataFactoryImpl.getInstance().getPurchaseData()
					.findByStatus(status);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getDocumentType() == DocumentType.PURCHASERETURN
						.ordinal()) {
					result.add(p.poToVO(temp.get(i)));
				}
			}
		} catch (RemoteException e) {
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
		ArrayList<PurchasePO> poList2 = new ArrayList<PurchasePO>();
		for (PurchasePO po : poList) {
			if (po.getDocumentType() == 7) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = purchase.poListToVoList(poList2);

		return voList;
	}

	public ResultMessage approve(PurchaseVO vo) {
		double total = vo.total;

		Customer cus = new Customer();
		cus.updateByPurchaseReturn(vo.customerId, total);

		for (CommodityLineItemVO vo1 : vo.saleList) {
			Commodity commodity = new Commodity();
			CommodityPO commoditypo = commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber() - vo1.number);
			// commoditypo.setRecentPurchasePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData()
						.update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
		return ResultMessage.SUCCESS;
	}
	
	public void writeoff(PurchaseVO vo){
		double total = vo.total;

		Customer cus = new Customer();
		cus.updateByPurchaseReturn(vo.customerId, total);

		for (CommodityLineItemVO vo1 : vo.saleList) {
			Commodity commodity = new Commodity();
			CommodityPO commoditypo = commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber() + vo1.number);
			// commoditypo.setRecentPurchasePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData()
						.update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}

	}

}
