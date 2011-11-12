/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nextQuest.ifc;

import java.rmi.Remote;
import nextQuest.server.Ability;
import nextQuest.server.User;

/**
 *
 * @author suk
 */
public interface iUserManager extends Remote
{

    User[] findUsersByAbilities();

    Ability[] listAblities();
    
}
