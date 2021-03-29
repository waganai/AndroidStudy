// UserChangeListenerInterface.aidl
package com.hubiao.ipcdemo;
import com.hubiao.ipcdemo.bean.User;

// Declare any non-default types here with import statements

interface UserChangeListener {
   void onUserChanged(in User user);
}