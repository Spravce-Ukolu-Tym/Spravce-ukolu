package nextQuest.guiClient;

public class WrongInputException extends Exception {
    private String description;

    WrongInputException (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "WrongInputException{" + "description=" + description + '}';
    }
}
