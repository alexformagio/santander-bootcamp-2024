package formagio.santander.controller.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handle(IllegalArgumentException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handle(NoSuchElementException ex){
		return new ResponseEntity<>("Recurso não localizado", HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handle(Throwable ex){
		var message = "Erro inesperado no servidor";
		logger.error(message, ex);
		return new ResponseEntity<>("Erro inesperado ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
