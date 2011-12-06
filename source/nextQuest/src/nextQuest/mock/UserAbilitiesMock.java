package nextQuest.mock;

import nextQuest.server.Ability;


class UserAbilitiesMock {
    private int idUser;
    private Ability ability;
    private int level;

    public UserAbilitiesMock(int idUser, Ability ability, int level) {
        this.idUser = idUser;
        this.ability = ability;
        this.level = level;
    }

}
