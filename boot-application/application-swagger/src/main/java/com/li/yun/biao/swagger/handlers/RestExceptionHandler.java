package com.li.yun.biao.swagger.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * @author chenmin
 * @since 2019/12/16
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * A single place to customize the response body of all Exception types.
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex      the exception
     * @param body    the body for the response
     * @param headers the headers for the response
     * @param status  the response status
     * @param request the current request
     */
    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        log.warn("Exception occurred:", ex);

        return new ResponseEntity<>(ex, headers, status);
    }

    /**
     * 参数校验异常
     */
    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(ex.getMessage());
        BusinessException exception = new BusinessException(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorMessage);
        return handleExceptionInternal(exception, null, null, OK, request);
    }

    private ResponseEntity<Object> processValidationResponse(Exception ex, String errorCode, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.OK;
        if (RegexUtils.isErrorCode(errorCode)) {
            return handleExceptionInternal(ExceptionFactory.create(errorCode), null, headers, status, request);
        }

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    @Override
    public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                      HttpStatus status, WebRequest request) {

        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        String errorCode = objectError.getDefaultMessage();
        return processValidationResponse(ex, errorCode, headers, request);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.OK, webRequest);
    }*/
}
