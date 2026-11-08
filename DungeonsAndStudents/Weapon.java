package DungeonsAndStudents;

public abstract class Weapon {
    String rare;
    double endurance;
    double speedOfAttack;//значение в "атаках в секунду"
    double magicDamage;
    double physicDamage;
    String typeOfWeapon;
    protected double getSpeedOfAttack(){return speedOfAttack;}
    protected double getEndurance(){return endurance;}
    protected String getRare(){return rare;}
    protected double getMagicDamage(){return magicDamage;}
    protected String getTypeOfWeapon(){return typeOfWeapon;}
    protected double getPhysicDamege(){return physicDamage;}
}
