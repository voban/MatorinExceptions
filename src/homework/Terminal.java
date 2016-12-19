package homework;

import java.io.IOException;

/**
 * Created by Владимир on 18.12.2016.
 */
public interface Terminal {
    void checkMoney();
    void addMoney() throws Exception;
    void getMoney() throws Exception;
    void exit();
}
