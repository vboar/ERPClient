/**
 * commodity逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

import po.CategoryPO;
import po.CommodityPO;
import util.ResultMessage;
import vo.CategoryCommodityVO;
import vo.CategoryVO;
import vo.CommodityVO;
import vo.ExceptionVO;
import vo.SpecialOfferVO;
import businesslogic.logbl.Log;
import businesslogic.promotionbl.SpecialOfferPromotion;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class Commodity {

	Log log = new Log();

	/**
	 * vo转换po
	 * 
	 * @param vo
	 * @return
	 */
	public CommodityPO commodityVOToCommodityPO(CommodityVO vo) {
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

	/**
	 * po转换vo
	 * 
	 * @param po
	 * @return
	 */
	public CommodityVO commodityPOToCommodityVO(CommodityPO po) {

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

		String categoryId = id.substring(0, id.length() - 6);
		//System.out.println("commoditybl:71 "+id);
		CategoryPO categorypo = new Category().getById(categoryId);
		CategoryVO categoryvo = new Category()
				.CategoryPOToCategoryVO(categorypo);

		CommodityVO vo = new CommodityVO(id, name, model, number,
				purchasePrice, salePrice, recentPurchasePrice, recentSalePrice,
				warningNumber, isTrade, categoryvo);
		return vo;
	}

	/**
	 * 判断po是否存在
	 * 
	 * @param id
	 * @return
	 */
	private boolean existPO(String name, String model) {
		ArrayList<CommodityVO> voList = show();
		for (CommodityVO voCheck : voList) {
			if (voCheck.name.equals(name) && voCheck.model.equals(model)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 在显示中把不在时间范围内的或者手动使之无效的促销包商品去除掉
	 * @param voList
	 * @return
	 */
	private ArrayList<CommodityVO> removeOutDate(ArrayList<CommodityVO> voList){
		ArrayList<CommodityVO> result=new ArrayList<CommodityVO>();
		for(CommodityVO vo:voList){
			if(vo.id.compareTo("9999")>0){
				String speid=vo.id.substring(6);
				SpecialOfferVO specialVO=new SpecialOfferPromotion().getById(speid);
				boolean intime=Utility.inTime(specialVO.startTime, specialVO.endTime)&&specialVO.valid;
				if(intime){
					result.add(vo);
				}
			}else{
				result.add(vo);
			}
		}
		return result;
	}

	
	/**
	 * 创建商品的时候新建id
	 * 
	 * @param fatherId
	 * @return
	 */
	public String createId(String fatherId) {
		ArrayList<CommodityVO> voList = findById(fatherId);
		if (voList.size() == 0) {
			//System.out.println("commoditybl 108 "+fatherId + "-00000");
			return fatherId + "-00000";
		} else {

			String max = voList.get(voList.size() - 1).id;
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			//System.out.println("commoditybl 118 "+fatherId + "-" + maxStr);
			return fatherId + "-" + maxStr;
		}
	}

	public ResultMessage add(CommodityVO vo) {

		CommodityPO po = commodityVOToCommodityPO(vo);
		// 已经存在
		if (existPO(po.getName(), po.getModel())) {
			return ResultMessage.EXIST;
		}
		// 输入非法
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 140,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			return nameCheck;
		}
		
		// 添加到data
		try {
			DataFactoryImpl.getInstance().getCommodityData().insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		//System.out.println("commoditybl: 143 po.id "+po.getId());

		// 修改分类
		Category cat = new Category();
		CategoryPO fatherPO = cat.getById(vo.category.id);
		CategoryVO fatherVO = cat.CategoryPOToCategoryVO(fatherPO);
		fatherVO.number++;
		cat.update(fatherVO);

		return ResultMessage.SUCCESS;

	}

	/**
	 * 删除商品
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage delete(CommodityVO vo) {
		CommodityPO po = commodityVOToCommodityPO(vo);
		if (!existPO(vo.name, vo.model)) {
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
		// 修改分类
		Category cat = new Category();
		CategoryPO fatherPO = cat.getById(vo.category.id);
		CategoryVO fatherVO = cat.CategoryPOToCategoryVO(fatherPO);
		fatherVO.number--;
		cat.update(fatherVO);

		return ResultMessage.SUCCESS;

	}

	/**
	 * uodate
	 * 
	 * @param vo
	 * @return
	 */
	public ResultMessage update(CommodityVO vo) {

		CommodityPO po = null;
		if (vo.category == null) {
			// 得到原来的
			CommodityPO oldPO = getById(vo.id);
			CommodityVO oldVO = commodityPOToCommodityVO(oldPO);
			// 改改改
			oldVO.model = vo.model;
			oldVO.name = vo.name;
			oldVO.warningNumber = vo.warningNumber;
			vo.purchasePrice = vo.purchasePrice;
			vo.salePrice = vo.salePrice;
			po = commodityVOToCommodityPO(oldVO);
		} else {
			po = commodityVOToCommodityPO(vo);
		}
		
		// 检查输入合法（其实也就是象征性的）
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
				true);
		if (nameCheck != ResultMessage.SUCCESS) {
			//System.out.println("commoditybl 218 namecheckfail");
			return nameCheck;
		}
		ResultMessage nameCheck2 = Utility.checkInputValid(po.getModel(), 2,
				14, true);
		if (nameCheck2 != ResultMessage.SUCCESS) {
			//System.out.println("commoditybl 224 namecheckfail");
			return nameCheck2;
		}

		// 改data
		try {
			DataFactoryImpl.getInstance().getCommodityData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;

	}

	public CommodityPO getById(String id) {
		CommodityPO po = null;
		try {
			po = DataFactoryImpl.getInstance().getCommodityData().getById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return po;

	}

	public ArrayList<CommodityVO> findById(String id) {
		ArrayList<CommodityPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData()
					.findById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CommodityVO> voList = new ArrayList<CommodityVO>();
		for (CommodityPO po : poList) {
			voList.add(commodityPOToCommodityVO(po));
		}
		voList=removeOutDate(voList);
		return voList;
	}

	private ArrayList<CommodityVO> findByName(String name) {
		ArrayList<CommodityPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData()
					.findByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CommodityVO> voList = new ArrayList<CommodityVO>();
		for (CommodityPO po : poList) {
			voList.add(commodityPOToCommodityVO(po));
		}
		voList=removeOutDate(voList);
		return voList;
	}

	private ArrayList<CommodityVO> findByModel(String model) {
		ArrayList<CommodityPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData()
					.findByModel(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CommodityVO> voList = new ArrayList<CommodityVO>();
		for (CommodityPO po : poList) {
			voList.add(commodityPOToCommodityVO(po));
		}
		voList=removeOutDate(voList);
		return voList;
	}

	/**
	 * 模糊查找
	 * 
	 * @param keyword
	 * @return
	 */
	public ArrayList<CommodityVO> fuzzyFind(String keyword) {
		ArrayList<CommodityVO> voList1 = findByName(keyword);
		ArrayList<CommodityVO> voList2 = findById(keyword);
		ArrayList<CommodityVO> voList3 = findByModel(keyword);
		ArrayList<CommodityVO> voList = new ArrayList<CommodityVO>();

		voList.addAll(voList1);
		for (CommodityVO vo2 : voList2) {
			boolean add = true;
			for (CommodityVO vo1 : voList) {
				if (vo1.id.equals(vo2.id)) {
					add = false;
				}
			}
			if (add)
				voList.add(vo2);
		}
		for (CommodityVO vo2 : voList3) {
			boolean add = true;
			for (CommodityVO vo1 : voList) {
				if (vo1.id.equals(vo2.id)) {
					add = false;
				}
			}
			if (add)
				voList.add(vo2);
		}

		return voList;

	}

	public ArrayList<CommodityVO> show() {
		ArrayList<CommodityPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CommodityVO> voList = new ArrayList<CommodityVO>();
		for (CommodityPO po : poList) {
			voList.add(commodityPOToCommodityVO(po));
		}
		voList=removeOutDate(voList);
		return voList;

	}
	/**
	 * 返回没有促销包的商品
	 * @return
	 */
	public ArrayList<CommodityVO> showButSpecial() {
		ArrayList<CommodityPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getCommodityData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<CommodityVO> voList = new ArrayList<CommodityVO>();
		for (CommodityPO po : poList) {
			if(po.getId().compareTo("9999")<0){
			voList.add(commodityPOToCommodityVO(po));
			}
		}
		return voList;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<CategoryCommodityVO> showByInitial(String id) {
		ArrayList<CategoryCommodityVO> voList = new ArrayList<CategoryCommodityVO>();

		ArrayList<CategoryPO> caPOList=null;
		try {
			 caPOList=DataFactoryImpl.getInstance().getCategoryData().showByInitial(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (CategoryPO capo : caPOList) {
			CategoryVO cavo=new Category().CategoryPOToCategoryVO(capo);
			CategoryCommodityVO cacovo = new CategoryCommodityVO(cavo.id, cavo,
					null);
			voList.add(cacovo);
		}

		ArrayList<CommodityPO> coPOList=null;
		try {
			coPOList=DataFactoryImpl.getInstance().getCommodityData().showByInitial(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (CommodityPO copo : coPOList) {
			CommodityVO covo=commodityPOToCommodityVO(copo);
			CategoryCommodityVO cacovo = new CategoryCommodityVO(covo.id, null,
					covo);
			voList.add(cacovo);
		}
		Collections.sort(voList, new SortByIdForBig());

		return voList;
	}


	/**
	 * 组合大排序
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CategoryCommodityVO> bigShow() {
		ArrayList<CategoryCommodityVO> voList = new ArrayList<CategoryCommodityVO>();

		ArrayList<CategoryVO> caList = new Category().show();
		for (CategoryVO cavo : caList) {
			CategoryCommodityVO cacovo = new CategoryCommodityVO(cavo.id, cavo,
					null);
			voList.add(cacovo);
		}

		ArrayList<CommodityVO> coList = new Commodity().show();
		for (CommodityVO covo : coList) {
			CategoryCommodityVO cacovo = new CategoryCommodityVO(covo.id, null,
					covo);
			voList.add(cacovo);
		}
		Collections.sort(voList, new SortByIdForBig());

		return voList;
	}

	// 报溢报损单通过审批，修改商品数量
	public ResultMessage approveException(ExceptionVO vo) {
		for (int i = 0; i < vo.list.size(); i++) {
			try {
				CommodityPO temp = DataFactoryImpl.getInstance()
						.getCommodityData().getById(vo.list.get(i).id);
				temp.setNumber(temp.getNumber()+vo.list.get(i).actualNumber-vo.list.get(i).systemNumber);
				DataFactoryImpl.getInstance().getCommodityData().update(temp);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage writeoff(ExceptionVO vo){
		for (int i = 0; i < vo.list.size(); i++) {
			try {
				CommodityPO temp = DataFactoryImpl.getInstance()
						.getCommodityData().getById(vo.list.get(i).id);
				temp.setNumber(temp.getNumber()+(vo.list.get(i).systemNumber-vo.list.get(i).actualNumber));
				DataFactoryImpl.getInstance().getCommodityData().update(temp);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		return ResultMessage.SUCCESS;
	}

}
