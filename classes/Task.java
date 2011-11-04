import java.util.Date;

public class Task {
    private Date CreaitonDate;
    private String Description;
    private Integer MaxHours;
    private User Creator;
    private User AssignedTo;
    private Boolean isSubtask;
    private Task[] Subtasks;
    private Integer Percentage;
    private Ability[] NecessaryAbilities;

    public Task(Date CreaitonDate, String Description, Integer MaxHours, User Creator, User AssignedTo, Boolean isSubtask, Task[] Subtasks, Integer Percentage, Ability[] NecessaryAbilities) {
        this.CreaitonDate = CreaitonDate;
        this.Description = Description;
        this.MaxHours = MaxHours;
        this.Creator = Creator;
        this.AssignedTo = AssignedTo;
        this.isSubtask = isSubtask;
        this.Subtasks = Subtasks;
        this.Percentage = Percentage;
        this.NecessaryAbilities = NecessaryAbilities;
    }

    public User getAssignedTo() {
        return AssignedTo;
    }

    public Date getCreaitonDate() {
        return CreaitonDate;
    }

    public User getCreator() {
        return Creator;
    }

    public String getDescription() {
        return Description;
    }

    public Integer getMaxHours() {
        return MaxHours;
    }

    public Ability[] getNecessaryAbilities() {
        return NecessaryAbilities;
    }

    public Integer getPercentage() {
        return Percentage;
    }

    public Task[] getSubtasks() {
        return Subtasks;
    }

    public Boolean getIsSubtask() {
        return isSubtask;
    }

    public void accept() {
    }

    public void reject(String Reason) {
    }

    public void returnTask() {
    }
}

