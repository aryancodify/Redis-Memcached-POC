package config;

import static java.util.Arrays.asList;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;

@Configuration
public class MemcachedConfig {

	
	
	/*
	 * creating cache named EmployeeMem in Memcached
	 * 
	 * */
	
	@Bean
	public CacheManager createCartCacheMgr() throws Exception {
		return createCacheManager("EmployeeMem", 3500, "localhost:11211");
	}
	
	
	
	
	private static CacheManager createCacheManager(String cacheName, int expiry, String url) throws Exception {
		final CacheFactory cf = new CacheFactory();
		cf.setCacheName(cacheName);
		cf.setCacheClientFactory(new MemcacheClientFactoryImpl());
		cf.setAddressProvider(new DefaultAddressProvider(url));
		CacheConfiguration cc = new CacheConfiguration();
		cc.setConsistentHashing(true);
		cf.setConfiguration(cc);

		final SSMCacheManager ssm = new SSMCacheManager();
		ssm.setCaches(asList(new SSMCache(cf.getObject(), expiry, true)));
		return ssm;
	}
}
