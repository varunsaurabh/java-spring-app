package RestAPI.APIDemo.confic;
import RestAPI.APIDemo.model.Product;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

//@Configuration
//@EnableCaching
public class EhcacheConfig{

    @Bean
    public CacheManager cacheManager2(){
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();

        CacheConfiguration<Long, Product> configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, Product.class, ResourcePoolsBuilder.heap(100)
                .offheap(10, MemoryUnit.MB))
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMinutes(10))).build();

        javax.cache.configuration.Configuration<Long, Product> cacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(configuration);

        cacheManager.createCache("product_name", cacheConfiguration);

        return cacheManager;
    }


}



















