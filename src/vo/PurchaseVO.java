package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class PurchaseVO {

	   /**
	    * 单据编号
	    */
	 public String receiptId;
	   
	   /**
	    * 客户编号
	    */
	 public String customerId;	   
	   
	   /**
	    * 客户姓名
	    */
	 public String name;
	   
	   /**
	    * 操作员
	    */
	 public String operator;
	   
	   /**
	    * 仓库
	    */
	 public String storage;
	   
	   /**
	    * 入库商品列表
	    */
	 public ArrayList<CommodityLineItemVO> saleList;
	   
	   /**
	    * 总额
	    */
	 public double total;
	   
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
	    * 单据类型
	    */
	 public DocumentType receiptType;
	   
	  /**
	   * 构造方法
	   * 
	   * @param receiptId
	   * @param customerId
	   * @param name
	   * @param operator
	   * @param storage
	   * @param saleList
	   * @param totalBeforeDiscount
	   * @param discount
	   * @param voucher
	   * @param totalAfterDiscount
	   * @param remark
	   * @param approvalState
	   * @param isWriteOff
	   * @param receiptType
	   */
	 public PurchaseVO(String receiptId,String customerId,String name,
	   String operator,String storage,ArrayList<CommodityLineItemVO> saleList,double total,
	   String remark,DocumentStatus approvalState,boolean isWriteOff,DocumentType receiptType){
		   this.receiptId=receiptId;
		   this.customerId=customerId;
		   this.name=name;
		   this.operator=operator;
		   this.storage=storage;
		   this.saleList=saleList;
		   this.total=total;
		   this.remark=remark;
		   this.approvalState=approvalState;
		   this.isWriteOff=isWriteOff;
		   this.receiptType=receiptType;
	   }
	 
	 public PurchaseVO(String operator, double total){
		 this.operator = operator;
		 this.total = total;
	 }
}
