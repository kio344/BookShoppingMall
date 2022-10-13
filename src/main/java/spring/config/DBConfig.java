package spring.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import common.proxy.TransactionProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DBConfig {
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookShoppingMall");

		return emf;

	}

	@Bean
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	@Bean
	public TransactionProxy transactionProxy() {
		return new TransactionProxy();
	}
}
