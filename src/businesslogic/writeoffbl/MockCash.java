package businesslogic.writeoffbl;

import util.ResultMessage;

public class MockCash {

	public MockCash(){
		
	}
	
	public ResultMessage autoCreateWriteOffCash(String id) {
		return ResultMessage.SUCCESS;
	}

}
