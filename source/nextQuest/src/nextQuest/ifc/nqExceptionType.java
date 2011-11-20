package nextQuest.ifc;
public enum nqExceptionType
{
    ServerError,  //chyba na serveru, vetsinou neco s cim z klienta nic udelat nepujde
    BadLogin,  //spatne prihlasovaci udaje
    DBError,  //"tvrda" chyba DB, nenulova pravdepodobnost ze se to u klienta da opravit
    DBSoftError, //"mekka" chyba DB, velka pravdepodobnost, ze je to chyba u klienta (ruzne spatne ID, (ne)existujici zaznamy a podobne)
    NoPermission, //nedostatek opravneni k provedeni cinnosti
    GeneralError, //To, na co se nevyplati vytvaret specialni typy a predevsim - to co neovlivni uzivatel
}
