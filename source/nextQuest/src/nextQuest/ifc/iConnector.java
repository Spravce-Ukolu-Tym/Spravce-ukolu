package nextQuest.ifc;

import java.rmi.*;
import java.rmi.server.*;
public interface iConnector extends Remote
{
    public iUser Login(String username, String pass);
}
