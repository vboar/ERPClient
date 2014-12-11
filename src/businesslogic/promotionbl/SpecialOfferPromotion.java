package businesslogic.promotionbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityLineItemPO;
import po.SpecialOfferPO;
import util.ResultMessage;
import vo.CategoryVO;
import vo.CommodityLineItemVO;
import vo.CommodityVO;
import vo.SaleVO;
import vo.SpecialOfferVO;
import businesslogic.commoditybl.Category;
import businesslogic.commoditybl.Commodity;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;

public class SpecialOfferPromotion {

	public String createId() {
		ArrayList<SpecialOfferVO> voList = show();

		if (voList.size() == 0) {
			return "SPO-00000";
		} else {
			String max = voList.get(voList.size() - 1).id;
			String oldMax = max.substring(max.length() - 5);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00000";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "SPO-" + maxStr;
		}

	}

	public ResultMessage add(SpecialOfferVO vo) {
		SpecialOfferPO po = voToPO(vo);
		String commodityId = vo.id;
		String commodityName = null;
		for (CommodityLineItemVO vo1 : vo.commodityList) {
			commodityName += vo1.name + "x" + vo1.number + " ";
		}

		if (!Utility.checkTime(vo.startTime, vo.endTime)) {
			return ResultMessage.TIME_ERROR;
		}
		try {
			DataFactoryImpl.getInstance().getSpecialOfferData().insert(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Category cat = new Category();
		CategoryVO catvo = cat.CategoryPOToCategoryVO(cat.getById("99999"));
		CommodityVO commodityvo = new CommodityVO("99999-" + commodityId,
				commodityName, "nothing", 0, 0, vo.total, 0, 0, 0, false, catvo);
		new Commodity().add(commodityvo);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(SpecialOfferVO vo) {
		SpecialOfferPO po = voToPO(vo);
		if (!Utility.checkTime(vo.startTime, vo.endTime)) {
			return ResultMessage.TIME_ERROR;
		}
		try {
			DataFactoryImpl.getInstance().getSpecialOfferData().update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.SUCCESS;
	}

	public ArrayList<SpecialOfferVO> show() {
		ArrayList<SpecialOfferPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSpecialOfferData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<SpecialOfferVO> voList = new ArrayList<SpecialOfferVO>();
		for (SpecialOfferPO po : poList) {
			if (!po.isValid()) {
				continue;
			}
			if (Utility.inTime(po.getStartTime(), po.getEndTime())) {
				continue;
			}
			SpecialOfferVO vo = poToVo(po);
			voList.add(vo);
		}
		return voList;
	}
	
	
	public ArrayList<SpecialOfferVO> showAll() {
		ArrayList<SpecialOfferPO> poList = null;
		try {
			poList = DataFactoryImpl.getInstance().getSpecialOfferData().show();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<SpecialOfferVO> voList = new ArrayList<SpecialOfferVO>();
		for (SpecialOfferPO po : poList) {
			SpecialOfferVO vo = poToVo(po);
			voList.add(vo);
		}
		return voList;
	}

	public SpecialOfferVO getById(String id){
		ArrayList<SpecialOfferVO> voList=showAll();
		for(SpecialOfferVO vo:voList){
			if(vo.id.equals(id)){
				return vo;
			}
		}
		return null;
	}
	

	public SaleVO calBonus(SaleVO saleVO, SpecialOfferVO specialOfferVO) {
		int validnumber = 0;
		for (CommodityLineItemVO voCheck : specialOfferVO.commodityList) {
			for (CommodityLineItemVO voBuy : saleVO.saleList) {
				if (voBuy.id.equals(voCheck.id)
						&& voBuy.number >= voCheck.number) {
					validnumber++;
				}
			}
		}
		if (validnumber != specialOfferVO.commodityList.size()) {
			saleVO.remark += "选购商品不满足促销条件";
		}
		// TODO
		// 促销包怎么搞
		return saleVO;

	}

	private SpecialOfferPO voToPO(SpecialOfferVO vo) {
		String id=vo.id;
		ArrayList<CommodityLineItemPO> giftInfo = Utility
				.voListToPOList(vo.commodityList);
		double total = vo.total;
		String startTime = vo.startTime;
		String endTime = vo.endTime;
		boolean valid = vo.valid;
		SpecialOfferPO po = new SpecialOfferPO(id, giftInfo, total, startTime,
				endTime, valid);
		return po;
	}

	private SpecialOfferVO poToVo(SpecialOfferPO po) {
		String id = po.getId();
		ArrayList<CommodityLineItemVO> giftInfo = Utility.poListToVOList(po
				.getCommodityList());

		double total = po.getTotal();
		String startTime = po.getStartTime();
		String endTime = po.getEndTime();
		boolean valid = po.isValid();
		SpecialOfferVO vo = new SpecialOfferVO(id, giftInfo, total, startTime,
				endTime, valid);
		return vo;

	}
}
