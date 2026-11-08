package DungeonsAndStudents;

import java.util.Scanner;

public class Player implements ActionsWithPlayerOrMenu {
    Scanner sa = new Scanner(System.in);
    private String name;
    private Inventory inventory = new Inventory();
    private double health = 100;
    private double protection = 0;
    private double endurance = 0;
    private double regeneration = 0.03;
    private double survivalChance;

    public double getHealth() {
        return health;
    }
    public double getSurvivalChance(){
        return survivalChance;
    }

    public double getProtection() {
        protection = (inventory.getHelmet() != null ? inventory.getHelmet().getProtection(): 0) + (inventory.getChest() != null ? inventory.getChest().getProtection(): 0) +
                (inventory.getPants() != null ? inventory.getPants().getProtection(): 0) + (inventory.getBoots() != null ? inventory.getBoots().getProtection(): 0);
        return protection;
    }

    public Player(String name) {
        this.name = name;
    }
    public void Winner(int res){
        if (res == 1) {
            Weapon W2 = AddWeapon.create();
            takeWeapon(W2);
        } else {
            Armor A3 = AddArmor.create();
            switch (A3.getType()){
                case "Шлем":{
                    takeHelmet(A3);
                    break;
                }
                case "Кираса":{
                    takeChest(A3);
                    break;
                }
                case "Поножи":{
                    takePants(A3);
                    break;
                }
                case "Ботинки":{
                    takeBoots(A3);
                    break;
                }
            }
        }
        if (getHealth() < 100 && (100 >= getHealth() + getHealth()* ((inventory.getHelmet() != null ? inventory.getHelmet().getRegeneration():0) + (inventory.getChest() != null? inventory.getChest().getRegeneration():0) +
                (inventory.getPants() != null ? inventory.getPants().getRegeneration():0) + (inventory.getBoots() != null ? inventory.getBoots().getRegeneration():0) + regeneration))) regen();
        setSurvivalChance();
        System.out.println("Теперь ваш коэффициент выживаемости: " + getSurvivalChance());
        System.out.println("Выход из подземелья...");
    }
    public void Looser(){
        System.out.println("В результате схватки вы проиграли...");
        double damage = (1-getProtection()/100)*35;
        System.out.println("Вы получаете " + damage + " урона и уходите нисчем");
        addHealth(-damage);
        System.out.println("Выход из подземелья...");
    }



    public boolean tryToEscape() {
        String accident[] = {"По пути домой вас загрыз убил зверовоин. Ваш коэффициент выживаемости был слишком мал...", "Пробираясь сквозь чащю, вы были смертельно отравлены очень странной змеей. Ваш коэффициент выжимаемости был слишком мал...",
        "В вас попала молния. Неповезло...(Ваш коэффициент выживаемости был слишком мал...)","Вы были убиты мародёрами. Ваш коэффициент выживаемости был слишком мал...", "Переплывая море вы были окутаны зовом сирен...(Ваш коэффициент выживаемости был слишком мал...)"};
        int result = (int) (Math.random() * 5);
        if (survivalChance < 1) {
            System.out.println(accident[result]);
            return false;
        }
        else {
            System.out.println("=================Вы покорили этот загадочный мир!==================");
            System.out.println("====================Слава победителю!==============================");
            return true;
        }
    }

    public void setSurvivalChance(){
        double MediumAttack = Math.min(1.0,(inventory.getWeapon().getPhysicDamege()+inventory.getWeapon().getMagicDamage())/200);
        double MediumProtection = Math.min(1.0,((inventory.getHelmet() != null ? inventory.getHelmet().getProtection(): 0) + (inventory.getChest() != null ? inventory.getChest().getProtection(): 0) +
                (inventory.getPants() != null ? inventory.getPants().getProtection(): 0) + (inventory.getBoots() != null ? inventory.getBoots().getProtection(): 0))/100);
        double MediumEndurance = Math.min(1.0,((inventory.getHelmet()!=null?inventory.getHelmet().getEndurance():0) + (inventory.getChest()!=null?inventory.getChest().getEndurance():0) +
                (inventory.getPants()!= null?inventory.getPants().getEndurance():0) + (inventory.getBoots()!=null? inventory.getBoots().getEndurance():0) + (inventory.getWeapon()!=null ? inventory.getWeapon().getEndurance():0))/37);
        double raremultiply = 0;
        switch (inventory.getWeapon().getRare()){
            case "Обычное":{
                raremultiply = 0.9;
                break;
            }
            case "Необычное":{
                raremultiply = 1;
                break;
            }
            case "Редкое":{
                raremultiply = 1.1;
                break;
            }
            case "Эпическое":{
                raremultiply = 1.2;
                break;
            }
            case "Легендарное":{
                raremultiply = 1.3;
                break;
            }
        }
        double impactOfAttack = 0.6;
        double impactOfProtection = 0.8;
        double impactOfEndurance = 0.2;
        double mainstats = impactOfAttack*MediumAttack + impactOfProtection*MediumProtection + impactOfEndurance*MediumEndurance;
        survivalChance = mainstats * raremultiply;
    }

    public void addHealth(double health) {
        this.health += health;
    }
    public void regen(){
        this.health += health + health*((inventory.getHelmet() != null ? inventory.getHelmet().getRegeneration():0) + (inventory.getChest() != null? inventory.getChest().getRegeneration():0) +
                (inventory.getPants() != null ? inventory.getPants().getRegeneration():0) + (inventory.getBoots() != null ? inventory.getBoots().getRegeneration():0) + regeneration);
    }

    public void Info(){
        System.out.println("__________________Правила игры_________________");
        System.out.println("1. Чтобы выбраться в реальный мир, необходимо повысить ваш коэффициент выживаемости до целого значения.");
        System.out.println("2. Для повышения вашего коэффициента вам необходимо получать новые элементы оружия/брони более высокого качества.");
        System.out.println("3. В начале игры вам был выдан 'стартовый набор': 2 элемента брони и одно оружие.");
        System.out.println("4. Получить новые элементы снаряжения можно лишь спустившись в подземелье.");
        System.out.println("5. Как только ваш коэффициент поднялся на достаточный уровень вы сможете выйти, выбрав пункт номер 4 в игровом меню.");
        System.out.println("__________________Приятной вам игры!_________________");

    }
    public void showStats(){
        System.out.println("===============================================================\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣠⣤⠤\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⡤⠤⠴⠶⠒⣋⠉⡍⠉⡉⠀⠢⢄⣐\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣠⣴⣶⣾⣶⣶⡛⡛⠙⡇⠄⠩⡖⣄⠄⣶⣤⡘⠶⠀⢹⣀⣠⡶⠴⠟\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢄⣀⣠⣤⣤⡤⠴⠒⠲⠞⠹⡍⢩⣿⣿⣿⣿⣿⣿⣿⣿⣙⠀⠐⠤⣄⣩⣅⣨⠷⠾⠗⠚⠉⠉⠁\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠂⠒⠒⠀⡑⢿⣄⠃⢤⡁⠂⠠⠀⣿⣿⣃⠻⢿⠿⠿⢻⣿⣾⠶⠓⠚⠉⠉⠁\n" +
                "⠀⠀⠀⣠⠰⡄⢀⠇⠐⢮⣼⡆⢠⣶⡇⢸⣩⣿⢸⣅⣠⣄⣾⣿⣯⠘⠷⠴⠶⠇⢸⣯\n" +
                "⠀⣀⣰⢴⣿⣿⣿⡿⠛⠨⣿⡧⢼⣗⣠⡿⠿⠗⠚⠛⠉⣉⡵⣿⣿⡀⠀⠄⠀⢠⢿⡿⢀⡀\n" +
                "⠀⠀⠀⠀⢻⣉⣉⡛⢰⠀⠈⠉⠉⠉⠀⠀⠀⣀⣀⣤⣾⣿⡐⢿⣿⡳⣄⡠⢴⣁⢎⣰⣿⣿⣿⠶⣦⣄\n" +
                "⠀⠀⠀⠀⠀⢻⣍⡴⠈⠂⠀⠈⣧⢴⠐⣆⣾⣿⣿⣿⣿⣿⣿⡷⣟⣗⣈⣡⣭⠭⠞⢛⣡⣴⣶⢋⣴⠿⣓⣶⣄\n" +
                "⠀⠀⠀⠀⠀⠀⢅⠀⠀⠀⠀⠻⣿⡾⢳⣿⡮⣿⣿⣿⣿⣷⣬⣿⣿⣿⣿⣿⠷⢖⣊⣱⣶⣫⣴⣿⣷⣿⣿⠿⣿⡀\n" +
                "⠀⠀⠀⠀⠀⠀⠸⡀⠀⠀⠀⠰⡅⠀⠀⣹⢷⣾⣿⡟⢳⣾⣿⣿⣿⣟⣽⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣯⡤⢶⢿⣷⣄\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠣⡀⠀⠀⠀⠀⠀⠈⢁⣼⣿⣿⣦⣄⣾⠉⠻⣿⡻⢿⣿⣿⣿⣿⣿⣿⣿⡿⠙⣷⣾⣷⣥⠐⢾⣿⣿⣷⣄\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠙⠀⠀⢠⣀⡾⠶⣿⣿⣿⣿⣦⡉⠹⣦⡀⢹⠹⠚⠃⣭⠙⢻⣿⣿⣿⣷⣴⣻⣟⣿⣿⠄⠶⣿⣿⣿⣿⣾⣏⣛⣒⡦\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠒⠀⠁⠀⢀⣿⣿⣿⣿⠛⢻⣦⢹⠍⡀⠀⢡⠰⢦⣘⣻⣿⣿⣿⣿⣯⣤⡳⢯⡙⠿⠟⢿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣯⣓⣾⠷⡄⡈⢠⠀⣊⡿⠶⣿⣿⡿⣿⣿⣿⣿⣿⣷⡾⠿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣿⣿⢾⣿⣿⣾⡷⠐⠺⡇⠘⠺⢥⢾⡷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢧⣼⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣿⣿⣏⣿⣿⡟⡆⣦⡀⣸⠀⢀⣾⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠉⣿⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⢿⢟⣹⣟⣋⣬⠽⣷⠶⣾⣶⣾⡿⠿⣿⣿⣿⣿⣿⣿⣿⡿⠉⠀⣈⢪⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⢿⣧⣿⣯⡔⠂⡭⠰⢑⣿⠋⢳⡘⣻⣿⣿⣿⣿⣿⣿⢁⠆⠀⣼⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢽⣿⣿⣯⠀⢻⣿⢜⣉⠆⡀⣞⣴⠛⢛⣯⡥⢭⣿⣿⣿⣿⣇⡼⠀⡰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣇⣸⣿⣿⣽⡶⣿⣯⡙⡛⢋⣯⣥⠼⣶⣿⣿⠟⣽⠋⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⠻⡜⣿⣿⣿⣿⣿⣿⣿⣿⣏⣆⡴⠿⡾⢻⣿⡋⡀⠈⠑⢋⣾⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⠀⠡⡻⣯⣿⣿⣿⣿⣷⡾⢸⡏⠀⠀⢸⣿⣏⡼⢡⢄⡀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠡⡹⣿⣿⣿⣿⣿⣧⣿⠁⠀⠀⣾⣿⣿⣷⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "==================================================================================");
        double generalEndurance = (inventory.getHelmet() != null ? inventory.getHelmet().getEndurance(): 0) + (inventory.getChest() != null ? inventory.getChest().getEndurance(): 0) +
                (inventory.getPants() != null ? inventory.getPants().getEndurance(): 0) + (inventory.getBoots() != null ? inventory.getBoots().getEndurance(): 0) +
                (inventory.getWeapon() != null ? inventory.getWeapon().getEndurance():0);
        double generalProtection = (inventory.getHelmet() != null ? inventory.getHelmet().getProtection(): 0) + (inventory.getChest() != null ? inventory.getChest().getProtection(): 0) +
                (inventory.getPants() != null ? inventory.getPants().getProtection(): 0) + (inventory.getBoots() != null ? inventory.getBoots().getProtection(): 0);
        double generalRegeneration = (inventory.getHelmet() != null ? inventory.getHelmet().getRegeneration():0) + (inventory.getChest() != null? inventory.getChest().getRegeneration():0) +
                (inventory.getPants() != null ? inventory.getPants().getRegeneration():0) + (inventory.getBoots() != null ? inventory.getBoots().getRegeneration():0) + regeneration;
        System.out.println("Здоровье: " + health);
        System.out.println("Общая защита: " + generalProtection);
        System.out.println("Общий вес: " + generalEndurance);
        System.out.println("Регенерация (в %): " + (generalRegeneration*100) );
        System.out.println("~~~~~~~~~~~Коэффиент выживаемости~~~~~~~~~~~");
        System.out.println(survivalChance + "/1");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }
    public void showInventory() {
        System.out.println("====================Инвентарь====================");
        if (inventory.getHelmet() != null) {
            System.out.println("   ---Шлем--- ");
            System.out.println("Редкость: " + inventory.getHelmet().getRare());
            System.out.println("Защита (в %): " + inventory.getHelmet().getProtection());
            System.out.println("Вес: " + inventory.getHelmet().getEndurance());
        } else {
            System.out.println("У вас отсутствует шлем!");
        }

        if (inventory.getChest() != null) {
            System.out.println("   ---Кираса--- ");
            System.out.println("Редкость: " + inventory.getChest().getRare());
            System.out.println("Защита (в %): " + inventory.getChest().getProtection());
            System.out.println("Вес: " + inventory.getChest().getEndurance());
        } else {
            System.out.println("У вас отсутствует Кираса!");
        }
        if (inventory.getPants() != null) {
            System.out.println("   ---Поножи--- ");
            System.out.println("Редкость: " + inventory.getPants().getRare());
            System.out.println("Защита (в %): " + inventory.getPants().getProtection());
            System.out.println("Вес: " + inventory.getPants().getEndurance());
        } else {
            System.out.println("У вас отсутствуют поножи!");
        }
        if (inventory.getBoots() != null) {
            System.out.println("   ---Ботинки--- ");
            System.out.println("Редкость: " + inventory.getBoots().getRare());
            System.out.println("Защита (в %): " + inventory.getBoots().getProtection());
            System.out.println("Вес: " + inventory.getBoots().getEndurance());
        } else {
            System.out.println("У вас отсутствуют ботинки!");
        }
        if (inventory.getWeapon() != null) {
            System.out.println("   ---" + inventory.getWeapon().getTypeOfWeapon() + "---   ");
            System.out.println("Редкость: " + inventory.getWeapon().getRare());
            System.out.println("Скорость атаки (в атаках в секунду): " + inventory.getWeapon().getSpeedOfAttack());
            System.out.println("Магический урон: " + inventory.getWeapon().getMagicDamage());
            System.out.println("Физический урон: " + inventory.getWeapon().getPhysicDamege());
            System.out.println("Вес: " + inventory.getWeapon().getEndurance());
        } else {
            System.out.println("У вас отсутствует оружие!");
        }
        System.out.println("=================================================");

    }
    public void takeHelmet(Armor helmet) {
        if (inventory.getHelmet() == null) {
            inventory.setHelmet(helmet);
            System.out.println("Вы экипировали: " + helmet.getType() + ", " + helmet.getRare());
        } else {
            System.out.println("У вас уже экипирован шлем: " + inventory.getHelmet().getRare());
            System.out.println("Вам выпал предмет качества: " + helmet.getRare());
            System.out.println("Заменить?(y/n)");
            if (sa.nextLine().equalsIgnoreCase("y")) {
                inventory.setHelmet(helmet);
                System.out.println("Шлем заменен!");
            } else {
                System.out.println("Вы оставили свой шлем!");
            }
        }

    }

    public void takeChest(Armor chest) {
        if (inventory.getChest() == null) {
            inventory.setChest(chest);
            System.out.println("Вы экипировали: " + chest.getType() + ", " + chest.getRare());
        } else {
            System.out.println("У вас уже экипирована кираса: " + inventory.getChest().getRare());
            System.out.println("Вам выпал предмет качества: " + chest.getRare());
            System.out.println("Заменить?(y/n)");
            if (sa.nextLine().equalsIgnoreCase("y")) {
                inventory.setChest(chest);
                System.out.println("Кираса заменена!");
            } else {
                System.out.println("Вы оставили свою кирасу!");
            }
        }

    }
    public void takePants(Armor pants) {
        if (inventory.getPants() == null) {
            inventory.setPants(pants);
            System.out.println("Вы экипировали: " + pants.getType() + ", " + pants.getRare());
        } else {
            System.out.println("У вас уже экипированы поножи: " + inventory.getPants().getRare());
            System.out.println("Вам выпал предмет качества: " + pants.getRare());
            System.out.println("Заменить?(y/n)");
            if (sa.nextLine().equalsIgnoreCase("y")) {
                inventory.setPants(pants);
                System.out.println("Поножи заменены!");
            } else {
                System.out.println("Вы оставили свои поножи!");
            }
        }
    }
    public void takeBoots(Armor boots) {
        if (inventory.getBoots() == null) {
            inventory.setBoots(boots);
            System.out.println("Вы экипировали: " + boots.getType() + ", " + boots.getRare());
        } else {
            System.out.println("У вас уже экипированы ботинки: " + inventory.getBoots().getRare());
            System.out.println("Вам выпал предмет качества: " + boots.getRare());
            System.out.println("Заменить?(y/n)");
            if (sa.nextLine().equalsIgnoreCase("y")) {
                inventory.setBoots(boots);
                System.out.println("Ботинки заменены!");
            } else {
                System.out.println("Вы оставили свои ботинки!");
            }
        }
    }
    public void takeWeapon(Weapon weapon) {
        if (inventory.getWeapon() == null) {
            inventory.setWeapon(weapon);
            System.out.println("Вы экипировали: " + weapon.getTypeOfWeapon() + ", " + weapon.getRare());
        } else {
            System.out.println("У вас уже экипировано оружие: " + inventory.getWeapon().getRare());
            System.out.println("Вам выпал предмет качества: " + weapon.getRare());
            System.out.println("Заменить?(y/n)");
            if (sa.nextLine().equalsIgnoreCase("y")) {
                inventory.setWeapon(weapon);
                System.out.println("Оружие заменено!");
            } else {
                System.out.println("Вы оставили старое оружие!");
            }
        }
    }
}