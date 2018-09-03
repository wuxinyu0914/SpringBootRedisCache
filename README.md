# SpringBootRedisCache
Springboot集成Redis缓存


**重点配置**

` application.properties配置文件中指定缓存方式`

 `spring.cache.type=redis`
 
 `RedisConfig启用注解@EnableCaching`
 
 `redis存储序列化方案`
 ```
     @Bean(name="redisTemplate")
        public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
            RedisTemplate<String, String> template = new RedisTemplate<>();
            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(om);
    
            template.setConnectionFactory(factory);
            //key序列化方式
            template.setKeySerializer(redisSerializer);
            //value序列化
            template.setValueSerializer(jackson2JsonRedisSerializer);
            //value hashmap序列化
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            return template;
        }
    
        @Bean
        public CacheManager cacheManager(RedisConnectionFactory factory) {
            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            // 配置序列化
            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
            RedisCacheConfiguration redisCacheConfiguration =
                     config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                           .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
    
            RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                    .cacheDefaults(redisCacheConfiguration)
                    .build();
            return cacheManager;
        }
  ```      