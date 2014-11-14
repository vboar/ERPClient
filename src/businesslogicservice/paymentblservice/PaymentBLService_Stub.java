/**
 * 付款服务桩程序
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

public class PaymentBLService_Stub implements PaymentBLService {

	@Override
	public ResultMessage create(PaymentVO vo) {
		return ResultMessage.FAILED;
	}

	@Override
	public ArrayList<PaymentVO> show(String time1, String time2) {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		list.add(new PaymentVO("FKD-"+time1+"-00001","00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.PAYMENT));
		return list;
	}

	@Override
	public ResultMessage update(PaymentVO vo) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<PaymentVO> findByStatus(DocumentStatus status) {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		list.add(new PaymentVO("FKD-20141025-00001","00002","雷神托尔","金刚狼",
				transferList,700,status, DocumentType.PAYMENT));
		return list;
	}

	@Override
	public ArrayList<PaymentVO> findById(String id) {
		ArrayList<PaymentVO> list = new ArrayList<PaymentVO>();
		ArrayList<TransferLineItemVO> transferList = new ArrayList<TransferLineItemVO>();
		transferList.add(new TransferLineItemVO("工商银行账户1",1000,"无"));
		list.add(new PaymentVO(id,"00002","雷神托尔","金刚狼",
				transferList,700,DocumentStatus.PASSED, DocumentType.PAYMENT));
		return list;
	}

}
