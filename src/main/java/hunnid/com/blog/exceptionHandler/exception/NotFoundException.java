package hunnid.com.blog.exceptionHandler.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, String errorMessage) {
        super(new StringBuilder().append(entityName).append(errorMessage).toString());
    }
}
