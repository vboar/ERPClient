package businesslogic.utilitybl;

import util.ResultMessage;

public class Utility {
	
	/**
	 * @author chengcheng
	 * @param input  输入的内容
	 * @param min  允许的最大长度
	 * @param max  允许的最小长度
	 * @param Chinese  是否支持中文
	 * @return ResultMessage
	 */
	public static ResultMessage checkInputValid(String input,int min,int max,boolean Chinese){
		if(input.length()>max){
			return ResultMessage.TOO_LONG;
		}else if(input.length()<min){
			return ResultMessage.TOO_SHORT;
		}
		char[] inputList=input.toCharArray();
		for(char i:inputList){
			if((i>='A'&&i<='Z')||(i>='a'&&i<='z')||(i>='0'&&i<='9')){
				continue;
			}else if(Chinese&&i>=19968&&i<=171941){
				continue;
			}else{
				return ResultMessage.UNVALID;
			}
		}
		return ResultMessage.SUCCESS;
	}


}
