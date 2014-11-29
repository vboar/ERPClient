/**
 * SaleController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.ResultMessage;
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

}
