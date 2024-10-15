package com.example.librarymanagment.asp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeaurmentAspect {
	
	Logger log = LoggerFactory.getLogger(MeaurmentAspect.class);
	
	@Around(value = "execution(* om.example.librarymanagment.service..*(..))")
	public Object logTime(ProceedingJoinPoint  joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("KPI:");
		sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
				.append("\twithArgs: ");
		sb.append("\ttook: ");
		Object returnValue = joinPoint.proceed();
		log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());
		
		return returnValue;
	}

	@Pointcut("execution(* com.example.librarymanagment.service..*(..))")
	    public void bookOperations() {}
	
	 @Before("bookOperations()")
	    public void beforeBookOperation(JoinPoint joinPoint) {
	        System.out.println("Before " + joinPoint.getSignature().getName() + " method is called");
	    }

	@AfterThrowing(pointcut = "bookOperations()", throwing = "ex")
    public void afterThrowingBookOperation(Exception ex) {
        System.out.println("Exception thrown: " + ex.getMessage());
    }
	
	
}
