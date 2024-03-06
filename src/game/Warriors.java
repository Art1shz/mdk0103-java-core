package game;

public class Warriors
{
    String type;
    int hp, attack, cost;
    public Warriors(String type, int hp, int attack, int cost)
    {
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.cost = cost;
    }
    public String getType()
    {
        return type;
    }
    public int getHp()
    {
        return hp;
    }
    public int getAttack()
    {
        return attack;
    }
    public int getCost()
    {
        return cost;
    }
}