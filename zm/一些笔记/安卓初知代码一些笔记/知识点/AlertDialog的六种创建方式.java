AlertDialog的六种创建方式

创建AlerDialog的步骤:
	1.创建AlertDialog.Builder对象
	2.调用Builder对象的 setTitle方法设置标题,setIcon方法设置图标
	3.调用Builder相关方法如 setMessage方法、setItems方法、setSingleChoiceItems方法、setMultiChoiceItems方法、
							setAdapter方法、setView方法设置不同类型的对话框内容。
	4.调用setPositiveButton、setNegativeButton、setNeutralButton设置多个按钮
	5.调用Builder对象的create() 方法创建AlertDialog对象
	6.调用AlertDialog对象的show()方法将对话框显示出来
	
	
一、setMessage：设置对话框内容为简单文本内容

// 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置参数
        builder.setTitle("请做出选择")
				.setIcon(R.drawable.ic_launcher)
                .setMessage("我美不美")
                .setPositiveButton("美", new OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, "恭喜你答对了", 0)
                                        .show();
                            }
                        }).setNegativeButton("不美", new OnClickListener() {// 消极

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, "一点也不老实", 0)
                                        .show();
                            }
                        }).setNeutralButton("不知道", new OnClickListener() {// 中间级

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, "快睁开眼瞅瞅", 0)
                                        .show();
                            }
                        });
        builder.create().show();
		
		
		
二、setItem：设置文本框内容为简单列表项

// 创建数据       
 final String[] items = new String[] { "北京", "上海", "广州", "深圳" };
// 创建对话框构建器
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
    // 设置参数       
	builder.setIcon(R.drawable.ic_launcher).setTitle("提示")
     .setItems(items, new OnClickListener() {

    @Override
     public void onClick(DialogInterface dialog, int which) {
     Toast.makeText(MainActivity.this, items[which],
       Toast.LENGTH_SHORT).show();
		}
	});
	
     builder.create().show();


三、setSingleChoiceItems()设置对话框内容为单选列表项

// 创建数据        final String[] items = new String[] { "北京", "上海", "广州", "深圳" };
// 创建对话框构建器
AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 设置参数        builder.setIcon(R.drawable.ic_launcher).setTitle("提示")
        .setSingleChoiceItems(items, 0, new OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
         // TODO Auto-generated method stub
            Toast.makeText(MainActivity.this, items[which],
                                Toast.LENGTH_SHORT).show();
			}
        });
builder.create().show();


四、setMultiChoiceItems()设置对话框内容为多选项列表

　　　　// 创建数据        final String[] items = new String[] { "北京", "上海", "广州", "深圳" };
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher)
                .setTitle("提示")
                .setMultiChoiceItems(items,
                        new boolean[] { true, true, false, false, false },
                        new OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which, boolean isChecked) {
                                // TODO Auto-generated method stub
                                if (isChecked) {
                                    Toast.makeText(MainActivity.this,
                                            items[which], 0).show();
                                }
                            }
                        });
        builder.create().show();
		
		
		
五、setAdapter()设置对话框内容为自定义列表项（这里是一个布局）


// 创建数据
        final String[] items = new String[] { "北京", "上海", "广州", "深圳" };
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setIcon(R.drawable.ic_launcher)
                .setAdapter(
                        new ArrayAdapter<String>(MainActivity.this,
                                R.layout.item, R.id.tv, items),
                        new OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, items[which],
                                        0).show();
                            }
                        });
        builder.create().show();




六、setView()设置对话框为自定义View


// 创建对话框构建器        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// 获取布局
View view2 = View.inflate(MainActivity.this, R.layout.login, null);

// 获取布局中的控件        final EditText username = (EditText) view2.findViewById(R.id.username);
final EditText password = (EditText) view2.findViewById(R.id.password);
final Button button = (Button) view2.findViewById(R.id.btn_login);

// 设置参数        builder.setTitle("Login").setIcon(R.drawable.ic_launcher)
        .setView(view2);
		
// 创建对话框        final AlertDialog alertDialog = builder.create();
button.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub                String uname = username.getText().toString().trim();
        String psd = password.getText().toString().trim();
        if (uname.equals("shenhua") && psd.equals("123456")) {
            Toast.makeText(MainActivity.this, "登录成功", 0).show();
        }
        Toast.makeText(MainActivity.this, "登录失败", 0).show();
        alertDialog.dismiss();// 对话框消失            }

});

alertDialog.show();		



	 