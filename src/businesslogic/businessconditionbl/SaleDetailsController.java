package businesslogic.businessconditionbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.RequirementVO;
import vo.SaleDetailsVO;
import businesslogicservice.businessconditionblservice.SaleDetailsBLService;

public class SaleDetailsController implements SaleDetailsBLService {
	SaleDetails sd=new SaleDetails();
	
	@Override
	public ArrayList<SaleDetailsVO> show(RequirementVO vo) {
		sd.addLog("查看销售明细");
		return sd.show(vo);
	}

	@Override
	public ResultMessage exportExcel(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
