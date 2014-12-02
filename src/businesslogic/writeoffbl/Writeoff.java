/**
 * WriteOff逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;

import java.util.ArrayList;

public class Writeoff {

	private ArrayList<CanWriteOff> bls;
	
	public ResultMessage autoCreate(DocumentType type, String id) {
		for(int i=0; i<bls.size(); i++){
			if(bls.get(i).getType() == type){
				return bls.get(i).createWriteOffDocument(id);
			}
		}
		return ResultMessage.FAILED;
	}
	
	public ResultMessage manualCreate(DocumentType type, DocumentVO vo){
		for(int i=0; i<bls.size(); i++){
			if(bls.get(i).getType() == type){
				return bls.get(i).manualCreateDocument(vo);
			}
		}
		return ResultMessage.FAILED;	
	}
	
	public ResultMessage createLog(String content){	
		return null;
	}

}


