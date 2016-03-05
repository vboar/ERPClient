package businesslogic.utilitybl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import po.CommodityLineItemPO;
import po.PresentLineItemPO;
import util.ResultMessage;
import vo.CommodityLineItemVO;
import vo.PresentLineItemVO;

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
		if(input.length() == 0) {
			return ResultMessage.NULL;
		}
		char[] inputList = input.toCharArray();
		for (char i : inputList) {
			if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z')
					|| (i >= '0' && i <= '9')||(i=='&')||(i=='*')||(i=='-')||(i=='>')) {
				continue;
			} else if (Chinese && i >= 19968 && i <= 171941) {
				continue;
			} else {
				return ResultMessage.UNVALID;
			}
		}
		if (input.length() > max) {
			return ResultMessage.TOO_LONG;
		} else if (input.length() < min) {
			return ResultMessage.TOO_SHORT;
		}
		return ResultMessage.SUCCESS;
	}

	/**
	 * 检查输入的开始时间是否在结束时间之前
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
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

	/**
	 * 检查当前时间是否在输入的时间区间内
	 * @param startTime
	 * @param endTime
	 * @return
	 */
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
	
	/**
	 * commoditylineitem的vo转换po
	 * @param voList
	 * @return
	 */
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
/**
 * commodity的po转换vo
 * @param poList
 * @return
 */
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
/**
 * presentLineItem的po转vo
 * @param poList
 * @return
 */
public static ArrayList<PresentLineItemVO> presentPOListToVOList(ArrayList<PresentLineItemPO> poList){
	ArrayList<PresentLineItemVO> voList=new ArrayList<PresentLineItemVO>();
	for(PresentLineItemPO po:poList){
		String id=po.getId();
		String name=po.getName();
		String model=po.getModel();
		int number=po.getNumber();
		PresentLineItemVO vo=new PresentLineItemVO(id, name, model, number);
		voList.add(vo);
	}
	return voList;
}

public static ArrayList<PresentLineItemPO> presentVOListToPOlist(ArrayList<PresentLineItemVO> voList){
	ArrayList<PresentLineItemPO> poList=new ArrayList<PresentLineItemPO>();
	for(PresentLineItemVO vo:voList){
		String id=vo.id;
		String name=vo.name;
		String model=vo.model;
		int number=vo.number;
		PresentLineItemPO po=new PresentLineItemPO(id, name, model, number);
		poList.add(po);
	}
	return poList;
}

public static String getCurrentTime(){
	Date date=new Date();
	SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String time=myFmt.format(date);

	return time;
}





//	public static void main(String[] args) {
//		String time;
//		Date date=new Date();
//		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
//
//		System.out.println(myFmt.format(date));
//		
//	}

}
