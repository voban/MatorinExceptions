package exceptions;

import homework.ExceptionThread;

/**
 * Created by Владимир on 18.12.2016.
 */
public class IllegalCommand extends Exception {
    public IllegalCommand(String message) {
        super(message);
    }
}
