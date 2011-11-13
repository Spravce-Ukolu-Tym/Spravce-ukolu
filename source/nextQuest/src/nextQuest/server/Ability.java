package nextQuest.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nextQuest.ifc.iAbility;

public class Ability  extends UnicastRemoteObject implements iAbility {
    private String Name;
    private String Description;

    public Ability(String Name, String Description)throws RemoteException
    {
        this.Name = Name;
        this.Description = Description;
    }

    @Override
    public String getDescription() {
        return Description;
    }

    @Override
    public String getName() {
        return Name;
    }
}
