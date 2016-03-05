/**
 * 销售单VO类
 * @author oneoneO
 * @date 2014/12/25
 */

package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class SaleVO implements DocumentVO {

	/**
	 * 单据编号
	 */
	public String id;

	/**
	* 单据编号
	*/
	public String saleId;
	
	/**
	 * 创建时间
	 */
	public String time;

	/**
	 * 客户
	 */
	public String customerId;

	/**
	 * 客户姓名
	 */
	public String customerName;

	/**
	 * 客户等级
	 */
	public int customerVIP;

	/**
	 * 业务员
	 */
	public String salesmanId;

	/**
	 * 操作员id
	 */
	public String operatorId;

	/**
	 * 仓库
	 */
	public String storage;

	/**
	 * 入库商品列表
	 */
	public ArrayList<CommodityLineItemVO> saleList;

	/**
	 * 赠品列表
	 */
	public ArrayList<PresentLineItemVO> giftList;
	
	/**
	 * 赠品单编号
	 */
	public String presentId;

	/**
	 * 折让前总额
	 */
	public double totalBeforeDiscount;

	/**
	 * 折让
	 */
	public double discount;

	/**
	 * 代金券
	 */
	public double voucher;

	/**
	 * 折让后总额
	 */
	public double totalAfterDiscount;

	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 审批状态
	 */
	public DocumentStatus approvalState;

	/**
	 * 是否为红冲单据
	 */
	public boolean isWriteOff;
	
	/**
	 * 是否可退货
	 */
	public boolean canReturn;
	
	/**
	 * 是否可红冲
	 */
	public boolean canWriteOff;

	/**
	 * 单据类型
	 */
	public DocumentType receiptType;

	/**
	 * 构造方法
	 * @param id
	 * @param saleId
	 * @param time
	 * @param customerId
	 * @param customerName
	 * @param customerVIP
	 * @param salesmanId
	 * @param operatorId
	 * @param storage
	 * @param saleList
	 * @param presentId
	 * @param giftList
	 * @param totalBeforeDiscount
	 * @param discount
	 * @param voucher
	 * @param totalAfterDiscount
	 * @param remark
	 * @param approvalState
	 * @param isWriteOff
	 * @param canReturn
	 * @param canWriteOff
	 * @param receiptType
	 */
	public SaleVO(String id, String saleId, String time, String customerId,
			String customerName, int customerVIP, String salesmanId,
			String operatorId, String storage,
			ArrayList<CommodityLineItemVO> saleList,String presentId,
			ArrayList<PresentLineItemVO> giftList, double totalBeforeDiscount,
			double discount, double voucher, double totalAfterDiscount,
			String remark, DocumentStatus approvalState, boolean isWriteOff,
			boolean canReturn, boolean canWriteOff,	DocumentType receiptType) {
		this.id = id;
		this.saleId = saleId;
		this.time = time;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerVIP = customerVIP;
		this.salesmanId = salesmanId;

		this.operatorId = operatorId;
		this.storage = storage;
		this.saleList = saleList;
		this.presentId=presentId;
		this.giftList = giftList;
		this.totalBeforeDiscount = totalBeforeDiscount;
		this.discount = discount;
		this.voucher = voucher;
		this.totalAfterDiscount = totalAfterDiscount;
		this.remark = remark;
		this.approvalState = approvalState;
		this.isWriteOff = isWriteOff;
		this.canReturn = canReturn;
		this.canWriteOff = canWriteOff;
		this.receiptType = receiptType;
	}

	/**
	 * 无参构造方法
	 */
	public SaleVO(){
		
	}

	/**
	 * 构造方法
	 * @param name
	 * @param total
	 */
	public SaleVO(String name, double total) {
		this.customerName = name;
		this.totalAfterDiscount = total;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public DocumentStatus getStatus() {
		return approvalState;
	}

	@Override
	public DocumentType getType() {
		return receiptType;
	}

	@Override
	public boolean isWriteoff() {
		return isWriteOff;
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public void setStatus(DocumentStatus status) {
		this.approvalState = status;
	}

	/**
	 * 将赠品条目转成String
	 * @return
	 */
	public String giftListToStr(){
		String str="";
		for(int i=0; i<giftList.size()-1; ++i){
			str = str + giftList.get(i).toString() + ",";
		}
		if(giftList.size()>0)
			str = str + giftList.get(giftList.size()-1).toString();
		return str;
	}

	/**
	 * 将商品条目转成String
	 * @return
	 */
	public String saleListToStr(){
		String str="";
		for(int i=0; i<saleList.size()-1; ++i){
			str = str + saleList.get(i).toString() + ",";
		}
		if(saleList.size()>0)
			str += saleList.get(saleList.size()-1).toString();
		return str;
	}
	
}
