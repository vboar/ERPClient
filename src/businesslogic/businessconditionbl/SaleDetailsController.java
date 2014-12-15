package businesslogic.businessconditionbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	public ResultMessage exportExcel(String path,RequirementVO vo) {
		sd.addLog("导出销售明细Excel");
		return sd.exportExcel(path, vo);
	}

	@Override
	public String getDefaultPath() {
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		String time=df.format(new Date());
		String path="销售明细表.xls"+time;
		return path;
	}

}
