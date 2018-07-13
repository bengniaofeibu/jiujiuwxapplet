package com.applet.utils.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valOpsStr;

	@Resource(name = "redisTemplate")
	private ValueOperations<Object, Object> valOps;

	@Bean
	public RedisTemplate redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		StringRedisSerializer srs = new StringRedisSerializer();
		Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(
				Object.class);
		template.setKeySerializer(srs);
		template.setHashKeySerializer(srs);
		template.setValueSerializer(jsonRedisSerializer);
		template.setHashValueSerializer(jsonRedisSerializer);
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}


	/**
	 * 更新key的有效期
	 */

	public void stringRedisExpire(String key, Integer values) {
		if (values != 0) {
			stringRedisTemplate.expire(key, values, TimeUnit.SECONDS);
		}
	}

	/**
	 * 更新key的有效期
	 */
	public void redisExpire(String key, Integer values) {
		if (values != 0) {
			redisTemplate.expire(key, values, TimeUnit.SECONDS);
		}
	}

	public void setStringAndExpire(String key, String value, Integer seconds) {
		valOpsStr.set(key, value, seconds, TimeUnit.SECONDS);
	}

	public void redisTemplateSetAndExpire(String key, Object ob, Integer seconds) {
		valOps.set(key, ob, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 缓存字符串值
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            字符串value
	 */
	public void setStr(String key, String value) {
		valOpsStr.set(key, value);
	}

	/**
	 * 缓存Object类型值
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            对象value
	 */
	public void setObj(String key, Object value) {
		valOps.set(key, value);
	}

	public void setObjAndExpire(String key, Object value, Integer seconds) {

		valOps.set(key, value, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 *            key
	 * @return value
	 */
	public String getValuesStr(String key) {
		String value = (String) valOpsStr.get(key);
		return value;
	}

	public Object getValueByKeyAndDb(String key,Integer db,String mapkey){
		JedisConnectionFactory factory = (JedisConnectionFactory)stringRedisTemplate.getConnectionFactory();
		factory.setDatabase(db);
		stringRedisTemplate.setConnectionFactory(factory);
		return  stringRedisTemplate.opsForHash().get(key,mapkey);
	}

	/**
	 * 获取非字符串类型任务
	 * 
	 * @param key
	 *            key
	 * @return value
	 */
	public Object getValueObj(String key) {
		Object value = valOps.get(key);
		return value;
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 *            要删除的key
	 */
	public void deteleKey(String key) {
		stringRedisTemplate.delete(key);
	}

	/**
	 * 通过前缀批量删除String类型Key
	 * 
	 * @param preKey
	 *            删除的key前缀
	 */
	public void deteleKeyByPre(String preKey) {
		Set<String> set = stringRedisTemplate.keys(preKey + "*");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			stringRedisTemplate.delete(key);
		}
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 *            要删除的key
	 */
	public void deteleObjKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 通过前缀批量删除Object类型Key
	 * 
	 * @param preKey
	 *            删除的key前缀
	 */
	public void deteleObjKeyByPre(String preKey) {
		Set<Object> set = redisTemplate.keys(preKey + "*");
		Iterator<Object> it = set.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			redisTemplate.delete(key);
		}
	}

	/**
	 * 计数器+1
	 * 
	 * @param key
	 *            计数器key
	 * @return 返回计数器当前数值
	 */
	public long increment(String key) {
		return valOpsStr.increment(key, 1);
	}

	/**
	 * 添加list
	 */
	public void setObjList(String key, List< ? extends Object> list) {
		for (Object obj : list) {
			redisTemplate.opsForList().leftPush(key, obj);
		}
	}


	public void setMapList(String key,List<Map<String,Object>> list){

		for (Map<String,Object> obj : list) {
			redisTemplate.opsForList().leftPush(key, obj);
		}
	}

	public void addToList(String key, Object obj) {
		redisTemplate.opsForList().leftPush(key, obj);

	}

	/**
	 * 获取集合大小
	 * 
	 * @param key
	 *            list的key
	 * @return list大小
	 */
	public long getSize(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 获得整个集合
	 */
	public List<? extends Object> getObjList(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	public List<Object> getObjListForDb(String key,Integer db){
		JedisConnectionFactory jedisConnectionFactory =
				(JedisConnectionFactory)redisTemplate.getConnectionFactory();
		jedisConnectionFactory.setDatabase(db);
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * 根据索引获取集合数据
	 */
	public List<Object> getObjList(String key, int start, int end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 删除集合顶端元素
	 *
	 */
	public Object lPopList(String key) {
		return redisTemplate.opsForList().leftPop(key);

	}

	/**
	 * 删除集合底部元素
	 *
	 */
	public Object rPopList(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 在集合的顶部添加元素
	 * 
	 * @param key
	 *            集合的key
	 */
	public void lPushList(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 在集合的底部添加元素
	 * 
	 * @param key
	 *            集合的key
	 */
	public void rPushList(String key, Object value) {
		redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * 添加map对象元素
	 * 
	 * @param key
	 *            map的key
	 */
	public void putObject(String key, String mapKey, Object value) {
		redisTemplate.opsForHash().put(key, mapKey, value);
	}

	/**
	 * 获取map对象元素
	 * 
	 * @param key
	 *            map对应的key
	 * @param mapKey
	 *            map中的key
	 * @return map中的value
	 */
	public Object getObject(String key, String mapKey) {
		return redisTemplate.opsForHash().get(key, mapKey);
	}

	public Map<Object, Object> getEntities(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	
	
	/**
	 * @param listkey
	 * @param object
	 * @return
	 */
	public boolean listHasKey(String listkey,String object){
		return stringRedisTemplate.opsForList().range(listkey,0,-1).contains(object);
	}
	
	public long getListSize(String key){
		return stringRedisTemplate.opsForList().size(key);
	}
	
	/**
	 * 删除map对象元素
	 * 
	 * @param key
	 *            map对应的key
	 * @param mapKey
	 *            map中的key
	 */
	public void deleteObject(String key, String mapKey) {
		redisTemplate.opsForHash().delete(key, mapKey);
	}

	public void addToSet(String key, String value) {
		stringRedisTemplate.opsForSet().add(key, value);
	}

	
	public boolean isInSet(String key,String value){
	    return stringRedisTemplate.opsForSet().isMember(key,value);
	  }
	
	public void removeFromSet(String key, String value) {
		stringRedisTemplate.opsForSet().remove(key, value);
	}

	public Set<String> getSet(String key) {
		return stringRedisTemplate.opsForSet().members(key);
	}

	/**
	 * 根据前缀获取key（慎用，库中key多时效率低）
	 */
	public Set<String> getKeysByPre(String pre) {
		return stringRedisTemplate.keys(pre + "*");
	}

	public boolean ifExist(String key) {
		return stringRedisTemplate.hasKey(key);
	}
}
