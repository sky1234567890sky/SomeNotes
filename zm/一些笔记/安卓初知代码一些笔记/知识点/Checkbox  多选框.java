	//CheckBox  多选框
	
// 1:概念
	
复选框是一种双状态按钮的特殊类型，复选框的状态只有两种：选中或者未选中状态，因此复选框状态变化包含两种情况：
复选框由选中状态变成未选中状态
复选框由未选中状态变成选中状态
通过鼠标单击复选框，可触发复选框状态的改变。
通过setOnCheckedChangeListener()方法注册复选框组件状态改变监听器OnCheckedChangeListener。
	
此类定义如下:
	java.lang.Object
		android.view.View	
			android.widget.TextView
				android.widget.Button
					android.widget.CompoundButton
						android.widget.CheckBox
	

// 2:常用属性：
     android:checked="false" 设置是否被选中，true为选中， false为未选中
     
	 android:text="文本" 设置多选按钮的内容显示。
	
	方法											功能描述

	dispatchPopulateAccessibilityEvent()		在子视图创建时，分派一个辅助事件

	isChecked()			判断组件状态是否勾选					

	onRestoreInstanceState()	设置视图恢复以前的状态，该状态由onSaveInstanceState()方法生成

	performClick()		执行Click动作，该动作会触发事件监听器

	setButtonDrawable()		根据Drawable对象设置组件的背景

	setChecked()	设置组件的状态。若参数为真，则置组件为选中状态；否则置组件为未选中状态

	setOnCheckedChangeListener()		CheckBox常用的设置事件监听器的方法，状态改变时调用该监听器

	toggle()		改变按钮的当前状态

	drawableStateChanged()		视图状态的变化影响到所显示可绘制的状态时调用该方法

	onCreateDrawableState()		获取文本框为空时，文本框默认显示的字符串

	
	

	