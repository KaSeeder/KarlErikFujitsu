package ee.praktika.trial.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleEntityNotFoundException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>("Entity Not Found!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehicleIsForbiddenException.class)
    protected ResponseEntity<?> handleVehicleIsForbiddenException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>("Usage of selected vehicle type is forbidden", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCityNameException.class)
    protected ResponseEntity<?> InvalidCityNameException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>("The city input has to be either Tallinn, Tartu or Pärnu!", HttpStatus.NOT_FOUND);
    }
}
