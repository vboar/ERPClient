package businesslogic.promotionbl;

import businesslogic.commoditybl.Category;
import businesslogic.commoditybl.Commodity;
import businesslogic.utilitybl.Utility;
import dataservice.datafactoryservice.DataFactoryImpl;
import po.CategoryPO;
import po.CommodityLineItemPO;
import po.SpecialOfferPO;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SpecialOfferPromotion {

	public String createId() {
		ArrayList<SpecialOfferVO> voList = show();

		if (voList.size() == 0) {
			return "SPO00";
		} else {
			String max = voList.get(voList.size() - 1).id;
			String oldMax = max.substring(max.length() - 2);
			int maxInt = Integer.parseInt(oldMax);
			String pattern = "00";
			java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
			String maxStr = df.format(maxInt + 1);
			return "SPO" + maxStr;
		}

	}

	public ResultMessage add(SpecialOfferVO vo) {
		SpecialOfferPO po = voToPO(vo);
		String commodityId = vo.id;
		String commodityName = "";
		for (CommodityLineItemVO vo1 : vo.commodityList) {
			commodityName += vo1.name + "*" + vo1.number + "&";
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
		CategoryPO pocheck=cat.getById("99999");
		if(pocheck==null){
			CategoryVO catvo=new CategoryVO("99999", "促销包们", 0, null);
			cat.add(catvo);
		}
		CategoryVO catvo = cat.CategoryPOToCategoryVO(cat.getById("99999"));
		System.out.println("specialofferpromotionbl 62: catvoid "+catvo.id);
		CommodityVO commodityvo = new CommodityVO("99999-" + commodityId,
				commodityName, "促销包", 0, 0, vo.total, 0, 0, 0, false, catvo);
		
		Commodity commoditytest=new Commodity();
		ResultMessage resultmessage=commoditytest.add(commodityvo);
		System.out.println("specialofferpromotionbl 67: message"+resultmessage.toFriendlyString());
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
			if (!Utility.inTime(po.getStartTime(), po.getEndTime())) {
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
