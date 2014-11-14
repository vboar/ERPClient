/**
 * payment MockLog类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.paymentbl;

import util.ResultMessage;
import businesslogic.logbl.Log;

public class MockLog extends Log{

	private String content;
	
	public MockLog(String content){
		this.content=content;
	}
	
	public ResultMessage add(){
		System.out.println(this.content);
		System.out.println("add successfully!");
		return ResultMessage.SUCCESS;
	}
	
	
}
