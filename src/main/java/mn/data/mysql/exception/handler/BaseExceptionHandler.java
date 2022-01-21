package mn.data.mysql.exception.handler;

import mn.data.mysql.exception.BaseException;
import io.micronaut.context.annotation.Parallel;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import mn.data.mysql.exception.BaseException;

@Parallel
@Singleton
@Requires(classes = {BaseException.class, ExceptionHandler.class})
public class BaseExceptionHandler implements ExceptionHandler<BaseException,HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, BaseException exception) {
        return HttpResponse.badRequest(exception.getMessage());
    }
}
