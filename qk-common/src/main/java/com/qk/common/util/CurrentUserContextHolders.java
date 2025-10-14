package com.qk.common.util;

/**
 * @Author: RightSquare
 * @Date: 2025/10/14 21:50
 * @Description:
 */
public class CurrentUserContextHolders {

    // 创建一个ThreadLocal对象 用来保存当前用户信息
    // 隐藏一个设计模式单例设计模式 饿汉式
    private static final ThreadLocal<Integer> USER_CONTEXT = new ThreadLocal<>();

    private CurrentUserContextHolders() {
    }

    // 存
    public static void set(Integer userId) {
        USER_CONTEXT.set(userId);
    }

    // 取
    public static Integer get() {
        return USER_CONTEXT.get();
    }

    // 移除
    public static void remove() {
        USER_CONTEXT.remove();
    }
}
