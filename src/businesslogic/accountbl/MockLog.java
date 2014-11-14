/**
 * account MockLog
 * @author JaneLDQ
 * @date 2014/11/13
 */
package businesslogic.accountbl;

import util.ResultMessage;
import businesslogic.logbl.Log;

public class MockLog extends Log{

	private String content;
	
	public MockLog(String content){
		this.content=content;
	}
	
	public ResultMessage add(){
		System.out.println(this.content);
		System.out.println("approve successfully!");
		return ResultMessage.SUCCESS;
	}
	
	
}
