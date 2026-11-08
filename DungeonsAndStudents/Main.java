package DungeonsAndStudents;

import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Привет! Вы пытаетесь попасть в удивительный мир, который полон опасностей и загадок.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Как я могу к вам обращаться?");
        String name = sc.nextLine();
        Player player = new Player(name);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + name+ ", вы осознаёте что выбраться из этого мира будет непросто?...~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Я осознаю все риски и готов(а) войти.");
        System.out.println("2. Мне страшно.. я не думаю что готов(а)");
        try {
            int choose = sc.nextInt();
            if (choose == 1){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~А вы не из простых ребят!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Мы начинаем...");Thread.sleep(1000);
                System.out.println("3...");Thread.sleep(1000);
                System.out.println("2...");Thread.sleep(1000);
                System.out.println("1...");Thread.sleep(1000);
                System.out.println("Не волнуйтесь, не отпущю же я вас с пустыми руками, вы получите 2 элемента брони и одно оружие.");
                Armor A1 = AddArmor.create();
                Armor A2 = AddArmor.create();
                Weapon W1 = AddWeapon.create();Thread.sleep(1000);
                switch (A1.getType()){
                    case "Шлем":{
                        player.takeHelmet(A1);
                        break;
                    }
                    case "Кираса":{
                        player.takeChest(A1);
                        break;
                    }
                    case "Поножи":{
                        player.takePants(A1);
                        break;
                    }
                    case "Ботинки":{
                        player.takeBoots(A1);
                        break;
                    }
                }Thread.sleep(1000);
                switch (A2.getType()){
                    case "Шлем":{
                        player.takeHelmet(A2);
                        break;
                    }
                    case "Кираса":{
                        player.takeChest(A2);
                        break;
                    }
                    case "Поножи":{
                        player.takePants(A2);
                        break;
                    }
                    case "Ботинки":{
                        player.takeBoots(A2);
                        break;
                    }
                }Thread.sleep(1000);
                player.takeWeapon(W1);
                while (true){
                    System.out.println("~~~~~~~~~~~~~~~~~~~~ИГРОВОЕ МЕНЮ~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("1. Отобразить инвентарь.");
                    System.out.println("2. Отобразить характеристики персонажа.");
                    System.out.println("3. Спуститься в подземелье.");
                    System.out.println("4. Попытаться сбежать в реальный мир. (ОЧЕНЬ ОПАСНО)");
                    System.out.println("*5*. Справка.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    int n = sc.nextInt();
                    switch (n){
                        case 1:{
                            player.showInventory();
                            Thread.sleep(10000);
                            break;
                        }
                        case 2:{
                            player.setSurvivalChance();
                            player.showStats();
                            Thread.sleep(10000);
                            break;
                        }
                        case 4:{
                            player.setSurvivalChance();
                            boolean result = player.tryToEscape();
                            if (!result) return;
                            else return;
                        }
                        case 3:{
                            Thread.sleep(1000);
                            System.out.println("---------------Спуск....-------------");Thread.sleep(1000);
                            System.out.println("...");Thread.sleep(1000);
                            System.out.println("...");
                            String enemies[] = {"Гоблин", "Голем", "Ведьма","Темный рыцарь","Варвар","Поборник","Убийца","Асассин из Темного Ордена","Жнец","Десница", "Тёмный принц"};
                            int result1 = (int) (Math.random()*12);
                            System.out.println("---------Ваш враг:---------------");
                            System.out.println(enemies[result1]);
                            System.out.println("Идет бой...");Thread.sleep(1000);
                            System.out.println("...");Thread.sleep(1000);
                            System.out.println("...");Thread.sleep(1000);
                            if (enemies[result1].equals("Гоблин")) {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                            }
                            if(enemies[result1].equals("Голем")) {
                                if (player.getSurvivalChance() < 0.5) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Ведьма")){
                                if(player.getSurvivalChance() < 0.4) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Темный рыцарь")){
                                if (player.getSurvivalChance() < 0.6) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Варвар")){
                                if (player.getSurvivalChance() < 0.3) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Поборник")){
                                if (player.getSurvivalChance() < 0.3) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Убийца")){
                                if (player.getSurvivalChance() < 0.6) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Асассин из Темного Ордена")){
                                if (player.getSurvivalChance() < 0.9) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Жнец")){
                                if (player.getSurvivalChance() < 0.75) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Десница")){
                                if (player.getSurvivalChance() < 1.1) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (enemies[result1].equals("Тёмный принц")){
                                if (player.getSurvivalChance() < 0.69) player.Looser();
                                else {
                                    System.out.println("В результате тяжелой схватки, вы одержали победу!");
                                    System.out.println("Какой вид награды вы хотите выбрать?");
                                    System.out.println("1. Оружие");
                                    System.out.println("2. Броня");
                                    int res = sc.nextInt();
                                    player.Winner(res);
                                }
                            }
                            if (player.getHealth() <= 0){
                                System.out.println("Ваши жизни упали до нуля, игра окончена");
                                return;
                            }
                            break;
                        }
                        case 5:{
                            player.Info();
                            break;
                        }
                    }
                }

            } else{
                System.out.println("Это ваш выбор...");
            }
        } catch (Exception ex){
            System.out.println("Произошла непревиденная ошибка! Мне очень жаль..");
        }
    }
}
