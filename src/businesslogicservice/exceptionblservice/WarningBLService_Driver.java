/**
 * 报溢单业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.exceptionblservice;

import java.util.ArrayList;

import util.DocumentType;
import util.ResultMessage;
import vo.WarningLineItemVO;
import vo.WarningVO;

public class WarningBLService_Driver {

	public void drive(WarningBLService warningBLService) {
		
		System.out.println("创建报警的返回信息：");
		ArrayList<WarningLineItemVO> list = new ArrayList<WarningLineItemVO>();
		list.add(new WarningLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 30, 50));
		ResultMessage result = warningBLService.create(
				new WarningVO("WAR-20141023-00001", "22:21:37", list, DocumentType.WARNING));
		if (result == ResultMessage.SUCCESS) System.out.println("报警单创建成功！\n");
		else System.out.println("报警单创建失败！\n");
		
		System.out.println("根据时间段查找报警单：");
		ArrayList<WarningVO> list2 = warningBLService.show("2014/10/25/20/57/10", 
				"2014/10/25/20/59/10");
		System.out.println("报警单：" + list2.get(0).id + " " + list2.get(0).time + "\n");
		
		System.out.println("根据编号查找报警单：");
		list2 = warningBLService.findById("WAR-20141023-00001");
		System.out.println("报警单：" + list2.get(0).id + " " + list2.get(0).time + "\n");
	
	}
	
}
