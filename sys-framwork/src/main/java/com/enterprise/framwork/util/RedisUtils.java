//package com.enterprise.framwork.util;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.RedisConnectionFailureException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.Cursor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ScanOptions;
//
//public class RedisUtils<K, V> {
//    private static Logger log = LoggerFactory.getLogger(RedisUtils.class);
//
//    private RedisTemplate<K, V> redisTemplate;
//    private int retryDelayMilli;
//    private int retryCount;
//
//    public RedisUtils(RedisTemplate<K, V> redisTemplate,
//                      int retryDelayMilli,
//                      int retryCount) {
//        this.redisTemplate = redisTemplate;
//        this.retryDelayMilli = retryDelayMilli;
//        this.retryCount = retryCount;
//    }
//
//    public Boolean expire(K key, final long timeout, final TimeUnit unit) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.expire(key, timeout, unit);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long getExpire(K key, final TimeUnit timeUnit) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.getExpire(key, timeUnit);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Boolean hasKey(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.hasKey(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Set<K> getKeys(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.keys(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Boolean delete(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.delete(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long delete(Collection<K> keys) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.delete(keys);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public V get(Object key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForValue().get(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public void set(K key, V value) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    redisTemplate.opsForValue().set(key, value);
//                    return;
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public void set(K key, V value, long timeoutSeconds) {
//        set (key, value, timeoutSeconds, TimeUnit.SECONDS);
//    }
//    public void set(K key, V value, long timeout, TimeUnit unit) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    redisTemplate.opsForValue().set(key, value, timeout, unit);
//                    return;
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long increment(K key, long delta) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForValue().increment(key, delta);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Boolean setIfAbsent(K key, V value, long timeout, TimeUnit unit) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Object _hashGet(K key, Object hashKey) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForHash().get(key, hashKey);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Map<Object, Object> _hashEntries(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForHash().entries(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public void _hashPutAll(K key, Map<String, Object> map) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    redisTemplate.opsForHash().putAll(key, map);
//                    return;
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public void _hashPut(K key, Object hashKey, Object hashValue) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    redisTemplate.opsForHash().put(key, hashKey, hashValue);
//                    return;
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _hashDelete(K key, Object... hashKeys) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForHash().delete(key, hashKeys);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Boolean _hashHasKey(K key, Object hashKey) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForHash().hasKey(key, hashKey);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Double _hashIncrement(K key, Object hashKey, double delta) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForHash().increment(key, hashKey, delta);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Set<V> _setMembers(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForSet().members(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Boolean _setIsMember(K key, Object o) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForSet().isMember(key, o);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _setAdd(K key, V... values) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForSet().add(key, values);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _setSize(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForSet().size(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _setRemove(K key, Object... values) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForSet().remove(key, values);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public List<V> _listRange(K key, long start, long end) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForList().range(key, start, end);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _listSize(K key) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForList().size(key);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public V _listIndex(K key, long index) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForList().index(key, index);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _listRightPush(K key, V value) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForList().rightPush(key, value);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _listRightPushAll(K key, Collection<V> values) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForList().rightPushAll(key, values);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public void _listSet(K key, long index, V value) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    redisTemplate.opsForList().set(key, index, value);
//                    return;
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public Long _listRemove(K key, long count, Object value) {
//        int tryCount = 0;
//        try {
//            while (true) {
//                try {
//                    return redisTemplate.opsForList().remove(key, count, value);
//                }
//                catch (RedisConnectionFailureException exception) {
//                    log.info("caught connection exception, then retry it.");
//                    Thread.sleep(retryDelayMilli);
//                    tryCount += 1;
//                    if (tryCount == retryCount) {
//                        throw exception;
//                    }
//                }
//            }
//        }
//        catch (InterruptedException exception) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(exception);
//        }
//    }
//
//
//    public Set<String> scan(String pattern, long count) throws IOException {
//        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//        ScanOptions scanOptions = ScanOptions.scanOptions()
//                .count(count)
//                .match(pattern)
//                .build();
//        Cursor c = redisConnection.scan(scanOptions);
//        try {
//            Set<String> keys = new HashSet<>();
//            while (c.hasNext()) {
//                String key = (String) redisTemplate.getKeySerializer().deserialize((byte[]) c.next());
//                keys.add(key);
//            }
//            return keys;
//        } finally {
//            c.close();
//        }
//    }
//
//    public Set<K> keys(K pattern) {
//        return redisTemplate.keys(pattern);
//    }
//}
