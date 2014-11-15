/**
 * WriteOff逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import java.util.ArrayList;

import util.DocumentType;
import util.ResultMessage;
import vo.DocumentVO;

public class Writeoff {

	private ArrayList<CanWriteOff> bls;
	
	public Writeoff(){
		bls = new ArrayList<CanWriteOff>();
		bls.add(new MockPresent());
		bls.add(new MockOver());
		bls.add(new MockCash());
		bls.add(new MockPayment());
		bls.add(new MockPurchase());
		bls.add(new MockSale());
		bls.add(new MockLoss());
	}
	
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

}


