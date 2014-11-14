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
	CASH // 现金费用单
}
