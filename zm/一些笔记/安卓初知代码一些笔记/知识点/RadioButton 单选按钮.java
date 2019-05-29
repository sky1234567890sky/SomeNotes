// RadioButton单选按钮

// 1:概念
RadioButton(单选按钮)在Android开发中应用的非常广泛，比如一些选择项的时候，会用到单选按钮。
它是一种单个圆形单选框双状态的按钮，可以选择或不选择。在RadioButton没有被选中时，用户能够按下或点击来选中它。
但是，与复选框相反，用户一旦选中就不能够取消选中。

RadioButton、RadioGroup定义如下：                      

java.lang.Object
	android.view.View	
		android.widget.TextView
			android.widget.Button
				android.widget.CompoundButton
					android.widget.RadioButton
					
					
java.lang.Object
	android.view.View
		android.view.ViewGroup
			android.widget.LinearLayout
				android.widget.RadioGroup

				
实现RadioButton由两部分组成,
也就是RadioButton和RadioGroup配合使用.
RadioGroup是单选组合框，可以容纳多个RadioButton的容器.在没有RadioGroup的情况下，RadioButton可以全部都选中；
当多个RadioButton被RadioGroup包含的情况下，RadioButton只可以选择一个。
并用setOnCheckedChangeListener来对单选按钮进行监听。
				
	
//常用属性

      RadioGroup.getCheckedRadioButtonId (); //获取选中按钮的id
      RadioGroup.clearCheck ()	//---清除选中状态
      RadioGroup.check (int id);	//---通过传入选项id来设置该选项为选中状态
      android:checked="true"  	//xml属性 设置选中状态
	

//设置选择监听的格式如下
	raGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
              
            @Override 
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {  
                   执行的代码
                     }  
        });  

	