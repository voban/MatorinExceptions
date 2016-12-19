package homework;

import exceptions.IllegalCommand;
import exceptions.IllegalMoneyCount;
import exceptions.NoAvailableMoneyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Владимир on 18.12.2016.
 */
public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final BufferedReader reader;
    private final ExceptionHandler handler;

    public TerminalImpl(String pinCode) {
        this.server = new TerminalServer(0);
        this.pinValidator = new PinValidator(pinCode);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.handler = new ExceptionHandler();
        pinValidator.validate(reader);
    }

    @Override
    public void checkMoney() {
        pinValidator.validate(reader);
        System.out.println("Ваш баланс - " + server.checkMoney() + " рублей.");
    }

    @Override
    public void addMoney() throws Exception {
        pinValidator.validate(reader);
        System.out.println("Введите сумму:");
        int money = Integer.parseInt(reader.readLine());
        if ((money % 100) != 0) throw new IllegalMoneyCount("Сумма должна быть кратна 100.");
        server.addMoney(money);
        System.out.println("Вы положили на счёт " + money + " рублей. Ваш баланс - " + server.checkMoney() + " рублей.");
    }

    @Override
    public void getMoney() throws Exception {
        pinValidator.validate(reader);
        System.out.println("Введите сумму:");
        int money = Integer.parseInt(reader.readLine());
        if ((money % 100) != 0) throw new IllegalMoneyCount("Сумма должна быть кратна 100.");
        int result = server.getMoney(money);
        if (result == -1) throw new NoAvailableMoneyException("Не хватает денег на счету!");
        System.out.println("Вы сняли со счёта " + money + " рублей. Ваш баланс - " + result + " рублей.");
    }

    @Override
    public void exit() {
        System.out.println("Выход! До свидания!");
    }

    public int printActions() {
        System.out.println();
        System.out.println("Выберите действие: \n" +
                "1. Узнать баланс.\n" +
                "2. Положить деньги на счёт. \n" +
                "3. Снять деньги со счета. \n" +
                "4. Выход");
        int code = -1;
        try {
            code = Integer.parseInt(reader.readLine());
            action(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public void action(int code) {
        try {
            if ((code < 1) || (code > 4))
                throw new IllegalCommand("Неправильный номер команды!");
            switch (code) {
                case 1: checkMoney();
                    break;
                case 2: addMoney();
                    break;
                case 3: getMoney();
                    break;
                case 4: exit();
            }
        } catch (Exception e) {
            handler.printException(e);
        }
    }
}
