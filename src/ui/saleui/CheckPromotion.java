package ui.saleui;

import java.util.ArrayList;

import vo.PromotionVO;

public interface CheckPromotion {

	public ArrayList<PromotionVO> getPromotions();
	
	public void calTotal();
}
