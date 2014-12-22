/**
 * stock逻辑类
 * @author Vboar
 * @date 2014/11/12
 */

package businesslogic.stockbl;

import businesslogic.commoditybl.Commodity;
import businesslogic.presentbl.Present;
import businesslogic.promotionbl.SpecialOfferPromotion;
import businesslogic.purchasebl.Purchase;
import businesslogic.purchasebl.PurchaseReturn;
import businesslogic.salebl.Sale;
import businesslogic.salebl.SaleReturn;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.StockPO;
import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.*;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Stock {

	public Stock() {
	}

	/**
	 * 库存查看
	 * 流程是这样的：
	 * 1.首先获得时间，如果时间缺省就自动补充成道现在或者从头开始
	 * 2.按时间查询，销售单，销售退货单，进货单，进货退货单，赠品单，并且建立outlist（出货列表）和inlist（进货列表）
	 * 3.把这些单据中没有审批的或者审批不过的去掉
	 * 4.把赠品单里的商品list转化并进入inlist或者outlist。
	 * 5.把促销包商品转化成对应的商品
	 * 6.把销售单和进货的退货单里面的商品的list并入outlist，吧销售退货单和进货单里面的商品list并入inlist
	 * 7.使用遍历的方法合并inlist和outlist的同类项的商品，做成最后的结果，存入arraylist
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<StockInfoVO> showStockInfo(String time1, String time2) {

		// 获得时间
		if (time1 == null) {
			time1 = "1970/1/1 00:00:00";
		}
		if (time2 == null) {
			Date date = new Date();
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			time2 = myFmt.format(date);
		}
		// 获取初始单据信息
		ArrayList<PresentVO> presentList = new Present().findByTime(time1,
				time2);
		System.out.println("Stockbl:53 presentlist.size " + presentList.size());
		ArrayList<SaleVO> saleList = new Sale().findByTime(time1, time2);
		ArrayList<SaleVO> saleReturnList = new SaleReturn().findByTime(time1,
				time2);
		ArrayList<PurchaseVO> purchaseList = new Purchase().findByTime(time1,
				time2);
		ArrayList<PurchaseVO> purchaseReturnList = new PurchaseReturn()
				.findByTime(time1, time2);

		ArrayList<CommodityLineItemVO> inList = new ArrayList<CommodityLineItemVO>();
		ArrayList<CommodityLineItemVO> outList = new ArrayList<CommodityLineItemVO>();

		// 消去没有被审批的
		Iterator<SaleVO> salelistitr = saleList.iterator();
		while (salelistitr.hasNext()) {
			SaleVO salevo = salelistitr.next();
			if (salevo.approvalState != DocumentStatus.PASSED) {
				salelistitr.remove();

			}
			
		}
		Iterator<SaleVO> saleReturnItr = saleReturnList.iterator();
		while (saleReturnItr.hasNext()) {
			SaleVO salevo = saleReturnItr.next();
			if (salevo.approvalState != DocumentStatus.PASSED) {
				saleReturnItr.remove();

			}
		}
		Iterator<PurchaseVO> purchaseItr = purchaseList.iterator();
		while (purchaseItr.hasNext()) {
			PurchaseVO purchasevo = purchaseItr.next();
			if (purchasevo.documentStatus != DocumentStatus.PASSED) {
				purchaseItr.remove();

			}
		}
		Iterator<PurchaseVO> purchaseReturnItr = purchaseReturnList.iterator();
		while (purchaseReturnItr.hasNext()) {
			PurchaseVO purchasevo = purchaseReturnItr.next();
			if (purchasevo.documentStatus != DocumentStatus.PASSED) {
				purchaseReturnItr.remove();

			}
		}
		Iterator<PresentVO> presentItr = presentList.iterator();
		while (presentItr.hasNext()) {
			PresentVO presentvo = presentItr.next();
			if (presentvo.documentStatus != DocumentStatus.PASSED) {
				presentItr.remove();

			}
		}

		// 把present矫诏变成commodity的样子
		System.out
				.println("Stockbl 101: presentlist.size" + presentList.size());
		for (PresentVO presentVO : presentList) {
			ArrayList<PresentLineItemVO> preList = presentVO.list;
			boolean present = true;
			if (presentVO.documentType == DocumentType.PRESENTRETURN) {
				present = false;
			}
			for (PresentLineItemVO presentvo : preList) {

				double money = new Commodity().getById(presentvo.id)
						.getPurchasePrice();
				CommodityLineItemVO clivo = new CommodityLineItemVO(
						presentvo.id, presentvo.name, presentvo.model,
						presentvo.number, money, presentvo.number * money, null);
				if (present)
					outList.add(clivo);
				else
					inList.add(clivo);

			}

		}
		System.out.println("Stockbl 127: outlist.size " + outList.size());
		System.out.println("Stockbl 128: inlist.size " + inList.size());

		//把促销包变换成商品
		for(SaleVO salevo : saleList){
			ArrayList<CommodityLineItemVO> list=removeSpecial(salevo.saleList);
			salevo.saleList=list;
		}
		
		for(SaleVO salevo : saleReturnList){
			ArrayList<CommodityLineItemVO> list=removeSpecial(salevo.saleList);
			salevo.saleList=list;
		}

		// 加到第二级列表中，按照销售和进货分开
		for (SaleVO salevo : saleList) {
			outList.addAll(salevo.saleList);
		}
		for (SaleVO salereturnvo : saleReturnList) {
			inList.addAll(salereturnvo.saleList);
		}

		for (PurchaseVO purchasevo : purchaseList) {
			inList.addAll(purchasevo.saleList);
		}
		for (PurchaseVO purchasereturnvo : purchaseReturnList) {
			outList.addAll(purchasereturnvo.saleList);
		}

		// 做结果的list
		ArrayList<StockInfoVO> result = new ArrayList<StockInfoVO>();
		while (!inList.isEmpty()) {
			CommodityLineItemVO invo = inList.get(0);
			StockInfoVO vo = new StockInfoVO(invo.id, invo.name, invo.model,
					invo.number, invo.total, 0, 0);
			inList.remove(0);
			Iterator<CommodityLineItemVO> clivoitr = inList.iterator();
			while (clivoitr.hasNext()) {
				CommodityLineItemVO vo1 = clivoitr.next();
				if (vo1.id.equals(invo.id)) {
					vo.inNumber += vo1.number;
					vo.inMoney += vo1.total;
					clivoitr.remove();
				}
			}
			Iterator<CommodityLineItemVO> clivoitr2 = outList.iterator();

			while (clivoitr2.hasNext()) {
				CommodityLineItemVO vo2 = clivoitr2.next();
				if (vo2.id.equals(invo.id)) {
					vo.outNumber += vo2.number;
					vo.outMoney += vo2.total;
					clivoitr2.remove();
				}
			}
			result.add(vo);
		}
		// 如果剩下的只有卖的也不能漏下
		while (!outList.isEmpty()) {
			CommodityLineItemVO outvo = outList.get(0);
			StockInfoVO vo = new StockInfoVO(outvo.id, outvo.name, outvo.model,
					0, 0, outvo.number, outvo.total);
			outList.remove(0);
			Iterator<CommodityLineItemVO> clivoitr3 = outList.iterator();
			while (clivoitr3.hasNext()) {
				CommodityLineItemVO vo2 = clivoitr3.next();
				if (vo2.id.equals(outvo.id)) {
					vo.outNumber += vo2.number;
					vo.outMoney += vo2.total;
					clivoitr3.remove();
				}
			}
			result.add(vo);
		}
		return result;
	}

	/**
	 * 生成批号
	 * 老方法，每天从1开始
	 * @return
	 */
	private String createBatchNumber() {
		ArrayList<StockPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getStockData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (poList == null || poList.isEmpty()) {
			return "00001";
		}
		String old = poList.get(poList.size() - 1).getBatchNumber();
		String oldBatch = poList.get(poList.size() - 1).getBatch();
		String batch = null;
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd");
		batch = myFmt.format(date);
		if (!batch.equals(oldBatch)) {
			return "00001";
		}
		int maxInt = Integer.parseInt(old);
		String pattern = "00000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		String maxStr = df.format(maxInt + 1);
		return maxStr;
	}

	/**
	 * 库存盘点
	 * 过程是这样的：
	 * 1.先生称今天的时间
	 * 2.遍历每一个除了促销包的商品
	 * 3.在进货单中使劲找，如果是进货单，就在这个商品的总价上加上这次进货的总价，数量上加上这次进货的数量；退货的话则反过来
	 * 4.除一下，算出平均价，并且保留两位小数
	 * 5.把这一次的盘点计入到数据层
	 * @return
	 */
	public ArrayList<StockVO> showCheck() {
		String batch = null;
		Date date = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd");
		batch = myFmt.format(date);

		String batchNumber = createBatchNumber();

		ArrayList<CommodityVO> commodityList = new Commodity().showButSpecial();
		ArrayList<StockVO> stockList = new ArrayList<StockVO>();
		for (CommodityVO commodityvo : commodityList) {
			String id = commodityvo.id;
			double allPrice = 0;
			int numbertotal=0;
			StockVO stockvo = new StockVO(id, commodityvo.name,
					commodityvo.model, commodityvo.number, 0, batch,
					batchNumber);
			ArrayList<PurchaseVO> purchaseList = new Purchase()
					.findByStatus(DocumentStatus.PASSED.ordinal());
			for (PurchaseVO purchasevo : purchaseList) {
				for (CommodityLineItemVO commodity : purchasevo.saleList) {
					if (commodity.id.equals(id)) {
						if (purchasevo.receiptType == DocumentType.PURCHASE) {
							allPrice += commodity.total;
							numbertotal+=commodity.number;
						} else {
							allPrice -= commodity.total;
							numbertotal-=commodity.number;
						}
					}
				}
			}
			if (numbertotal >= 0) {
				stockvo.avgPrice = allPrice / numbertotal;
				BigDecimal bg = new BigDecimal(stockvo.avgPrice);
				stockvo.avgPrice = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			stockList.add(stockvo);
			StockPO po = voToPO(stockvo);
			try {
				DataFactoryImpl.getInstance().getStockData().insert(po);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
		return stockList;
	}

	/**
	 * 按照批次和批号查找
	 * 1.从数据层娶过来
	 * 2.po转成vo
	 * 3.返回
	 * @param batch
	 * @param batchNumber
	 * @return
	 */
	public ArrayList<StockVO> findByDate(String batch, String batchNumber) {
		ArrayList<StockVO> voList = new ArrayList<StockVO>();
		ArrayList<StockPO> poList = new ArrayList<StockPO>();
		try {
			poList = DataFactoryImpl.getInstance().getStockData()
					.findByDate(batch, batchNumber);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (StockPO po : poList) {
			StockVO vo = poToVO(po);
			voList.add(vo);
		}

		return voList;

	}

	/**
	 * 导出当天第一次生成的excel
	 * 1.生成路径
	 * 2.导出excel
	 * @param path
	 * @param time
	 * @return
	 */
	public ResultMessage exportExcel(String path, String time) {
		String batch = time;
		System.out.println("stockbl 269 time:" + batch);
		ArrayList<StockVO> voList = findByDate(batch, "00001");

		try {
			ExportStockPO.export(voList, path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResultMessage.SUCCESS;
	}

	private StockPO voToPO(StockVO vo) {
		String commodityId = vo.commodityId;
		String commodityName = vo.commodityName;
		String commodityModel = vo.commodityModel;
		int number = vo.number;
		double avgPrice = vo.avgPrice;
		String batch = vo.batch;
		String batchNumber = vo.batchNumber;
		StockPO po = new StockPO(commodityId, commodityName, commodityModel,
				number, avgPrice, batch, batchNumber);
		return po;
	}

	private StockVO poToVO(StockPO po) {
		String commodityId = po.getCommodityId();
		String commodityName = po.getCommodityName();
		String commodityModel = po.getCommodityModel();
		int number = po.getNumber();
		double avgPrice = po.getAvgPrice();
		String batch = po.getBatch();
		String batchNumber = po.getBatchNumber();
		StockVO vo = new StockVO(commodityId, commodityName, commodityModel,
				number, avgPrice, batch, batchNumber);
		return vo;
	}
	
	/**
	 * 在一个商品列表中消去促销包商品，并把对应商品加进去
	 * @param commodityList
	 * @return
	 */
	public ArrayList<CommodityLineItemVO> removeSpecial(ArrayList<CommodityLineItemVO> commodityList){
		ArrayList<CommodityLineItemVO> result=new ArrayList<CommodityLineItemVO>();
		for(CommodityLineItemVO vo:commodityList){
			if(vo.id.compareTo("9999")>0){
				SpecialOfferVO spevo=new SpecialOfferPromotion().getById(vo.id);
				ArrayList<CommodityLineItemVO> spList=spevo.commodityList;
				result.addAll(spList);
			}else{
				result.add(vo);
			}
		}
		return result;
	}




}
