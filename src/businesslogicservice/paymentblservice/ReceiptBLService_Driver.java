/**
 * 收款服务驱动程序
 * @author JanelDQ
 * @date 2014/10/25
 */
package businesslogicservice.paymentblservice;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;
import util.ResultMessage;
import vo.PaymentVO;
import vo.TransferLineItemVO;

public class ReceiptBLService_Driver {

	public void drive(PaymentBLService receiptBLService){
		ResultMessage result;
		
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		
		System.out.println("创建收款单返回信息：");
		result = receiptBLService.create(new PaymentVO("SKD-20141025-00001","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.RECEIPT));
		if (result == ResultMessage.SUCCESS) System.out.println("收款单创建成功！\n");
		else System.out.println("收款单创建失败！\n");
		
		System.out.println("更新收款单返回信息：");
		result = receiptBLService.update(new PaymentVO("SKD-20141025-00001","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.RECEIPT));
		if (result == ResultMessage.SUCCESS) System.out.println("收款单更新成功！\n");
		else System.out.println("收款单更新失败！\n");
		
		System.out.println("根据审批状态查找收款单：");
		ArrayList<PaymentVO> list1 = receiptBLService.findByStatus(DocumentStatus.PASSED);
		System.out.println("单据编号："+list1.get(0).id+"；操作员："+list1.get(0).operatorId+"；客户编号："
				+list1.get(0).customerId+"；客户名称："+list1.get(0).customerName+"；金额："+list1.get(0).total+"\n");
		
		System.out.println("根据单据编号查找收款单：");
		ArrayList<PaymentVO> list2 = receiptBLService.findById("XJFYD-20141025-00001");
		System.out.println("单据编号："+list2.get(0).id+"；操作员："+list2.get(0).operatorId+"；客户编号："
				+list2.get(0).customerId+"；客户名称："+list2.get(0).customerName+"；金额："+list2.get(0).total+"\n");
		
		System.out.println("查看一段时间内的收款单：");
		ArrayList<PaymentVO> list3 = receiptBLService.show("20141023","20141025");
		System.out.println("单据编号："+list3.get(0).id+"；操作员："+list3.get(0).operatorId+"；客户编号："
				+list3.get(0).customerId+"；客户名称："+list3.get(0).customerName+"；金额："+list3.get(0).total+"\n");
		
	}
}
