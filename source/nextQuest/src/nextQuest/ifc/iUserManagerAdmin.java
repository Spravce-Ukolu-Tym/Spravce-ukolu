/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;

/**
 *
 * @author suk
 */
public interface iUserManagerAdmin extends Remote
{

    void createAbility();

    void createUser();

    void editUser();

    void removeAbility();

    void removeUser();
    
}
