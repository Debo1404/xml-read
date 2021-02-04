package com.sample.xmlread.exception;

public class FileParsingException  extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1726988855748742401L;

	public FileParsingException (String message) {
        super(message);
    }
}
