package ui.util;

import util.DocumentType;

public interface DocumentShowDialogExtra{
	
	public boolean checkCompleted();
	
	public DocumentType getDocumentType();
	
	public String getDocumentID();
	
	public void createCopyDocument();
	
}
