��component,pas,util,vo�����Ƶ���Ŀ�У�
resĿ¼�µ�����ļ����Ƶ���ӦĿ¼

��manifest�ļ����������activity������
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
            
            
 1. ����Ӧ�����ƽ���:
  
  ��û��ʵ�� registerActivityLifecycleCallbacks
  ����Ŀ��Application�ļ��е�onCreate��������� 
  
  InitApp.init(this);    
  
  ���Ѿ�ʵ���� registerActivityLifecycleCallbacks  ������
  ��onActivityStarted�ص������   InitApp.onActivityStarted
  ��onActivityStopped�ص������   InitApp.onActivityStopped
  
  
 2. �������ã�
 
  PsMainActivity����������Ŀ���ҳ�档
  
  ��ĳ��ҳ����Ҫ���ƽ�����
  ����startactivity ʱʹ��PasswordUtil.startActivity()
  
  
  
            