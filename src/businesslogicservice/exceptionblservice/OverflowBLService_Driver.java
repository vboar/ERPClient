/**
 * 报溢单业务逻辑驱动程序
 * @author Vboar
 * @date 2014/10/25
 */
package businesslogicservice.exceptionblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.ExceptionLineItemVO;
import vo.ExceptionVO;

public class OverflowBLService_Driver {

	public void drive(ExceptionBLService exceptionBLService) {
		
		System.out.println("创建报溢单的返回信息：");
		ArrayList<ExceptionLineItemVO> list = new ArrayList<ExceptionLineItemVO>();
		list.add(new ExceptionLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP01", 30, 50));
		ResultMessage result = exceptionBLService.create(
				new ExceptionVO("ECP-20141023-00001", "22:21:37", list, DocumentStatus.NONCHECKED,
						DocumentType.OVERFLOW, false));
		if (result == ResultMessage.SUCCESS) System.out.println("报溢单创建成功！\n");
		else System.out.println("报溢单创建失败！\n");
		
		System.out.println("根据时间段查找报溢单：");
		ArrayList<ExceptionVO> list2 = exceptionBLService.show("2014/10/25/20/57/10", 
				"2014/10/25/20/59/10");
		System.out.println("报溢单：" + list2.get(0).id + " " + list2.get(0).time + "\n");
		
		System.out.println("根据编号查找报溢单：");
		list2 = exceptionBLService.findById("ECP-20141023-00001");
		System.out.println("报溢单：" + list2.get(0).id + " " + list2.get(0).time + "\n");
		
		System.out.println("根据单据状态查找报溢单：");
		list2 = exceptionBLService.findByStatus(DocumentStatus.NONCHECKED);
		System.out.println("报溢单：" + list2.get(0).id + " " + list2.get(0).time + "\n");
		
		System.out.println("更新报溢单的返回信息：");
		list = new ArrayList<ExceptionLineItemVO>();
		list.add(new ExceptionLineItemVO("00001-00001-00001", "飞利浦吊灯", "FLP05", 30, 50));
		result = exceptionBLService.create(
				new ExceptionVO("ECP-20141023-00001", "22:21:37", list, DocumentStatus.PASSED,
						DocumentType.OVERFLOW, false));
		if (result == ResultMessage.SUCCESS) System.out.println("报溢单更新成功！\n");
		else System.out.println("报溢单更新失败！\n");
	}
	
}
