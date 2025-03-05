package com.mrbean.common.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mrbean.common.exception.LoginRequiredException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginCheckAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoginCheckAspect.class);

    @Pointcut("execution(* com.mrbean.billofmaterials.controller.BillOfMaterialsRestController..*(..)) " +
            "|| execution(* com.mrbean.finishedproductsoutgoing..*(..)) " +
            "|| execution(* com.mrbean.rawmaterials.RawMaterialsController..*(..)) " +
            "|| execution(* com.mrbean.products.ProductsController..*(..)) " +
            "|| execution(* com.mrbean.warehouse.WarehouseController..*(..)) " +
            "|| execution(* com.mrbean.controller..*(..)) " +
            "&& !@annotation(com.mrbean.common.annotation.NoLoginCheck)")
    public void loginCheckPointcut() {}

    @Before("loginCheckPointcut()")
    public void checkLogin(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getHttpServletRequest();
        HttpServletResponse response = getHttpServletResponse();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            if (!response.isCommitted()) {
                throw new LoginRequiredException();
            } else {
                logger.error("Response already committed, cannot redirect.");
            }
        } else {
            logger.info("User is logged at URL: {}", request.getRequestURI());
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest();
        }
        throw new IllegalArgumentException("HttpServletRequest not found");
    }

    private HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getResponse();
        }
        throw new IllegalArgumentException("HttpServletResponse not found");
    }
}