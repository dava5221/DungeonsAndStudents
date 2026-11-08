package DungeonsAndStudents;

public class AddArmor {
    public static Armor create(){
        String[] types = {"Шлем", "Кираса", "Поножи", "Ботинки"};
        int result1 = (int) (Math.random() * 100);
        int result2 = (int) (Math.random() * 4);
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
        double protection = 0;
        double regeneration = 0;
        switch (result2){
            case 0:{
                endurance = 4;
                protection = 5;
                break;
            }
            case 1:{
                protection = 20;
                endurance = 11;
                break;
            }
            case 2:{
                protection = 20;
                endurance = 8;
                break;
            }
            case 3:{
                protection = 5;
                endurance = 5;
                break;
            }
        }
        switch (rare){
            case "Обычное":{
            }
            case "Необычное":{
                protection *= 1.2;
                break;
            }
            case "Редкое":{
                protection*=1.5;
                break;
            }
            case "Эпическое":{
                protection *= 1.8;
                regeneration += 0.02;
                break;
            }
            case "Легендарное":{
                protection *= 2;
                regeneration += 0.035;
                break;
            }
        }
        return new CreateTheArmor(rare,type,protection,endurance,regeneration);
    }
}
