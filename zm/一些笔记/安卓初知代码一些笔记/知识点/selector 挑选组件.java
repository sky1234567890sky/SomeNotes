//selector 挑选组件

Android中的selector是用来改变ListView、Button等控件在不同状态下的背景.

常用属性： 
	//按下状态下使用。
  android:state_pressed=”true/false” 
       true:表示按下状态下使用，false:表示非按下状态下使用。
    
	//聚焦状态使用
  android:state_focused=”true/false” 
       ture:表示聚焦状态使用，false:表示非聚集状态下使用。
	
	//选中状态下使用
  android:state_selected=”true/false” 
       true:表示被选中状态下使用，false:表示非选中下使用
	
	//勾选状态时使用
  android:state_active=”true/false” 
       true:表示可勾选状态时使用，false:表示不可勾选状态下使用

  android:state_checkable=”true/false” 
       true:表示勾选状态下使用，false:表示非勾选状态使用
  
    //可用状态下使用（能接收触摸/点击事件）
  android:state_enabled=”true/false” 
       true:表示可用状态使用（能接收触摸/点击事件），false:表示不可用状态使用
  
	//应用程序窗口有焦点时使用
  android:state_window_focused=”true/false” 
       true:表示应用程序窗口有焦点时使用（应用程序在前台），false:表示无焦点时使用


-- 如何使用
	   在res文件夹下创建一个 drawable 文件夹
	   再在以上文件夹下创建一个 xml 文件(引用selector)
	   
   定义好 selector.xml，引用方式  
          android:background="@drawable/btnselector"  

	   
	   
	   

