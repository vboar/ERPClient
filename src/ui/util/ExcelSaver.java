package ui.util;

import util.ResultMessage;

/**
 * 获取存储路径接口
 * @author JanelDQ
 * @date 2014/12/12
 */
public interface ExcelSaver {

	/**
	 * 获得存储路径
	 * @param path
	 */
	public ResultMessage setSavePath(String path);
	
	/**
	 * 获取默认路径
	 * @param path
	 */
	public String getDefaultPath();
}
