package nextQuest.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nextQuest.ifc.*;

public class Project  implements Serializable, Comparable<Project> {
    private int idproject;

    
    private UserInfo Creator;
    private UserInfo Leader;
    private int Priority;
	
    private String Name;
    
    public Project(int idproject, String Name, UserInfo Creator, UserInfo Leader) throws RemoteException{
        this.idproject = idproject;
        this.Name = Name;
        this.Creator = Creator;
        this.Leader = Leader;
        this.Priority = Priority;
    }
    public int getID() {
        return idproject;
    }
    public String getName() {
        return Name;
    }

    public UserInfo getUserCreatedBy() {
        return Creator;
    }

    public UserInfo getLeader() {
        return Leader;
    }

    public int getPriority() {
        return Priority;
    }

    
    public int compareTo(Project o) {
        return o.Priority-Priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (this.idproject != other.idproject) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idproject;
        return hash;
    }
    
    static Project GetProjectByID(int idproject, Connection con) throws SQLException, nqException, RemoteException
    {
	PreparedStatement st = con.prepareStatement("SELECT `idProject`, `idUserCreatedBy`, `idLeader`, `Name` FROM Projects WHERE idProject = ?");
	ResultSet rs = st.executeQuery();
	if(rs.next())
	{
	    UserInfo cb = UserManager.getUserByID(rs.getInt("idUserCreatedBy"), con);
	    UserInfo ld = UserManager.getUserByID(rs.getInt("idLeader"), con);
	    
	    return new Project(rs.getInt("idProject"), rs.getString("Name"), cb, ld);
	}
	throw new nqException(nqExceptionType.DBSoftError, "Project not found");
    }
}

