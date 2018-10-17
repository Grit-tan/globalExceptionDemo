package com.grit.learning;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 作用于注解为controller的全局异常处理类
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Autowired
    private AppResultManager res;

    /**
     * 参数校验失败Handler, 使用javax.validation.constraints下的注解校验失败的处理, 比如 @NotNull
     *
     * @param request HttpServletRequest
     * @param exception catch ConstraintViolationException
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public String validationFailHandler(
            HttpServletRequest request, ConstraintViolationException exception) {
        StringBuffer sb = new StringBuffer();
        exception.getConstraintViolations().forEach(c -> sb.append(c.getMessage()).append(", "));
        return res.error(sb.toString());
    }

    /**
     * 参数校验失败Handler, 使用 org.springframework.util.Assert 类校验失败的处理
     *
     * @param request HttpServletRequest
     * @param exception catch ConstraintViolationException
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String assertFailHandler(HttpServletRequest request,
            IllegalArgumentException exception) {
        return res.error(exception.getMessage());
    }

    /**
     * 缺少必需参数Handler， 设配 @RequestParam(required=true) 的情况
     *
     * @param request HttpServletRequest
     * @param exception catch MissingServletRequestParameterException
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public String missingRequiredParamsErrorHandler(HttpServletRequest request,
            MissingServletRequestParameterException exception) {
        return res.error("缺少参数 " + exception.getParameterName());
    }

    /**
     * 参数转换失败错误Handler
     *
     * @param request HttpServletRequest
     * @param exception catch TypeMismatchException
     */
    @ExceptionHandler(value = TypeMismatchException.class)
    public String typeNoMatchErrorHandler(HttpServletRequest request,
            TypeMismatchException exception) {
        return res.error(exception.getPropertyName() + "必须是 " + exception.getRequiredType() + "类型");
    }

    /**
     * 所有异常报错 Handler, 兜底
     *
     * @param request HttpServletRequest
     * @param exception Exception
     * @throws Exception 错误
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        return res.error(exception.toString());
    }

}
