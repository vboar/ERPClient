/**
 * 单据类型枚举类型
 * @author Vboar
 * 2014/10/25
 */
package util;

public enum DocumentType {
	PRESENT, // 赠品单
	OVERFLOW, // 报溢单
	LOSS, // 报损单
	WARNING, // 报警单
	SALE, // 销售单
	SALERETURN, // 销售退货单
	PURCHASE, // 进货单
	PURCHASERETURN, // 进货退货单
	RECEIPT, // 收款单
	PAYMENT, // 付款单
	CASH, // 现金费用单
	PRESENTRETURN;//赠品退货单
	
	public String toReadableString(){
		switch(this){
		case PRESENT:
			return "赠送单";
		case OVERFLOW:
			return "库存报溢单";
		case LOSS:
			return "库存报损单";
		case WARNING:
			return "库存报警单";
		case SALE:
			return "销售单";
		case SALERETURN:
			return "销售退货单";
		case  PURCHASE:
			return "进货单";
		case PURCHASERETURN:
			return "进货退货单";
		case RECEIPT:
			return "收款单";
		case PAYMENT:
			return "付款单";
		case CASH:
			return "现金费用单";
		case PRESENTRETURN:
			return "赠品退货单";
		default:
			return null;
		}
	}
	
	public static DocumentType strToType(String type){
		switch(type){
		case "赠送单":
			return PRESENT;
		case "库存报溢单":
			return OVERFLOW;
		case "库存报损单":
			return LOSS;
		case "库存报警单":
			return WARNING;
		case "销售单":
			return SALE;
		case "销售退货单":
			return SALERETURN;
		case "进货单":
			return PURCHASE;
		case "进货退货单":
			return PURCHASERETURN;
		case "收款单":
			return RECEIPT;
		case "付款单":
			return PAYMENT;
		case "现金费用单":
			return CASH;
		case "赠品退货单":
			return PRESENTRETURN;
		default:
			return null;
		}
	}
}
