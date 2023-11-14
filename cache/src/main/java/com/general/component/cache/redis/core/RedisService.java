package com.general.component.cache.redis.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.general.component.cache.redis.config.RedisProperty;
import com.general.component.common.jackson.JacksonSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RedisService
 *
 * @author littlesnail
 * @create 2023−03-08 10:09 PM
 */
@Configuration
@ConditionalOnBean(RedisProperty.class)
public class RedisService {

    private JedisConnectionFactory jedisConnectionFactory;

    private StringRedisTemplate stringRedisTemplate;

    public RedisService(RedisProperty redisProperty) {
        this.jedisConnectionFactory = jedisConnectionFactory(redisProperty);
        this.stringRedisTemplate = redisTemplate(this.jedisConnectionFactory);
    }

    /**
     * 获取JedisConnectionFactory
     *
     * @param redisProperty
     * @return
     */
    private JedisConnectionFactory jedisConnectionFactory(RedisProperty redisProperty) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperty.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperty.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperty.getMinIdle());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperty.getHost());
        redisStandaloneConfiguration.setPort(redisProperty.getPort());
        redisStandaloneConfiguration.setPassword(redisProperty.getPassword());
        redisStandaloneConfiguration.setDatabase(redisProperty.getIndex());
        JedisClientConfiguration.JedisClientConfigurationBuilder configurationBuilder = JedisClientConfiguration.builder();
        JedisClientConfiguration jedisClientConfiguration = configurationBuilder.usePooling().poolConfig(jedisPoolConfig).build();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }


    /**
     * 获取redisTemplate
     */
    private StringRedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        //设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        // key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    /**
     * set可过期的key
     *
     * @param key   key
     * @param value 值
     * @param time  过期时间-秒级
     */
    public void setKeyExpire(String key, String value, long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 判断key 是否存在
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public boolean delete(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }


    /**
     * 根据key 获取值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取string 并转化成对应的obj
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObj(String key, Class<T> clazz) {
        return JacksonSupport.strToObj(stringRedisTemplate.opsForValue().get(key), clazz);
    }

    /**
     * 转化map
     *
     * @param key
     * @return
     * @throws Exception
     */
    public Map<String, Object> getMap(String key) throws Exception {
        return JacksonSupport.strToMap(stringRedisTemplate.opsForValue().get(key));
    }

}
