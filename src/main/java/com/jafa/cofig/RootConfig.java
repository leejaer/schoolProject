package com.jafa.cofig;

import java.io.IOException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jafa.domain.ApprovalVO;
import com.jafa.domain.AttachVO;
import com.jafa.domain.AttendVO;
import com.jafa.domain.Category;
import com.jafa.domain.ExpendDTO;
import com.jafa.domain.ExpendVO;
import com.jafa.domain.MemberVO;
import com.jafa.domain.PeopleVO;
import com.jafa.domain.Reply_boardVO;
import com.jafa.domain.SchoolVO;
import com.jafa.domain.TestVO;

@Configuration
@MapperScan("com.jafa.repository")
@PropertySource(value = "classpath:database/oracle.properties")
@EnableTransactionManagement
public class RootConfig {
	
	@Value("${db.driver}")
	private String driverClass;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(new PathMatchingResourcePatternResolver()
						.getResources("classpath:mappers/**/*Mapper.xml"));
		factory.setTypeAliases(SchoolVO.class,Category.class,AttachVO.class,Reply_boardVO.class,PeopleVO.class
				,ApprovalVO.class,ExpendDTO.class,ExpendVO.class,TestVO.class,AttendVO.class,MemberVO.class);
		return factory; 
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory().getObject());
	}
	
	// MessageSource 설정 
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =  
							new ReloadableResourceBundleMessageSource(); 
		messageSource.setBasenames("classpath:message/label","classpath:message/error");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	
	//valid 사용
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	// 파일업로드 설정 
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(-1);
		return multipartResolver;
	}
	
	//동작중 오류 발생 시 롤백 작용
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
