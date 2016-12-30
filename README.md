将component,pas,util,vo包复制到项目中，
res目录下的相关文件复制到对应目录

在manifest文件中添加以下activity声明：
        <activity
            android:name="com.example.password.pas.PsMainActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
            android:windowSoftInputMode="adjustPan|stateHidden" />
        <activity
            android:name="com.example.password.pas.PasswordSetting"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
            android:windowSoftInputMode="adjustPan|stateHidden" />
        <activity
            android:name="com.example.password.pas.PasswordActivity"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
            android:windowSoftInputMode="adjustPan|stateHidden" />
        <activity
            android:name="com.example.password.pas.ForgotPassword"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
            android:windowSoftInputMode="adjustPan|stateHidden" />
            
            
 1. 开启应用手势解锁:
  
  若没有实现 registerActivityLifecycleCallbacks
  在项目的Application文件中的onCreate方法中添加 
  
  InitApp.init(this);    
  
  若已经实现了 registerActivityLifecycleCallbacks  方法则：
  在onActivityStarted回调中添加   InitApp.onActivityStarted
  在onActivityStopped回调中添加   InitApp.onActivityStopped
  
  
 2. 调用设置：
 
  PsMainActivity是手势密码的开启页面。
  
  若某个页面需要手势解锁：
  则在startactivity 时使用PasswordUtil.startActivity()
  
  
  
            