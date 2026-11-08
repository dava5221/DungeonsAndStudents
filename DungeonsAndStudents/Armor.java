package DungeonsAndStudents;

public abstract class Armor {
    protected double regeneration;
    protected double protection;
    protected String rare;
    protected String type;
    protected double endurance;
    public  double getRegeneration(){ return regeneration;}
    public  double getProtection(){return protection;}
    public String getRare(){return rare;}
    public String getType(){return type;}
    public double getEndurance(){return endurance;}
}
