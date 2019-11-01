package com.wds.server.wdsserver.commons.exceptions;

import com.wds.server.wdsserver.commons.ResponseType;
import com.wds.server.wdsserver.commons.enums.EnumMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

// @ControllerAdvice는 모든 @Controller 즉, 전역에서 발생할 수 있는 예외를 잡아 처리
// @RestControllerAdvice는 @RestController에서 발생한 Exception만 캐치
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private final ResponseType responseType;

	public GlobalExceptionHandler(ResponseType responseType) {
		this.responseType = responseType;
	}

	/**
	 * 404 Error
	 * @param e
	 * @return
	 */
	// @Controller, @RestController가 적용된 Bean 내에서 (하나의 클래스) 발생하는 예외를 잡아서 하나의 메서드에서 처리
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoHandlerFoundException.class})
	public ResponseEntity<?> error404(Exception e) {
		return responseType.send(HttpStatus.NOT_FOUND, EnumMessage.HTTP_NOT_FOUND, null);
	}

	/**
	 * 401 Error
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<?> error401(UnAuthorizedException e) {
		return responseType.send(HttpStatus.UNAUTHORIZED, e.getEnumMessage(), null);
	}

	/**
	 * 400 Error + @Valid check TODO
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> error400(BadRequestException e) {
		return responseType.send(HttpStatus.OK, e.getEnumMessage(), null);
	}

	/**
	 * 400 Error
	 * @param e
	 * @return
	 */
	@ExceptionHandler({
			MissingServletRequestParameterException.class // RequestParam 의 parameter가 넘어오지않을때
	})
	public ResponseEntity<?> error400(Exception e) {
		log.error(e.getMessage());
		return responseType.send(HttpStatus.OK, EnumMessage.HTTP_BAD_REQUEST, e.getMessage());
	}

	/**
	 * 500 Error
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler({RuntimeException.class, Exception.class})
	public ResponseEntity<?> error500(Exception e) {
		log.error(e.toString());
		return responseType.send(HttpStatus.INTERNAL_SERVER_ERROR, EnumMessage.HTTP_SERVER_ERROR, null);
	}
}
