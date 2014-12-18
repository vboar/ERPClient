package ui.saleui;

import java.util.ArrayList;

import vo.PromotionVO;

public interface GetPromotions {

	public ArrayList<PromotionVO> getPromotions();
	
	public void calTotal();
}
