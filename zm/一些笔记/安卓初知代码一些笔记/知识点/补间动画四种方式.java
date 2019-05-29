补间动画四种：
	AlphaAnimation	透明度渐变效果
	ScaleAnimation	缩放渐变效果
	TranslateAnimation	位移渐变效果
	RotateAnimation	旋转渐变效果
	AnimationSet	组合渐变效果
	
	
1. AlphaAnimation	透明度渐变效果  <alpha><alpha/>
	创建时指定开始以及结束透明度，还有动画的持续时间，
	透明度的变化范围（0,1），0是完全透明，1是完全不透明；

<alpha xmlns:android="http://schemas.android.com/apk/res/android"
     android:fromAlpha="1.0"
     android:toAlpha="0.1"
     android:duration="2000">
    
</alpha>

属性解释:

	fromAlpha：起始透明度

	toAlpha：结束透明度

	透明度范围：0-1，完全透明-完全不透明





2. ScaleAnimation	缩放渐变效果	<scale><scale/>
	创建老师指定开始以及结束的缩放比，以及缩放参考点，
	还有动画的持续时间；

<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="0.2"
    android:toXScale="1.5"
    android:fromYScale="0.2"
    android:toYScale="1.5"
    android:pivotX="50%"
    android:pivotY="50%"
    android:duration="2000" >
    
</scale>


属性解释：

	fromXScale/fromYScale：沿着X轴/Y轴缩放的起始比例

	toXScale/toYScale：沿着X轴/Y轴缩放的结束比例

	pivotX/pivotY：缩放的中轴点X/Y坐标，即举例自身左边缘的位置，
				   比如50%就是以图像的中心为中轴点






3. TranslateAnimation	位移渐变效果	<translate><translate/>
	创建时指定开始以及结束位置，并指定动画的持续时间

<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXDelta="0" 
    android:toXDelta="320"
    android:fromYDelta="0"
    android:toYDelta="0"
    android:duration="2000">
    
</translate>


属性解释：

	fromXDelta/fromYDelta：动画起始位置的X/Y坐标

	toXDelta/toYDelta：动画结束位置的X/Y坐标




4. RotateAnimation	旋转渐变效果 <rotate><rotate/>
	创建时指定动画起始以及结束的旋转角度，以及
	动画持续时间和旋转的轴心；
	
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
     android:fromDegrees="0"
     android:toDegrees="360"
     android:duration="1000"
     android:repeatCount="1"
     android:repeatMode="reverse">
    

</rotate>	
	

属性解释：
	
	fromDegrees/toDegrees：旋转的起始/结束角度
	
	repeatCount：旋转的次数，默认为0，代表一次，假如是其他值，
	比如3，则旋转4次 另外，值为 -1 或者 infinite 时，表示动画永久停止
	
	repeatMode：设置重复模式，默认restart，但只有当repeatCount大于0或者
	infinite 或 -1 时才有效。还可以设置成reverse，表示偶数次显示动画时会
	做方向相反的运动！
	
	
	
5. AnimationSet	组合渐变效果

<set xmlns:android="http://schemas.android.com/apk/res/android">
    
    <!-- 缩放渐变  -->
	<scale 
	    android:fromXScale="0.2"
	    android:fromYScale="0.2"
	    android:toXScale="1.5"
	    android:toYScale="1.5"
	    android:pivotX="50%"
	    android:pivotY="50%"
	    android:duration="2000"/>
	
	<!-- 旋转渐变  -->
	<rotate 
	    android:fromDegrees="0"
	    android:toDegrees="360"
	    android:duration="1000"
	    android:repeatCount="1"
	    android:repeatMode="reverse"/>
	
	<!-- 位移渐变  -->
	<translate 
	    android:fromXDelta="0"
	    android:fromYDelta="0"
	    android:toXDelta="320"
	    android:toYDelta="0"
	    android:duration="2000"/>
	
    <!-- 透明度渐变  -->
    <alpha 
        android:fromAlpha="1"
        android:toAlpha="0"
        android:duration="2000"/>
    

</set>
	
	