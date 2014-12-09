/**
 * losscontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.ExceptionVO;
import businesslogicservice.exceptionblservice.ExceptionBLService;

public class LossController implements ExceptionBLService {
	Loss l=new Loss();
	
	@Override
	public ResultMessage create(ExceptionVO vo) {
		l.addLog("增加报损单");
		return l.add(vo);
	}

	@Override
	public ArrayList<ExceptionVO> show(String time1, String time2) {
		l.addLog("显示一定时间内的报损单");
		return l.show(time1, time2);
	}

	@Override
	public ArrayList<ExceptionVO> findByStatus(DocumentStatus status) {
		l.addLog("根据状态查找报损单");
		return l.findByStatus(status.ordinal());
	}

	@Override
	public ResultMessage update(ExceptionVO vo) {
		l.addLog("更新报损单");
		return l.update(vo);
	}

	@Override
	public String createId() {
		l.addLog("获得新报损单Id");
		return l.createId();
	}

	@Override
	public ArrayList<ExceptionVO> findById(String id) {
		l.addLog("根据ID查找报损单");
		return l.findById(id);
	}

}
