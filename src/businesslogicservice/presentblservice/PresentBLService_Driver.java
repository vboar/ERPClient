/**
 * 赠品单业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.presentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.ResultMessage;
import vo.PresentLineItemVO;
import vo.PresentVO;

public class PresentBLService_Driver {
	
	public void drive(PresentBLService presentBLService) {
		
		System.out.println("创建赠送单：");
		ArrayList<PresentLineItemVO> list2 = new ArrayList<PresentLineItemVO>();
		list2.add(new PresentLineItemVO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		ResultMessage result = presentBLService.create(
				new PresentVO("ZSD-20141023-00001", "21:29:32","00001",
						"金刚狼", list2, DocumentStatus.NONCHECKED, false));
		if (result == ResultMessage.SUCCESS) System.out.println("创建赠送单成功！\n");
		else System.out.println("创建赠送单失败！\n");
		
		System.out.println("根据时间段查找赠送单：");
		ArrayList<PresentVO> list = presentBLService.show("2014/10/25/20/57/10", "2014/10/25/20/59/10");
		System.out.println("赠送单：" + list.get(0).id + " " + list.get(0).time + "\n");
		
		System.out.println("根据编号查找赠送单：");
		list = presentBLService.findById("ZSD-20141023-00001");
		System.out.println("赠送单：" + list.get(0).id + " " + list.get(0).time + "\n");
		
		System.out.println("根据状态查找赠送单：");
		list = presentBLService.findByStatus(DocumentStatus.NONCHECKED);
		System.out.println("赠送单：" + list.get(0).id + " " + list.get(0).time + "\n");
		
		System.out.println("更新赠送单：");
		list2 = new ArrayList<PresentLineItemVO>();
		list2.add(new PresentLineItemVO("00001-00001", "飞利浦吊灯", "FLP01", 50));
		result = presentBLService.update(
				new PresentVO("ZSD-20141023-00002", "21:29:32", "00001",
						"金刚狼",list2, DocumentStatus.NONCHECKED, false));
		if (result == ResultMessage.SUCCESS) System.out.println("更新赠送单成功！\n");
		else System.out.println("更新赠送单失败！\n");
		
	}

}
