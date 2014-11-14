/**
 * 赠品单业务逻辑桩程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.presentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PresentLineItemVO;
import vo.PresentVO;

public class PresentBLService_Stub implements PresentBLService {

	@Override
	public ResultMessage create(PresentVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<PresentVO> show(String time1, String time2) {
		ArrayList<PresentVO> list = new ArrayList<PresentVO>();
		ArrayList<PresentLineItemVO> list2 = new ArrayList<PresentLineItemVO>();
		list2.add(new PresentLineItemVO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentVO("ZSD-20141023-00001", "21:29:32", "00001",
				"金刚狼",list2, DocumentStatus.NONCHECKED, false));
		return list;
	}

	@Override
	public ArrayList<PresentVO> findById(String id) {
		ArrayList<PresentVO> list = new ArrayList<PresentVO>();
		ArrayList<PresentLineItemVO> list2 = new ArrayList<PresentLineItemVO>();
		list2.add(new PresentLineItemVO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentVO(id, "21:29:32","00001","金刚狼", list2, DocumentStatus.NONCHECKED, false));
		return list;
	}

	@Override
	public ArrayList<PresentVO> findByStatus(DocumentStatus status) {
		ArrayList<PresentVO> list = new ArrayList<PresentVO>();
		ArrayList<PresentLineItemVO> list2 = new ArrayList<PresentLineItemVO>();
		list2.add(new PresentLineItemVO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		list.add(new PresentVO("ZSD-20141023-00001", "21:29:32","00001","金刚狼", list2, status, false));
		return list;
	}

	@Override
	public ResultMessage update(PresentVO vo) {
		return ResultMessage.SUCCESS;
	}

}
