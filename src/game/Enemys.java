package game;
public class Enemys
{
    String tier;
    int hp, attack, points, reward;
    public Enemys(String tier, int hp, int attack, int points, int reward)
    {
        this.tier = tier;
        this.hp = hp;
        this.attack = attack;
        this.points = points;
        this.reward = reward;
    }
    public String getTier()
    {
        return tier;
    }
    public int getHp()
    {
        return hp;
    }
    public int getAttack()
    {
        return attack;
    }
    public int getPoints()
    {
        return points;
    }
    public int getReward()
    {
        return reward;
    }
}