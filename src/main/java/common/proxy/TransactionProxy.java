package common.proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class TransactionProxy {

	@Autowired
	private EntityManager em;

	@Around("execution(public * models..*Dao.*(..))")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("트렌잭션 실행");
		Object result = null;
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			result = joinPoint.proceed();
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();

		}

		return result;

	}

}
