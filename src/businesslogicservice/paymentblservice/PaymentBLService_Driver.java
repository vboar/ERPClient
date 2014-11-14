/**
 * 付款服务驱动程序
 * @author JanelDQ
 *
 */
package businesslogicservice.paymentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PaymentVO;
import vo.TransferLineItemVO;

public class PaymentBLService_Driver {

	public void drive(PaymentBLService paymentBLService){
		
		ResultMessage result;
		
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		
		System.out.println("创建付款单返回信息：");
		result = paymentBLService.create(new PaymentVO("FKD-20141025-00001","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.PAYMENT));
		if (result == ResultMessage.SUCCESS) System.out.println("付款单创建成功！\n");
		else System.out.println("付款单创建失败！\n");
		
		System.out.println("更新付款单返回信息：");
		result = paymentBLService.update(new PaymentVO("FKD-20141025-00001","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.PAYMENT));
		if (result == ResultMessage.SUCCESS) System.out.println("付款单删除成功！\n");
		else System.out.println("付款单删除失败！\n");
		
		System.out.println("根据审批状态查找付款单：");
		ArrayList<PaymentVO> list1 = paymentBLService.findByStatus(DocumentStatus.PASSED);
		System.out.println("单据编号："+list1.get(0).id+"；操作员："+list1.get(0).operator+"；客户编号："
				+list1.get(0).customerId+"；客户名称："+list1.get(0).customerName+"；金额："+list1.get(0).total+"\n");
		
		System.out.println("根据单据编号查找付款费用单：");
		ArrayList<PaymentVO> list2 = paymentBLService.findById("FKD-20141025-00001");
		System.out.println("单据编号："+list2.get(0).id+"；操作员："+list2.get(0).operator+"；客户编号："
				+list2.get(0).customerId+"；客户名称："+list2.get(0).customerName+"；金额："+list2.get(0).total+"\n");
		
		System.out.println("查看一段时间内的付款单：");
		ArrayList<PaymentVO> list3 = paymentBLService.show("20141023","20141025");
		System.out.println("单据编号："+list3.get(0).id+"；操作员："+list3.get(0).operator+"；客户编号："
				+list3.get(0).customerId+"；客户名称："+list3.get(0).customerName+"；金额："+list3.get(0).total+"\n");
		
		
	}
}
