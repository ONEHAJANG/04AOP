package spring.service.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

@Aspect
public class TestAspectJ01 {
	
	
	//constructor
	public TestAspectJ01() {
		System.out.println(":: TestAspectJ01 Default constructor");
	}	
		@Pointcut("execution(**.getMessge(..))")
		public void work() {
			System.out.println("work() point call.....");
		}
	
	//Target Object Method ȣ�� �� :: before() Method����
	@Before("work()")//or @Before(Value="work()")
	public void before(JoinPoint joinPoint) {
		System.out.println("[beforeLOG]"+getClass()+".before() start....");
		System.out.println("[beforeLOG] targetObject:"+ joinPoint.getTarget().getClass().getName());
		System.out.println("[beforeLOG] targetObject:"+ joinPoint.getSignature().getName());
		
		if(joinPoint.getArgs().length !=0) {
			System.out.println("[beforeLOG] targetObject call"+"method ���� argument :"+joinPoint.getArgs()[0]);
		}
		System.out.println("[beforeLOG]"+getClass()+".before() end...");	
	}
	
	@AfterReturning(pointcut="work()", returning="returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue) {
		System.out.println("[afterLOG]"+getClass()+".afterReturning() start....");
		System.out.println("[afterLOG] targetObject call Method :"+joinPoint.getTarget().getClass().getName());
		System.out.println("[afterLOG] Ÿ�� ��ü�� ȣ�� �� return value : "+joinPoint.getSignature().getName());
		System.out.println("[afterLOG] Ÿ�� ��ü�� ȣ�� �� return value : " + returnValue);
		System.out.println("[afterLOG]"+getClass()+".afterreturning() end...");	

	}
	@Around("work()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[Around before]"+getClass()+".invoke() start....");
		System.out.println("[Around before] targetObject call Method :"+joinPoint.getTarget().getClass().getName());
		System.out.println("[Around before] targetObject call Method :"+joinPoint.getSignature().getName());
		if(joinPoint.getArgs().length !=0) {
			System.out.println("[Around before] targetObject method ���� argument :"+joinPoint.getArgs()[0]);
		}
		//==>targetObject Method call
		Object obj = joinPoint.proceed();
		
		System.out.println("[Around before]Ÿ�� ��ü�� ȣ�� �� return value"+obj);
		System.out.println("[Around before]"+getClass()+".invoke() end...");
		
		return obj;
	}
	
	@AfterThrowing(pointcut="work()", throwing="throwable")
	//==> Target Object Method ȣ�� Exception�߻� : afterThrowing() Method����
	public void afterThrowing(JoinPoint joinPoint,Throwable throwable) {
		System.out.println("[exception]"+getClass()+".afterThrowing() start....");
		System.out.println("[afterLOG] targetObject call Method :"+joinPoint.getTarget().getClass().getName());
		System.out.println("[afterLOG] Ÿ�� ��ü�� ȣ�� �� return value : "+joinPoint.getSignature().getName());
		System.out.println("[exception] Exception�߻�...");
		
		System.out.println("[exception] Exception Message : "+throwable.getMessage());
		System.out.println("[exception]"+getClass()+".afterThrowing() end...");
	}
}
