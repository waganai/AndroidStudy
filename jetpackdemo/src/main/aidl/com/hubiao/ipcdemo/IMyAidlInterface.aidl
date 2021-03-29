// IMyAidlInterface.aidl
package com.hubiao.ipcdemo;
import com.hubiao.ipcdemo.bean.User;
import com.hubiao.ipcdemo.UserChangeListener;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    /**
    *
    *获取姓名
    */
    String getName(String nickName);

    /**
    *获取用户
    */
    User getUserById(int id);

    /**
    *注册监听
    */
    void registerListener(UserChangeListener listener);

    /**
    *取消监听
    */
    void unRegisterListener(UserChangeListener listener);
}