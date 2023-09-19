package curso.spring.springboot2.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import curso.spring.springboot2.exception.BadRequestException;
import curso.spring.springboot2.exception.BadRequestExceptionDetails;
import curso.spring.springboot2.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;

/**
 * todos os controladores irão usar essa classe
 */
@ControllerAdvice
@Log4j2
public class RestExceptionHandler {
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
		return new ResponseEntity<>(
				BadRequestExceptionDetails.builder().timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value()).title("Bad request exception, Check the documentation")
						.details(bre.getMessage()).developerMessage(bre.getClass().getName()).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		/** 
		 * PEGANDO AS PARTES COM ERROS
		 * E AS MENSSAGENS TAMBÉM
		 */
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		/**
		 * PEGANDO OS CAMPOS E RETORNANDO DENTRO DE UMA STRING, SE TIVER MASI DE UM VALOR ELE VAI SEPARAR POR VÍRGULA
		 */
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		/**
		 * PEGANDO AS MENSSAGENS
		 */
		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
						.timestamp(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value()).title("Bad request exception, Invalid Fields")
						.details("Check the field(s) error")
						.developerMessage(exception.getClass().getName())
						.fields(fields)
						.fieldsMessage(fieldsMessage)
						.build(),
				HttpStatus.BAD_REQUEST);
		 
	}
}
