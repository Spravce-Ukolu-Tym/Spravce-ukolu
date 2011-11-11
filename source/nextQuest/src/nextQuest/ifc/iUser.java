package nextQuest.ifc;

import java.rmi.*;
import java.rmi.server.*;

public interface iUser extends Remote
{
    public int getNum() throws RemoteException;
}
