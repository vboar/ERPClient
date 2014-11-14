/**
 * WriteOff逻辑类
 * @author JaneLDQ
 * @date 2014/11/14
 */
package businesslogic.writeoffbl;

import util.DocumentType;
import util.ResultMessage;

public class Writeoff {

	private MockOver mo;

	private MockCash mc;

	private MockPayment mpay;

	private MockSale ms;

	private MockPresent mpre;

	private MockPurchase mpur;
	
	public Writeoff(MockOver mo, MockCash mc, MockPayment mpay,
			MockPresent mpre, MockPurchase mpur, MockSale ms) {
		super();
		this.mo = mo;
		this.mc = mc;
		this.mpay = mpay;
		this.ms = ms;
		this.mpre = mpre;
		this.mpur = mpur;
	}

	public ResultMessage autoCreate(DocumentType type, String id) {
		switch (type) {
		case PAYMENT:
			return mpay.autoCreateWriteOffPayment(id);
		case PURCHASE:
			return mpur.autoCreateWriteOffPurchase(id);
		case SALE:
			return ms.autoCreateWriteOffSale(id);
		case CASH:
			return mc.autoCreateWriteOffCash(id);
		case PRESENT:
			return mpre.autoCreateWriteOffPresent(id);
		case OVERFLOW:
			return mo.autoCreateWriteOffOverflow(id);
		default:
			return ResultMessage.FAILED;
		}
	}

}


