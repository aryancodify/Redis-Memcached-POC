package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@EnableCaching
@SpringBootApplication
@ComponentScan({"controller","repository","entity","config"})
public class RedisMemcachedDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisMemcachedDemoApplication.class, args);
	}
}
