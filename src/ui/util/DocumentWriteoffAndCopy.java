package ui.util;

import util.DocumentType;

public interface DocumentWriteoffAndCopy{
	
	abstract public boolean checkCompleted();
	
	abstract public DocumentType getDocumentType();
	
	abstract public String getDocumentID();
	
	abstract public void createCopyDocument();
	
}
