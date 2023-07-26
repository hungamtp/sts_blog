package hunnid.com.blog.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(hunnid.com.blog.cusAnnotation.Log)")
    public void logPointcut(){
    }

    @After("logPointcut()")
    public void afterLogAllMethodCallsAdvice(){
        System.out.println("In Aspect");
    }

    @Before("logPointcut()")
    public void beforeLogAllMethodCallsAdvice(){
        System.out.println("Before Aspect");
    }
    
    
    //https://www.baeldung.com/spring-aop-pointcut-tutorial
}