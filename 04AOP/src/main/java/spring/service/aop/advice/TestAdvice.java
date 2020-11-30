package spring.service.aop.advice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class TestAdvice implements MethodBeforeAdvice,
									AfterReturningAdvice,
									ThrowsAdvice,
									MethodInterceptor{

	//Target Object Method 호출 전 :: before() Method구현
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("[beforeLOG]"+getClass()+".before() start....");
		System.out.println("[beforeLOG] targetObject call Method :"+method);
		if(args.length!=0) {
			System.out.println("[beforeLOG] targetObject method 전달 argument :"+args[0]);
		}
		System.out.println("[beforeLOG]"+getClass()+".before() end...");	
	}
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("[afterLOG]"+getClass()+".afterReturning() start....");
		System.out.println("[afterLOG] targetObject call Method :"+method);
		System.out.println("[afterLOG] 타겟 객체의 호출 후 return value : "+returnValue);
		System.out.println("[afterLOG]"+getClass()+".afterreturning() end...");	

	}
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("[Around before]"+getClass()+".invoke() start....");
		System.out.println("[Around before] targetObject call Method :"+invocation.getMethod());
		if(invocation.getArguments().length!=0) {
			System.out.println("[Around before] targetObject method 전달 argument :"+invocation.getArguments()[0]);
		}
		//==>targetObject Method call
		Object obj = invocation.proceed();
		System.out.println("[Around before]타겟 객체의 호출 후 return value"+obj);
		System.out.println("[Around before]"+getClass()+".invoke() end...");
		
		return obj;
	}
	
	//==> Target Object Method 호출 Exception발생 : afterThrowing() Method구현
	public void afterThrowing(Throwable throwable) {
		System.out.println("[exception]"+getClass()+".afterThrowing() start....");
		System.out.println("[exception] Exception발생...");
		
		System.out.println("[exception] Exception Message : "+throwable.getMessage());
		System.out.println("[exception]"+getClass()+".afterThrowing() end...");
	}



}
