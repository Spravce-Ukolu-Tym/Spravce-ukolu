package nextQuest.ifc;

import java.io.Serializable;

public class nqException extends Exception implements Serializable
{
    nqExceptionType type;
    public nqException(nqExceptionType type, String message)
    {
	super(message);
	this.type = type;
    }
    public nqExceptionType getType()
    {
	return this.type;
    }    
}
