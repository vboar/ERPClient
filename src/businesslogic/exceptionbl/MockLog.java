/**
 * exception mocklog
 * @author Vboar
 * @date 2014/11/14
 */

package businesslogic.exceptionbl;

import util.ResultMessage;
import businesslogic.logbl.Log;

public class MockLog extends Log {

	String content;
	
	public MockLog(String content) {
		this.content = content;
	}
	
	public ResultMessage create() {
		System.out.println(content);
		System.out.println("Create Successfully");
		return ResultMessage.SUCCESS;
	}
	
}
