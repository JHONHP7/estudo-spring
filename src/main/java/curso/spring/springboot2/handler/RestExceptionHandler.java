package curso.spring.springboot2.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import curso.spring.springboot2.exception.ExceptionDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import curso.spring.springboot2.exception.BadRequestException;
import curso.spring.springboot2.exception.BadRequestExceptionDetails;
import curso.spring.springboot2.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * todos os controladores irão usar essa classe
 */
@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value()).title("Bad request exception, Check the documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName()).build(),
                HttpStatus.BAD_REQUEST);
    }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
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

	@Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ExceptionDetails exceptionsDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(statusCode.value()).title(ex.getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName()).build();
        return new ResponseEntity<>(exceptionsDetails, headers, statusCode);
    }
}
