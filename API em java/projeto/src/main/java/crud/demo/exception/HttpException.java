package crud.demo.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class HttpException extends ResponseStatusException {
    public HttpException(HttpStatusCode status, String msg) {
        super(status, msg);
    }
}
