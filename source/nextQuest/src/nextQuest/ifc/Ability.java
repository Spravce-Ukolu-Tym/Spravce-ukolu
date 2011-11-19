package nextQuest.ifc;

import java.io.Serializable;

public class Ability implements Serializable {
    private String Name;
    private String Description;
    private int Level;
    
    
    public Ability(String Name, String Description, int Level)
    {
        this.Name = Name;
        this.Description = Description;
	this.Level = Level;
    }
    public String getDescription() {
        return Description;
    }
    
    public String getName() {
        return Name;
    }
    public int getLevel() {
	return this.Level;
    }
}
