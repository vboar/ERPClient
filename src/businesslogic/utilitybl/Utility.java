package businesslogic.utilitybl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import po.CommodityLineItemPO;
import util.ResultMessage;
import vo.CommodityLineItemVO;

public class Utility {

	/**
	 * @author chengcheng
	 * @param input
	 *            输入的内容
	 * @param min
	 *            允许的最大长度
	 * @param max
	 *            允许的最小长度
	 * @param Chinese
	 *            是否支持中文
	 * @return ResultMessage
	 */
	public static ResultMessage checkInputValid(String input, int min, int max,
			boolean Chinese) {
		if (input.length() > max) {
			return ResultMessage.TOO_LONG;
		} else if (input.length() < min) {
			return ResultMessage.TOO_SHORT;
		}
		char[] inputList = input.toCharArray();
		for (char i : inputList) {
			if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z')
					|| (i >= '0' && i <= '9')) {
				continue;
			} else if (Chinese && i >= 19968 && i <= 171941) {
				continue;
			} else {
				return ResultMessage.UNVALID;
			}
		}
		return ResultMessage.SUCCESS;
	}

	public static boolean checkTime(String startTime, String endTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean flag = start.before(end);
		return flag;
	}

	public static boolean inTime(String startTime,String endTime) {
		java.util.Date nowdate = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean flag1 = start.before(nowdate);
		boolean flag2 = end.after(nowdate);
		return flag1&&flag2;
	}
	
public static ArrayList<CommodityLineItemPO> voListToPOList(ArrayList<CommodityLineItemVO> voList){
	ArrayList<CommodityLineItemPO> poList=new ArrayList<CommodityLineItemPO>();
		for(CommodityLineItemVO vo:voList){
			String id=vo.id;
			String name=vo.name;
			String model=vo.model;
			int number=vo.number;
            double price=vo.price;
            double total=vo.total;
            String remark=vo.remark;
            CommodityLineItemPO po=new CommodityLineItemPO(id, name, model, number, price, total, remark);
            poList.add(po);						
		}
		return poList;
	}

public static ArrayList<CommodityLineItemVO> poListToVOList(ArrayList<CommodityLineItemPO> poList){
	ArrayList<CommodityLineItemVO> voList=new ArrayList<CommodityLineItemVO>();
		for(CommodityLineItemPO po:poList){
			String id=po.getId();
			String name=po.getName();
			String model=po.getModel();
			int number=po.getNumber();
            double price=po.getPrice();
            double total=po.getTotal();
            String remark=po.getRemark();
            CommodityLineItemVO vo=new CommodityLineItemVO(id, name, model, number, price, total, remark);
            voList.add(vo);						
		}
		return voList;
	}

//	public static void main(String[] args) {
//		// java.util.Date nowdate=new java.util.Date();
//		System.out.println(inTime("2014/11/26","2014/12/15"));
//	}

}
