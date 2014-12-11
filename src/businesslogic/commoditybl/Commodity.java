/**
 * commodity逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.commoditybl;

import businesslogic.logbl.Log;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.CategoryPO;
import po.CommodityPO;
import util.ResultMessage;
import vo.CategoryCommodityVO;
import vo.CategoryVO;
import vo.CommodityVO;
import vo.ExceptionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

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

		String categoryId = id.substring(0, id.length() - 6);
		System.out.println("commoditybl:71 "+id);
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
	 * 创建商品的时候新建id
	 * 
	 * @param fatherId
	 * @return
	 */
	public String createId(String fatherId) {
		ArrayList<CommodityVO> voList = findById(fatherId);
		if (voList.size() == 0) {
			System.out.println("commoditybl 108 "+fatherId + "-00000");
			return fatherId + "-00000";
		} else {

			String max = voList.get(voList.size() - 1).id;
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			System.out.println("commoditybl 118 "+fatherId + "-" + maxStr);
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
		ResultMessage nameCheck = Utility.checkInputValid(po.getName(), 2, 14,
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
		System.out.println("commoditybl: 143 po.id "+po.getId());

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
			System.out.println("commoditybl 218 namecheckfail");
			return nameCheck;
		}
		ResultMessage nameCheck2 = Utility.checkInputValid(po.getModel(), 2,
				14, true);
		if (nameCheck2 != ResultMessage.SUCCESS) {
			System.out.println("commoditybl 224 namecheckfail");
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
				temp.setNumber(vo.list.get(i).actualNumber);
				DataFactoryImpl.getInstance().getCommodityData().update(temp);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		return ResultMessage.SUCCESS;
	}

	// public static void main(String[] args) {
	// ArrayList<CommodityVO> voList=new ArrayList<CommodityVO>();
	// voList=new Commodity().fuzzyFind("0000");
	// for(int i=0;i<voList.size();i++)
	// System.out.println(voList.get(i).id);
	// }

}
