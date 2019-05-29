//EditText 编辑框

文本显示组件（TextView）的功能只是显示一些基础的文字信息，而如果用户要想定义可以输入的文本组件以达到很好的人机交互操作，则只能使用编辑框：EditText完成，此类的定义如下：
	java.lang.Object
	   android.view.View
	 	   android.widget.TextView
	 	 	   android.widget.EditText 


常见属性
     android:password="true"              // 以”.”形式显示文本
    
     android:phoneNumber=”true”  //EditText输入的文字为电话号码
     
	 android:maxLength=“50”    //EditText字数限制的设置
     
	 android:numeric=”integer”   //只接受数字，一共有三种分别为: integer（正整数）、signed（带符号整数，有正负）和decimal(浮点数)
     
	 android:hint=”默认文字” //设置默认显示的文字
     
	 android:textColorHint=”#FF0000″  //设置默认显示文字颜色
     
	 android:enabled=”false”  //设置输入框不能被编辑,true是可以被编辑
    
	 android:digits=”1234567890.+-*/%\n()”    //限制输入框中只能输入自己定义的这些字符串 如果输入其它将不予以显示
		   
			   
			   
