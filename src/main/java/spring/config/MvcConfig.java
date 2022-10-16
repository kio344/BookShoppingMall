package spring.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
@EnableWebMvc
@Import(DBConfig.class)
public class MvcConfig implements WebMvcConfigurer {

	@Value("${environment}")
	private String environment;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(thymeleafViewResolver());
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
		thymeleafViewResolver.setCharacterEncoding("utf-8");
		thymeleafViewResolver.setContentType("text/html");

		return thymeleafViewResolver;
	}

	@Bean
	public SpringTemplateEngine springTemplateEngine() {

		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

		springTemplateEngine.setTemplateResolver(springResourceTemplateResolver());

		springTemplateEngine.addDialect(new Java8TimeDialect());
		springTemplateEngine.addDialect(new LayoutDialect());

		return springTemplateEngine;

	}

	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {

		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

		boolean cacheable = false;

		if (environment.equals("dev")) {
			cacheable = false;
		} else if (environment.equals("prod")) {
			cacheable = true;
		}

		resolver.setSuffix(".html");
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setCacheable(cacheable);
		resolver.setApplicationContext(applicationContext);

		return resolver;
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter)).build();
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("message.testFolder.test", "message.user.user");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocations(new ClassPathResource("application.properties"));

		return configurer;
	}
}
