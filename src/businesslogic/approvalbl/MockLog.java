/**
 * @author chengcheng
 * @date 2014/11/14
 */

package businesslogic.approvalbl;

import util.ResultMessage;
import businesslogic.logbl.Log;

public class MockLog extends Log{

	private String content;
	
	public MockLog(String content){
		this.content=content;
	}
	
	public ResultMessage add(){
		System.out.println(this.content);
		System.out.println("Add successfully!");
		return ResultMessage.SUCCESS;
	}
	
	
}
