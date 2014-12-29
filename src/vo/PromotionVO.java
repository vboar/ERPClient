package vo;

import java.util.ArrayList;

public class PromotionVO {
	
	/**
	 * 这个促销策略实际的id
	 */
	public String id;
	/**
	 * 赠品列表
	 */
	public ArrayList<PresentLineItemVO> giftList;
	/**
	 * 折让
	 */
	public double discount;

	/**
	 * 代金券
	 */
	public double voucher;

	/**
	 * 
	 * @param giftList
	 * @param discount
	 * @param voucher
	 */
	public PromotionVO(String id,ArrayList<PresentLineItemVO> giftList, double discount,
			double voucher) {
		super();
		this.id=id;
		this.giftList = giftList;
		this.discount = discount;
		this.voucher = voucher;
	}
	
	@Override
	public String toString(){
		String str = "促销编号:"+ id;
		if(giftList!=null&&giftList.size()>0){
			str += "  赠品列表：";
			for(int i=0; i<giftList.size(); ++i){
				str += " "+ giftList.get(i).name + "-" + giftList.get(i).model + "*" 
						+ giftList.get(i).number + "、";
			}
		}
		str = str + "  折扣: "+ discount + "  赠送代金券：" + voucher;
		return str;
	}
}
