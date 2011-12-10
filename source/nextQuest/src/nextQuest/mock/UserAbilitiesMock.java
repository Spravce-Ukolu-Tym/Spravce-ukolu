package nextQuest.mock;

import nextQuest.server.Ability;


class UserAbilitiesMock {
    private int idUser;
    private Ability ability;

    public UserAbilitiesMock(int idUser, Ability ability) {
        this.idUser = idUser;
        this.ability = ability;
    }

    public int getIdUser() {
        return idUser;
    }

    public Ability getAbility() {
        return ability;
    }

    

}
