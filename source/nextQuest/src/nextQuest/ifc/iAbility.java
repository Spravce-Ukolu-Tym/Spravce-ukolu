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
public interface iAbility extends Remote
{

    String getDescription();

    String getName();
    
}
