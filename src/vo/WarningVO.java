/**
 * 报警单VO类
 * @author Vboar
 * @date 2014/10/25
 */
package vo;

import util.DocumentStatus;
import util.DocumentType;

import java.util.ArrayList;

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
	 * 
	 * @param id
	 * @param time
	 * @param list
	 * @param type
	 */
	public WarningVO(String id, String time, ArrayList<WarningLineItemVO> list,
			DocumentType type) {
		this.id = id;
		this.time = time;
		this.list = list;
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public DocumentStatus getStatus() {
		return null;
	}

	@Override
	public DocumentType getType() {
		return type;
	}

	@Override
	public boolean isWriteoff() {
		return isWriteoff();
	}

	@Override
	public String getTime() {
		return time;
	}

	@Override
	public void setStatus(DocumentStatus status) {
	}

	public String listToStr() {
		String str = "";
		if(list==null){
			return str;
		}
		for (int i = 0; i < list.size() - 1; ++i) {
			str = str + list.get(i).toString() + "\n";
		}
		str = str + list.get(list.size() - 1).toString();
		return str;
	}
}
