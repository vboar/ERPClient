package vo;

import java.util.ArrayList;

import util.DocumentStatus;
import util.DocumentType;

public class SaleVO {
	
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
	  * 业务员
	  */
	 public String salesman;
	   
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
	   public SaleVO(String receiptId,String customerId,String name,String salesman,
	   String operator,String storage,ArrayList<CommodityLineItemVO> saleList,double totalBeforeDiscount,
	   double discount,double voucher,double totalAfterDiscount,String remark,
	   DocumentStatus approvalState,boolean isWriteOff,DocumentType receiptType){
		   this.receiptId=receiptId;
		   this.customerId=customerId;
		   this.name=name;
		   this.salesman=salesman;
		   this.operator=operator;
		   this.storage=storage;
		   this.saleList=saleList;
		   this.totalBeforeDiscount=totalBeforeDiscount;
		   this.discount=discount;
		   this.voucher=voucher;
		   this.totalAfterDiscount=totalAfterDiscount;
		   this.remark=remark;
		   this.approvalState=approvalState;
		   this.isWriteOff=isWriteOff;
		   this.receiptType=receiptType;
	   } 
	   
	   public SaleVO(String name, double total){
		   this.name = name;
		   this.totalAfterDiscount = total;
	   }
}
