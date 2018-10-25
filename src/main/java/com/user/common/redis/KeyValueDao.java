package com.user.common.redis;

import java.util.List;
import java.util.Set;

/**
 * @author xuwenqian
 */
public interface KeyValueDao {

    /**
     * set存数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * set存数据设置过期时间
     * @param key
     * @param value
     * @param expireTime
     */
    boolean set(String key, Object value, int expireTime);

    /**
     * getSe存数据
     * @param key
     * @param value
     */
    Object getSet(String key, Object value);

    /**
     * get获取数据
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 设置有效天数
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * 移除数据
     * @param key
     * @return
     */
    void remove(String key);

    /**
     * 批量删除对应的value
     * @param keys
     */
    void remove(String... keys);

    /**
     * 递增（此方法会先检查key是否存在，存在+increment，不存在先初始化，再+increment）
     * by 要增加几(大于0)
     * @param key
     * @param delta
     * @return
     */
    long incr(String key, long delta);


    /**
     * 递减
     * @param key
     * @param delta
     * @return
     */
    long decr(String key, long delta);
    /**
     * 获取keys
     * @param key
     * @return
     */
    Set<String> keys(Object key);

    /**
     * 批量删除key
     * @param pattern
     */
    void removePattern(String pattern);

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    void hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    Object hmGet(String key, Object hashKey);

    /**
     * 列表添加
     * @param k
     * @param v
     */
    void lPush(String k, Object v);

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    List<Object> lRange(String k, long l, long l1);

    /**
     * 集合添加
     * @param key
     * @param value
     */
    void addMembers(String key, Object value);

    /**
     * 集合获取
     * @param key
     * @return
     */
    Set<Object> setMembers(String key);

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    Set<Object> getByScore(String key, double scoure, double scoure1);


}
