res 文件夹下 values文件夹里的资源

资源：
		所有在res文件夹下的都是资源
		可以被编译到R.java中的
			res/raw中的文件会被映射到R.java文件中，访问的时候直接使用资源ID即R.id.filename
		不可以被编译到R.java中的
			assets文件夹下的文件不会被映射到R.java中，访问的时候需要AssestManager类
		放在Res目录下的资源都可以在XML中被引用，或者在java代码中被引用
		
		
1. styles.xml 样式文件
		
	可以这样设计：
		<resources>
		
		<style name = "text">
			
			<item name = "android:textColor">#cccccc</item>
			<item name="android:textSize">50sp</item>
			
		</style>
			
		</resources>
	
	在布局文件中引用：
		
		style = "@style/text"
		
		
2. strings.xml 字符串文件

	设计方式：
		<resources>
		
		//name = "" 是被引用的   按钮是显示的
			<string name = "anniu">按钮</string>
		
		</resources>
	
	再布局中引用：

		android:text="@string/anniu"

		
3. include 组件

	这个组件用来调用其它的.xml布局文件

	如何引用：
	
	//title 为调用的布局的文件名 title.xml
		<include 
			layout="@layout/title"
		/>
	
	