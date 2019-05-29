 Dialog
 
知识目标：
	对话框AlertDialog的功能和使用
	ProgressDialog带进度条的对话框 (重点)
	
	
监听方法:

	短监听: setOnItemClickListener
	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
						
			}
		});	
	
	
	长按监听: setOnItemLongClickListener
	
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});	
	
	
Dialog介绍:

	在Android开发中，经常需要在界面上弹出一些对话框，用来提示用户输入信息或者让用户做出选择，这就是Android中的对话框功能。
	在Android中，对话框是使用Dialog类来实现。其中
	AlertDiaLog用于实现提示和警告的对话框。
	ProgressDialog用于实现带进度条的对话框。
	
 
一、AlertDialog实现

	AlertDialog是Dialog的一个直接子类，一个AlertDialog可以有两个Button或者3个Button，可以对一个AlertDialog设置title、message。
不能直接通过AlertDialog的构造函数来生成一个AlertDialog，一般生成的时候都是通过它的的一个内部静态类AlertDialog.Builder来构造的 。
 
	示例代码如下:
Dialog dialog = new AlertDialog.Builder(this) 
	.setTitle("对话框") // 设置标题 
	.setMessage("显示提示信息。") // 显示信息 
	.setIcon(R.drawable.file_icon) // 设置显示的Icon 
	.create(); // 创建Dialog 
dialog.show(); //显示对话框
}


对话框的分类：

    1.文字提示对话框
	首先是一个最简单的应用，就是弹出一个消息框，在android中可以这样实现,代码如下:

AlertDialog dialog = new AlertDialog.Builder(self)		self (这个java文件类名.this)
	.setTitle("标题")
	.setMessage("简单消息框")
	.setPositiveButton("确定",null)
	.create()
dialog.show();	
	
	2.下面是带确认和取消按钮的对话框 
	代码如下:
	
AlertDialog dialog = new AlertDialog.Builder(self)
	.setTitle("确认")
	.setMessage("确定吗?")
	.setPositiveButton("是",null)	
	.setNegativeButton("否",null)
	.create()
dialog.show();
	
	注意：这里有两个null参数，这里要放的其实是这两个按钮点击的监听程序，由于我们这里不需要监听这些动作，所以传入null值简单忽略掉，但是实际开发的时候一般都是需要传入监听器的，用来响应用户的操作。 

 
	3.可输入文本的对话框

AlertDialog dialog = new AlertDialog.Builder(self)
	.setTitle("请输入")
	.setIcon(R.drawable.a)
	.setView(new EditText(self))
	.setPositiveButton("确定",null)
	.setNegativeButton("取消",null)
	.create()
dialog.show() 


二、进度条对话框 ProgressDialog

方式一： new Dialog

	final ProgressDialog dialog = new ProgressDialog(this); 
	dialog.show(); 

方式二：使用静态方式创建并显示，这种进度条只能是圆形条,设置title和Message提示内容。

	ProgressDialog dialog2 = ProgressDialog.show(this, "提示", "正在登陆中"); 
	dialog2 .show();


