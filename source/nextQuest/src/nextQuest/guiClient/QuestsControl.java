package nextQuest.guiClient;

import nextQuest.server.Task;

public class QuestsControl {
    private static final QuestsControl instance = new QuestsControl();

    // Private constructor prevents instantiation from other classes
    private QuestsControl() { }

    public static QuestsControl getInstance() {
            return instance;
    }

    /**
     * odevzdání úkolu
     * @param task
     */
    public void returnTask(Task t) {
        t.returnTask();
    }

    /**
     * odmítnutí úkolu
     * @param task
     */
    public void reject(Task t, String reason) throws WrongInputException {
        if(reason == null) return;
        else if(reason.equals("")) throw new WrongInputException("Please input your reason");

        t.reject(reason);
    }
}
