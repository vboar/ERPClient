/**
 * warningcontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import java.util.ArrayList;

import util.ResultMessage;
import vo.WarningVO;
import businesslogicservice.exceptionblservice.WarningBLService;

public class WarningController implements WarningBLService {
	Warning w=new Warning();
	
	@Override
	public ResultMessage create(WarningVO vo) {
		w.createLog("创建报警单");
		return w.create(vo);
	}

	@Override
	public ArrayList<WarningVO> show(String time1, String time2) {
		w.createLog("按时间显示报警单");
		return w.findByTime(time1, time2);
	}

	@Override
	public ArrayList<WarningVO> findById(String id) {
		w.createLog("根据ID查找报警单");
		return w.fingById(id);
	}

	@Override
	public WarningVO getByid(String id) {
		w.createLog("根据ID唯一查找报警单");
		return w.getById(id);
	}

}
