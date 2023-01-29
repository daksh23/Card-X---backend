package com.cardx.Cardx.Config;

import com.cardx.Cardx.Model.Response.QuoteApiResponse;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.EhcacheManager;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class cachingConfig {

    @Bean
    public CacheManager EhcacheManager() {

        CacheConfiguration<String, QuoteApiResponse> cachecConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class,
                        QuoteApiResponse.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10000)))
                .build();


        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        javax.cache.configuration.Configuration<String, QuoteApiResponse> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cachecConfig);

        cacheManager.createCache("quoteCache", configuration);

        return cacheManager;
    }
}
