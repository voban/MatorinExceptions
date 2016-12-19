package homework;

/**
 * Created by Владимир on 18.12.2016.
 */
public class TerminalServer {
    private int money;

    public TerminalServer(int money) {
        this.money = money;
    }

    public void addMoney(int m) {
        money = money + m;
    }

    public int getMoney(int m) {
        if ((money - m) < 0) return -1;
        money = money - m;
        return money;
    }

    public int checkMoney() {
        return money;
    }
}
