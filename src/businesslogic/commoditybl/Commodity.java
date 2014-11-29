/**
 * commodity逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityPO;
import util.ResultMessage;
import vo.CategoryVO;
import vo.CommodityVO;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Commodity {

	public ResultMessage createLog(String content) {
		MockLog ml = new MockLog(content);
		return ml.create();
	}

	private CommodityPO commodityVOToCommodityPO(CommodityVO vo) {
		String id = vo.id;
		String name = vo.name;
		String model = vo.model;
		int number = vo.number;
		double purchasePrice = vo.purchasePrice;
		double salePrice = vo.salePrice;
		double recentSalePrice = vo.recentSalePrice;
		double recentPurchasePrice = vo.recentPurchasePrice;
		int warningNumber = vo.warningNumber;
		boolean isTrade = vo.isTrade;
		CommodityPO po = new CommodityPO(id, name, model, number,
				purchasePrice, salePrice, recentPurchasePrice, recentSalePrice,
				warningNumber, isTrade);
		return po;
	}

	private CommodityVO commodityPOToCommodityVO(CommodityPO po) {
		String id = po.getId();
		String name = po.getName();
		String model = po.getModel();
		int number = po.getNumber();
		double purchasePrice = po.getPurchasePrice();
		double salePrice = po.getSalePrice();
		double recentSalePrice = po.getRecentSalePrice();
		double recentPurchasePrice = po.getRecentPurchasePrice();
		int warningNumber = po.getWarningNumber();
		boolean isTrade = po.isTrade();
		CommodityVO vo = new CommodityVO(id, name, model, number,
				purchasePrice, salePrice, recentPurchasePrice, recentSalePrice,
				warningNumber, isTrade, null);
		return vo;
	}

	public ResultMessage add(CommodityVO vo)  {

		CommodityPO po = commodityVOToCommodityPO(vo);
		if (existPO(po.getId())) {
			return ResultMessage.EXIST;
		}
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		try {
			DataFactoryImpl.getInstance().getCommodityData().insert(po);
			changeCategory(vo.category, 1);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return ResultMessage.SUCCESS;

	}

	public ResultMessage delete(CommodityVO vo)  {
		CommodityPO po = commodityVOToCommodityPO(vo);
		if (!existPO(vo.id)) {
			return ResultMessage.NOT_FOUND;
		}
		if (vo.isTrade) {
			return ResultMessage.IS_TRADE;
		}
		try {
			DataFactoryImpl.getInstance().getCommodityData().delete(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	public ResultMessage update(CommodityVO vo)  {
		CommodityPO po = commodityVOToCommodityPO(vo);
		if (!existPO(po.getId())) {
			return ResultMessage.NOT_FOUND;
		}

		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		ResultMessage nameCheck2 = Utility.checkInputValid(po.getModel(), 2,
				14, true);
		if (nameCheck2 != ResultMessage.SUCCESS) {
			return nameCheck2;
		}

		try {
			DataFactoryImpl.getInstance().getCommodityData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	public ArrayList<CommodityVO> findById(String id)  {
		 ArrayList<CommodityPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData().findById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 ArrayList<CommodityVO> voList=new ArrayList<CommodityVO>();
		 for(CommodityPO po:poList){
			 voList.add(commodityPOToCommodityVO(po));
		 }
		 return voList;
	}
	
//	private CommodityPO getById(String id){
//		CommodityPO po=null;
//		try {
//			po = DataFactoryImpl.getInstance().getCommodityData().getById(id);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		return po;
//		
//	}
	//TODO
	
	public ArrayList<CommodityVO> findByName(String name)  {
		 ArrayList<CommodityPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData().findByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 ArrayList<CommodityVO> voList=new ArrayList<CommodityVO>();
		 for(CommodityPO po:poList){
			 voList.add(commodityPOToCommodityVO(po));
		 }
		 return voList;
	}
	
	public ArrayList<CommodityVO> findByModel(String model)  {
		 ArrayList<CommodityPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData().findByModel(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 ArrayList<CommodityVO> voList=new ArrayList<CommodityVO>();
		 for(CommodityPO po:poList){
			 voList.add(commodityPOToCommodityVO(po));
		 }
		 return voList;
	}



	public ArrayList<CommodityVO> show()  {
		ArrayList<CommodityPO> poList=null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 ArrayList<CommodityVO> voList=new ArrayList<CommodityVO>();
		 for(CommodityPO po:poList){
			 voList.add(commodityPOToCommodityVO(po));
		 }
		 return voList;

	}

	private boolean existPO(String id)  {
		ArrayList<CommodityVO> voList = show();
		for (CommodityVO voCheck : voList) {
			if (voCheck.id.equals(id)) {
				return true;
			}
		}

		return false;
	}

	private void changeCategory(CategoryVO vo, int change)
			throws RemoteException {
		Category cat = new Category();
		vo.number += change;
		cat.update(vo);
	}

}
