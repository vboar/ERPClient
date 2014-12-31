/**
 * 单据状态枚举类型
 * @author Vboar
 * 2014/10/25
 */
package util;

public enum DocumentStatus {
	NONCHECKED, // 待审批
	PASSED, //通过
	FAILED; // 不通过

	/**
	 * 枚举类型转中文String
	 * @return
	 */
	public String toReadableString(){
		switch(this){
		case NONCHECKED:
			return "待审批";
		case PASSED:
			return "审批通过";
		case FAILED:
			return "审批不通过";
		default:
			return null;
		}
	}
}