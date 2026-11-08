package DungeonsAndStudents;

public class AddWeapon {
    public static Weapon create() {
        String[] types = {"Меч", "Лук", "Арбалет", "Посох", "Алебарда", "Катана"};
        int result1 = (int) (Math.random() * 100);
        int result2 = (int) (Math.random() * 6);
        String rare = "";
        switch (result1){
            case 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39:{
                rare = "Обычное";
                break;
            }
            case 40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69:{
                rare = "Необычное";
                break;
            }
            case 70,71,72,73,74,75,76,77,78,79,80,81,82,83,84:{
                rare = "Редкое";
                break;
            }
            case 85,86,87,88,89,90,91,92,93,94:{
                rare = "Эпическое";
                break;
            }
            case 95,96,97,98,99:{
                rare = "Легендарное";
                break;
            }
        }
        String type = types[result2];
        double endurance = 0;
        double speedOfAttack = 0;
        double magicDamage = 0;
        double phisicDamage = 0;
        switch (result2){
            case 0:{
                endurance = 7.0;
                speedOfAttack = 0.3;
                magicDamage = 0;
                phisicDamage = 60;
                break;
            }
            case 1:{
                endurance = 3.5;
                speedOfAttack = 0.6;
                magicDamage = 0;
                phisicDamage = 45;
                break;
            }
            case 2:{
                endurance = 6.0;
                speedOfAttack = 0.2;
                magicDamage = 0;
                phisicDamage = 65;
                break;
            }
            case 3:{
                endurance = 2.0;
                speedOfAttack = 0.5;
                magicDamage = 40;
                phisicDamage = 10;
                break;
            }
            case 4:{
                endurance = 9.0;
                speedOfAttack = 0.2;
                magicDamage = 0;
                phisicDamage = 80;
                break;
            }
            case 5:{
                endurance = 4.0;
                speedOfAttack = 0.6;
                magicDamage = 0;
                phisicDamage = 50;
            }
        }
        switch (rare){
            case "Обычное":{

            }
            case "Необычное":{
                speedOfAttack += 0.1;
                if(result2 == 3) magicDamage *= 1.2;
                else phisicDamage *= 1.2;
                break;
            }
            case "Редкое":{
                speedOfAttack += 0.2;
                if(result2 == 3) magicDamage *= 1.5;
                else phisicDamage *= 1.5;
                break;
            }
            case "Эпическое":{
                speedOfAttack += 0.3;
                if(result2 == 3) magicDamage *= 2;
                else phisicDamage *= 2;
                break;
            }
            case "Легендарное":{
                speedOfAttack += 0.4;
                if(result2 == 3) magicDamage *= 2.5;
                else phisicDamage *= 2.5;
                break;
            }
        }
        return new CreateTheWeapon(rare,endurance,speedOfAttack,magicDamage,phisicDamage,type);
    }
}
