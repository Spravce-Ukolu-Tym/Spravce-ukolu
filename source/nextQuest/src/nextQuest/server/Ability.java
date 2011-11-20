package nextQuest.server;

import java.io.Serializable;

public class Ability implements Serializable {
    private String Name;
    private String Description;
    private int Level;
    private int idability;
    
    
    public Ability(String Name, String Description) //pouzitelne pri vytvareni u klienta bez navaznosti na ukol/uzivatele, napriklad pri vytvareni schopnosti do seznamu
    {
	this.idability = -1;
	this.Name = Name;
	this.Description = Description;
    }
    public Ability(String Name, String Description, int Level) //stejne jako predchozi, jen s navaznosti na uzivatele, napriklad pri nastavovani uzivatelskych schopnosti
    {
	this.idability = -1;
	this.Name = Name;
	this.Description = Description;
	this.Level = Level;
    }
    
    Ability(int idability, String Name, String Description) //tenhle konstruktor bude vyuzivat pouze server aby byl pak schopen zpetne identifikovat schopnost podle ID
    {
	this.idability = idability;
	this.Name = Name;
	this.Description = Description;
    }
    Ability(int idability, String Name, String Description, int Level) //tenhle konstruktor bude vyuzivat pouze server aby byl pak schopen zpetne identifikovat schopnost podle ID
    {
	this.idability = idability;
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
    int getID()
    {
	return this.idability;
    }
}
