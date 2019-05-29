资源文件


资源类型			所在目录			文件名										适合的关键xml元素

字符串  	 		/res/values/		strings.xml									<string>

字符串数组			/res/values/		arrays.xml									<string-array>

颜色值				/res/values/		colors.xml									<color>

尺寸				/res/values/		dimens.xml									<dimen>

简单Drawable图形	/res/values/		drawables.xml								<drawable>

位图图像			/res/drawable/		例如：img.png、img.jpg等					支持的图形文件或xml文件定义的drawable图形

动画序列(补间)		/res/anim/			如：fancy_animl.xml							<set>、<alpha>、<scale>、<translate>、<rotate>

菜单文件			/res/menu/			如：my_menul.xml							<menu>

XML文件				/res/xml/			如：some.xml								由开发人员自己定义

原始文件			/res/raw/			如：some_audio.mp3、main_video.mp4			

布局文件			/res/layout/		如：start_screen.xml						多种定义

样式和主题			/res/values/		styles.xml、themes.xml						<style>


android 有很多种类的资源，有7个是在res下新建目录然后将资源放在目录下：
res/values,res/xml,res/layout,res/drawable,res/anim,res/menu,res/raw
还有一种是存放在res同级的目录assets目录下，也就是说大体上android资源种类有8类。

	res/values : 存放字符串，颜色，尺寸，数组，主题，类型等资源。
	
	res/xml : 可以是任意类型的xml文件，这些xml文件可以在运行时读取
	
	res/layout:布局文件
	
	res/drawable：图片（bmp,png,gif,jpg等）
	
	res/anim: xml格式的动画资源 （帧动画 和 补间动画）
	
	res/menu:菜单资源
	
	res/raw：会封装在apk，但是不会被编译，可以放任意类型文件，一般存放比较大的音频，视频，图片，文档，会在R类生成资源ID
	
	assets：可以存放任意类型，不会被编译，与raw相比，不会在R类中生成资源ID



	




