public class Ability {
    private String Name;
    private String Description;

    public Ability(String Name, String Description) {
        this.Name = Name;
        this.Description = Description;
    }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }
}
