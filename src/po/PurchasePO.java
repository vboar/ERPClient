/**
 * 进货单PO类
 * @author oneoneO
 * @date  2014/10/25 
 */
package po;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchasePO implements Serializable {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 单据编号
	 */
	private String id;
	
	/**
	 * 对应进货单ID(针对退货单)
	 */
	private String purId;
	
	/**
	 * 创建时间
	 */
	private String time;
	   
	/**
	* 客户编号
	*/
	private String customerId;	   
	
	/**
	 * 客户姓名
	 */
	private String customerName;
	   
	/**
	* 操作员
	*/
	private String operatorId;
	   
	/**
	* 仓库
	*/
	private String storage;
	   
	/**
	* 入库商品列表
	*/
	private ArrayList<CommodityLineItemPO> saleList;
	   
	/**
	* 总额
	*/
	private double total;
	   
	/**
	* 备注
	*/
	private String remark;
	   
	/**
	* 审批状态
	*/
	private int documentStatus;
	   
	/**
	* 是否为红冲单据
	*/
	private boolean isWriteOff;
	
	/**
	 * 是否可退货
	 */
	private boolean canReturn;
	
	/**
	 * 是否可红冲
	 */
	private boolean canWriteOff;
	   
	/**
	* 单据类型
	*/
	private int documentType;

	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param customerId
	 * @param customerName
	 * @param operatorId
	 * @param storage
	 * @param saleList
	 * @param total
	 * @param remark
	 * @param documentStatus
	 * @param isWriteOff
	 * @param canReturn
	 * @param canWriteOff
	 * @param documentType
	 */
	public PurchasePO(String id, String purId, String time,String customerId,String customerName,
			String operatorId,String storage,ArrayList<CommodityLineItemPO> saleList,double total,
			String remark,int documentStatus,boolean isWriteOff,
			boolean canReturn, boolean canWriteOff, int documentType){
		this.time = time;
		this.id=id;
		this.purId = purId;
		this.customerId=customerId;
		this.customerName=customerName;
		this.operatorId=operatorId;
		this.storage=storage;
		this.saleList=saleList;
		this.total=total;
		this.remark=remark;
		this.documentStatus=documentStatus;
		this.isWriteOff=isWriteOff;
		this.canReturn = canReturn;
		this.canWriteOff = canWriteOff;
		this.documentType=documentType;
	}

	public boolean isWriteOff() {
		return isWriteOff;
	}

	public void setWriteOff(boolean isWriteOff) {
		this.isWriteOff = isWriteOff;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getStorage() {
		return storage;
	}

	public ArrayList<CommodityLineItemPO> getSaleList() {
		return saleList;
	}

	public double getTotal() {
		return total;
	}

	public String getRemark() {
		return remark;
	}

	public int getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(int documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public int getDocumentType() {
		return documentType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isCanReturn() {
		return canReturn;
	}

	public boolean isCanWriteOff() {
		return canWriteOff;
	}

	public String getPurId() {
		return purId;
	}
		
}
