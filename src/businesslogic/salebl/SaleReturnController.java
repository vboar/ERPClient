/**
 * SaleReturnController
 * @author oneoneO
 * @date 2014/11/14
 */
package businesslogic.salebl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.SaleVO;
import businesslogicservice.saleblservice.SaleBLService;

public class SaleReturnController implements SaleBLService {
	
	SaleReturn sr=new SaleReturn();

	@Override
	public ResultMessage add(SaleVO vo) {
		
		return sr.add(vo);
	}

	@Override
	public ArrayList<SaleVO> findByTime(String time1, String time2) {
		
		return sr.findByTime(time1, time2);
	}

	@Override
	public ArrayList<SaleVO> show() {
		
		return sr.show();
	}
}
