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
	

}
