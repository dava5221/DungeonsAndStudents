package DungeonsAndStudents;

public interface ActionsWithPlayerOrMenu {
    void Info();
    void Winner(int res);
    void Looser();
    void showInventory();
    void showStats();
    void takeHelmet(Armor helmet);
    void takeChest(Armor chest);
    void takePants(Armor pants);
    void takeBoots(Armor boots);
    void takeWeapon(Weapon weapon);
    boolean tryToEscape();
}
