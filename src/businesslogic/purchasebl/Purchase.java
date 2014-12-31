/**
 * Purchase
 * @author oenoneO
 * @date 2014/11/14
 */
package businesslogic.purchasebl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.CommodityLineItemPO;
import po.CommodityPO;
import po.PurchasePO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import util.Time;
import vo.CommodityLineItemVO;
import vo.CustomerVO;
import vo.PurchaseVO;
import businesslogic.commoditybl.Commodity;
import businesslogic.customerbl.Customer;
import businesslogic.loginbl.Login;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Purchase {

	public PurchaseVO poToVO(PurchasePO po) {
		String id = po.getId();
		String purId = po.getPurId();
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
		boolean canReturn = po.isCanReturn();
		boolean canWriteOff = po.isCanWriteOff();
		DocumentType documentType = DocumentType.values()[po.getDocumentType()];
		PurchaseVO vo = new PurchaseVO(id, purId,time, customerId, customerName,
				operatorId, storage, saleList, total, remark, documentStatus,
				isWriteOff, canReturn, canWriteOff, documentType);
		return vo;

	}

	public PurchasePO voToPO(PurchaseVO vo) {
		String id = vo.id;
		String purId = vo.purId;
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
		boolean canReturn = vo.canReturn;
		boolean canWriteOff = vo.canWriteOff;
		int documentType = vo.receiptType.ordinal();
		PurchasePO po = new PurchasePO(id, purId, time, customerId, customerName,
				operatorId, storage, saleList, total, remark, documentStatus,
				isWriteOff, canReturn, canWriteOff,documentType);
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

	public String createId() {
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String time = myFmt.format(date);
		ArrayList<PurchaseVO> presentList = bigShow();
		if (presentList.isEmpty()) {
			return "JHD-" + time + "-00001";
		} else {
			String max = presentList.get(presentList.size() - 1).id;
			String day = max.substring(4, max.length() - 5);
			if (day.compareTo(time) < 0) {
				return "JHD-" + time + "-00001";
			}
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "JHD-" + time + "-" + maxStr;
		}
	}

	public String createReturnId() {
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String time = myFmt.format(date);
		ArrayList<PurchaseVO> presentList = bigShow();
		if (presentList.isEmpty()) {
			return "JHTHD-" + time + "-00001";
		} else {
			String max = presentList.get(presentList.size() - 1).id;
			String day = max.substring(4, max.length() - 5);
			if (day.compareTo(time) < 0) {
				return "JHTHD-" + time + "-00001";
			}
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "JHTHD-" + time + "-" + maxStr;
		}
	}

	public ResultMessage add(PurchaseVO vo) {
    	String time =Time.getCurrentTime();
		vo.time = time;
		vo.operatorId = Login.currentUserId;

		PurchasePO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPurchaseData().insert(po);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		CustomerVO customerVO=new Customer().getById(vo.customerId);
		customerVO.isDeletable=false;
		new Customer().update(customerVO);
		
		return ResultMessage.SUCCESS;

	}

	public ResultMessage update(PurchaseVO vo) {
		String time = getById(vo.id).time;
		vo.time = time;
		System.out.println(vo.id + " " +vo.canReturn);
		PurchasePO po = voToPO(vo);
		try {
			DataFactoryImpl.getInstance().getPurchaseData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public PurchaseVO getById(String id) {
		PurchasePO po = null;
		try {
			po = DataFactoryImpl.getInstance().getPurchaseData().getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		PurchaseVO vo = poToVO(po);
		return vo;
	}

	public ArrayList<PurchaseVO> findByTime(String time1, String time2) {
		time1=Time.jdugeTime1(time1);
		time2=Time.jdugeTime2(time2);
		ArrayList<PurchasePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getPurchaseData()
					.findByTime(time1, time2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<PurchasePO> poList2 = new ArrayList<PurchasePO>();
		for (PurchasePO po : poList) {
			if (po.getDocumentType() == 6) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);
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
			if (po.getDocumentType() == 6) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

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
			if (po.getDocumentType() == 6) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;
	}

	public ArrayList<PurchaseVO> findByStatus(int status) {
		ArrayList<PurchaseVO> result = new ArrayList<PurchaseVO>();
		ArrayList<PurchasePO> temp = new ArrayList<PurchasePO>();

		try {
			temp = DataFactoryImpl.getInstance().getPurchaseData()
					.findByStatus(status);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getDocumentType() == DocumentType.PURCHASE
						.ordinal())
					result.add(poToVO(temp.get(i)));
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
		ArrayList<PurchasePO> poList2 = new ArrayList<PurchasePO>();
		for (PurchasePO po : poList) {
			if (po.getDocumentType() == 6) {
				poList2.add(po);
			}
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList2);

		return voList;

	}

	public ArrayList<PurchaseVO> bigShow() {
		ArrayList<PurchasePO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getPurchaseData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<PurchaseVO> voList = poListToVoList(poList);

		return voList;

	}

	// TODO
	public ResultMessage approve(PurchaseVO vo) {
		double total = vo.total;

		Customer cus = new Customer();
		cus.updateByPurchase(vo.customerId, total);

		for (CommodityLineItemVO vo1 : vo.saleList) {
			Commodity commodity = new Commodity();
			CommodityPO commoditypo = commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber() + vo1.number);
			commoditypo.setRecentPurchasePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData()
						.update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
		return ResultMessage.SUCCESS;
	}

	public void writeoff(PurchaseVO vo) {
		double total = vo.total;

		Customer cus = new Customer();
		cus.updateByPurchase(vo.customerId, total);

		for (CommodityLineItemVO vo1 : vo.saleList) {
			Commodity commodity = new Commodity();
			CommodityPO commoditypo = commodity.getById(vo1.id);
			commoditypo.setNumber(commoditypo.getNumber() - vo1.number);
			//commoditypo.setRecentPurchasePrice(vo1.price);
			try {
				DataFactoryImpl.getInstance().getCommodityData()
						.update(commoditypo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}

	}

}
