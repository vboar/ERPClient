package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import businesslogicservice.writeoffblservice.WriteoffBLService;

public class WriteoffController implements WriteoffBLService {

	Writeoff w=new Writeoff();
	
	@Override
	public ResultMessage create(DocumentType type,String id) {
		return w.add(type, id);
	}
	
}
