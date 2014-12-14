/**
 * overflowcontroller
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import businesslogicservice.exceptionblservice.ExceptionBLService;
import util.DocumentStatus;
import util.ResultMessage;
import vo.ExceptionVO;

import java.util.ArrayList;

public class OverflowController implements ExceptionBLService {
	Overflow of=new Overflow();
	
	@Override
	public ResultMessage create(ExceptionVO vo) {
		of.addLog("增加报溢单");
		return of.create(vo);
	}

	@Override
	public ArrayList<ExceptionVO> show(String time1, String time2) {
		of.addLog("按时间显示报溢单");
		return of.findByTime(time1, time2);
	}

	@Override
	public ArrayList<ExceptionVO> findByStatus(DocumentStatus status) {
		of.addLog("按状态查找报溢单");
		return of.findByStatus(status);
	}

	@Override
	public ResultMessage update(ExceptionVO vo) {
		of.addLog("更新报溢单");
		return of.update(vo);
	}

	@Override
	public String createId() {
		of.addLog("获得报溢单ID");
		return of.createId();
	}

	@Override
	public ArrayList<ExceptionVO> findById(String id) {
		of.addLog("按ID查找报溢单");
		return of.findById(id);
	}

	public ExceptionVO getById(String id){
		of.addLog("按ID唯一查找报溢单");
		return of.getById(id);
	}
}
