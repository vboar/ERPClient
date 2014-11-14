/**
 * 报警单VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

import java.util.ArrayList;

import util.DocumentType;

public class WarningVO implements DocumentVO {
	
	/**
	 * 报警单编号
	 */
	public String id;
	
	/**
	 * 创建时间
	 */
	public String time;
	
	/**
	 * 商品列表
	 */
	public ArrayList<WarningLineItemVO> list;
	
	/**
	 * 单据类型
	 */
	public DocumentType type;
	
	/**
	 * 构造方法
	 * @param id
	 * @param time
	 * @param list
	 * @param type
	 */
	public WarningVO(String id, String time, ArrayList<WarningLineItemVO> list, DocumentType type) {
		this.id = id;
		this.time = time;
		this.type = type;
	}
	
}
