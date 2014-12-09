/**
 * SaleController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PromotionVO;
import vo.SaleVO;
import businesslogicservice.saleblservice.SaleBLService;

public class SaleController implements SaleBLService {

	Sale sale=new Sale();
	@Override
	public ResultMessage add(SaleVO vo) {
		
		return sale.add(vo);
	}

	@Override
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		
		return sale.findByTime(time1, time2);
	}

	@Override
	public ArrayList<SaleVO> show() {
		
		return sale.show();
	}

	@Override
	public ArrayList<SaleVO> findByStatus(DocumentStatus status) {
		
		return sale.findByStatus(status.ordinal());
	}

	@Override
	public ArrayList<SaleVO> findByCustomer(String customer) {
		
		return sale.findByCustomer(customer);
	}

	@Override
	public String createId() {
		
		return sale.createId();
	}

	@Override
	public SaleVO getById(String id) {
		
		return sale.getById(id);
	}
	
	@Override
	public ArrayList<PromotionVO> calCustomerPromotion(int VIP) {
		
		return sale.calCustomerPromotion(VIP);
	}

	@Override
	public ArrayList<PromotionVO> calTotalGiftPromotion(double price) {
		
		return sale.calTotalGiftPromotion(price);
	}

	@Override
	public SaleVO calAfterPrice(String customerGiftId, String totalGiftId,
			SaleVO vo) {
		
		return null;
	}
	
	

}
