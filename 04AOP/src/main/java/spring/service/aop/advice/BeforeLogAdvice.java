package spring.service.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeLogAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("======================");
		System.out.println("[beforeLOG]"+getClass()+".before() start....");
		System.out.println("[beforeLOG] targetObject call Method :"+method);
		if(args.length!=0) {
			System.out.println("[beforeLOG] targetOnject method РќДо argument :"+args[0]);
		}
		System.out.println("[beforeLOG]"+getClass()+".before() end...");
	
	}
}
