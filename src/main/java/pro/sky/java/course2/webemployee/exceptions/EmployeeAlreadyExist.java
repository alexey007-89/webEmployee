package pro.sky.java.course2.webemployee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeAlreadyExist extends RuntimeException {
    public EmployeeAlreadyExist() {
    }

    public EmployeeAlreadyExist(String message) {
        super(message);
    }

    public EmployeeAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAlreadyExist(Throwable cause) {
        super(cause);
    }

    public EmployeeAlreadyExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
