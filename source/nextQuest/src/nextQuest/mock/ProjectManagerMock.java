/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.mock;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import nextQuest.ifc.*;
import nextQuest.server.Project;
import nextQuest.server.UserInfo;

public class ProjectManagerMock extends UnicastRemoteObject implements iProjectManager
{

    private final iUser usr;
    private   HashSet<Project> hs = DatabaseMock.getProjects();
        
    public ProjectManagerMock(iUser usr) throws RemoteException{
        
	this.usr = usr;
    }


    @Override
    public void createProject(String Name, UserInfo Leader,int priority) throws nqException, RemoteException{
        UserInfo usrInf=null; 
        int id;
        if(usr.getLoginName().equals("root")){ //fixuje error
            id=1;
        }else{
            id=usr.getID();
        }
        if(!(usr==null)){
        usrInf = new UserInfo(id, usr.getName(), usr.getLoginName(), usr.getPermissionAdmin(), usr.getPermissionLeader(), usr.getPermissionPersonalist());
        }       
        hs.add(new Project(hs.size(), Name, usrInf, Leader, priority));
    }
    public void editProject(Project prj, String Name, UserInfo Leader,int priority){
        prj.setLeader(Leader);
        prj.setName(Name);
        prj.setPriority(priority);
    } 

    @Override
    public Project[] listProjects() throws RemoteException, nqException{
        Project [] tm = hs.toArray(new Project[0]);
        return (Project[]) tm; 
    }
}
