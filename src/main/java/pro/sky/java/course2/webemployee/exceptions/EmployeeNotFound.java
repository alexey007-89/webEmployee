package pro.sky.java.course2.webemployee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound() {
    }

    public EmployeeNotFound(String message) {
        super(message);
    }

    public EmployeeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFound(Throwable cause) {
        super(cause);
    }

    public EmployeeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
