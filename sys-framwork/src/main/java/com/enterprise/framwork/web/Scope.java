package com.enterprise.framwork.web;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author chenyi
 */
public class Scope {
    // 静态变量，维护不同线程的上下文Scope
    private static final ThreadLocal<Scope> SCOPE_THREAD_LOCAL = new ThreadLocal<>();

    // 实例变量，维护每个上下文中所有的状态数据，为了区分不同的状态数据，使用ScopeKey类型的实例作为key
    private final ConcurrentMap<ScopeKey<?>, Object> values = new ConcurrentHashMap<>();

    // 获取当前上下文
    public static Scope getCurrentScope() {
        return SCOPE_THREAD_LOCAL.get();
    }

    // 在当前上下文设置一个状态数据
    public <T> void set(ScopeKey<T> key, T value) {
        if (value != null) {
            values.put(key, value);
        } else {
            values.remove(key);
        }
    }

    // 在当前上下文读取一个状态数据
    public <T> T get(ScopeKey<T> key) {
        T value = (T) values.get(key);
        if (value == null && key.initializer() != null) {
            value = key.initializer().get();
        }
        return value;
    }

    // 开启一个上下文
    public static Scope beginScope() {
        Scope scope = SCOPE_THREAD_LOCAL.get();
        if (scope != null) {
            throw new IllegalStateException("start a scope in an exist scope.");
        }
        scope = new Scope();
        SCOPE_THREAD_LOCAL.set(scope);
        return scope;
    }

    // 关闭当前上下文
    public static void endScope() {
        SCOPE_THREAD_LOCAL.remove();
    }

}
