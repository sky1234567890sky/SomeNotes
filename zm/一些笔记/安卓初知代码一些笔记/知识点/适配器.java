// 适配器

 适配器类型
一个ListView，可以用不同的Adapter让它显示出来，比如说最常用的ArrayAdapter，SimpleAdapter，以及重写BaseAdapter等方法。
 ArrayAdapter：最简单，只能显示一行文字。
 
 SimpleAdapter：SimpleAdapter的扩展性最好，可以定义各种各样的布局出来，
				可以放上ImageView（图片），还可以放上Button（按钮），CheckBox（复选框）等等.
 
 自定义BaseAdapter:可以实现各种显示的Item效果，经常使用这个基础适配器。

 用SimpleAdapter添加一个按钮到ListView的条目中，会发现可以添加，但是却无法获得焦点，
				点击操作被ListView的Item所覆盖。这时候最方便的方法就是使用灵活的适配器BaseAdapter了
				
				
				
一: ArrayAdapter 


1.ArrayAdapter实现ListView效果
     步骤：
 ① 在xml里面添加< ListView/>组件
 ② 在代码中获取ListView组件对象
 ③ 定义List对象，添加测试数据
 ④ 定义ArrayAdapter对象，将List对象设置给ArrayAdapter对象
 ⑤ 将ArrayAdapter对象设置显示在ListView组件上面。   
				
-- 代码如下

//实例化ListView组件对象
listView=(ListView) findViewById(R.id.listview);
//实例化集合
dataList=new ArrayList<String>();
//添加数据到集合里面，测试数据
for(int i=0;i<30;i++){
      dataList.add("测试数据"+i);
   }
//实例化ArrayAdapter对象
ArrayAdapter<String>  adapter= new ArrayAdapter<String>(MainActivity.this,
			android.R.layout.simple_expandable_list_item_1,dataList);
//将适配器设置给ListView
listView.setAdapter(adapter);
				
-- 小结
	
	实例化对象:
		//context, 上下文
		//resource, 使用的xml文件
		//textViewResourceId, 使用的组件
		//objects	存入的数据
		new ArrayAdapter<T>(context, resource, textViewResourceId, objects)
		
	例如:
	
		new ArrayAdapter<String>(MainActivity.this, R.layout.text, R.id.tv, s)

	ArrayAdapter实现ListView效果，只能显示文字效果，其他的图片、按钮都控件都不能显示。
	布局文件可以自己写，也可以用系统的，我这里是用的系统的。自己写的布局中包含一个TextView就可以了，
	当然系统中也有包含一个TextView的布局文件：就是 android.R.layout.simple_expandable_list_item_1,调用这个比较方便。
				
	
	

二: SimpleAdapter 


2.SimpleAdapter实现自定义ListView效果
     步骤：
  ① 在xml里面添加<ListView/>组件
  ② 定义ListView每个Item视图对应的xml文件
  ③ 代码里面获得ListView组件对象
  ④ 定义List<Map<String,Object>>对象，添加测试的键值对数据
  ⑤ 定义SimpleAdapter的对象，
  ⑥ 将List<HashMap<String,Object>>跟定义的list_item.xml布局每个控件绑定
  ⑦ 将SimpleAdapter的对象设置给ListView
	
	
	实例化对象:
	
	 第一个参数Context表示上下文对象		
	 //context,	上下文
	 
	 第二个参数List<Map<String,Object>数据以Map键值对的格式添加到List里面。
	 //data,	使用的数据
	 
	 第三个参数resource是ListView每个item对应的.xml布局
	 //resource, 使用的布局文件
	 
	 第四个参数 from表示Map中定义的key的名字，放到数组里面
	 //from,	集合键值的数组集
	 
	 第五个参数to,整数数组，里面是Map中每个key-value对应显示的控件id。
	 //to 与键值对应的位置

		new SimpleAdapter(context, data, resource, from, to)
	
	例如:
	
		String [] from = {"src","text1","text2"};
		int [] to = {R.id.lv,R.id.tv1,R.id.tv2};
		
		new SimpleAdapter(MainActivity.this, list, R.layout.item, from, to)
	
	
-- 小结

 ArrayAdapter实现ListView效果实质上就是用 android自带列表布局，
		将自定义的数据先设置到ArrayAdapter里面，然后显示到ListView上面。
 ArrayAdapter的局限性是只有文字显示效果，不能实现图片和其他组件的ListView效果
 
 SimpleAdapter实现ListView效果实质上是将定义的item对应的.xml文件控件id,
		与Map<String,Object>里面的Object值进行绑定,最后显示到ListView上面
 SimpleAdapter的局限性是如果item布局上面添加Button组件，获得不了焦点,无法设置点击监听
	
	
	
三: BaseAdapter


3.BaseAdapter实现ListView

如果我们想在每个Item中加个button，而且点击button有对应的操作，那该怎么办呢？
BaseAdapter与其他Adapter有些不一样，其他的Adapter可以直接在
      其构造方法中进行数据的设置，但是在BaseAdapter中需要实现一个继承自
     BaseAdapter的类，并且重写里面的很多方法。
  
  重写的方法有：
       getCount（）返回ListView列表的长度
       getItem(int position)返回每个item视图对应的实体类对象
       getItemId(int position) 返回每个item视图对应的索引
       getView(int position, View convertView, ViewGroup parent) 返回每个item视图

// ListView工作原理:

  ListView在开始绘制的时候，系统首先调用getCount（）函数，根据返回值得到ListView的长度。
  根据得到的长度，调用getView()方法，一行一行的绘制ListView的每一项item视图。
  
  如果getCount（）返回值是0，ListView一行也不会显示。
  如果getCount()返回1，ListView就只显示一行。getCount（）返回数字是几，ListView就显示几个item视图。
  
  如果ListView有上千万的item要显示，要创建上千万个View吗？
  答案是不可能，Android早已经缓存了视图View，新增的需要复用即可。

// 核心代码

public class MyAdapter  extends BaseAdapter{
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		//引入item布局.xml
		convertView=mInflater.inflate(R.layout.list_item, null);
        
		//获取item布局上面每个控件
		ImageView img=(ImageView) convertView.findViewById(R.id.image);
		TextView title=(TextView) convertView.findViewById(R.id.title);
		TextView price=(TextView) convertView.findViewById(R.id.price);
		
		//从集合List里面获取当前item对应的Map对象数据
		Map<String,Object> map=mList.get(position);
		
		//开始设置数据
		img.setImageResource((Integer)map.get("img"));
		title.setText((String)map.get("title"));
		price.setText((String)map.get("price"));
        
		return convertView;         
    }
}

3.BaseAdapter实现ListView:

		步骤：
	① 在xml里面添加<ListView/>组件、代码里面获得ListView组件对象
	
	② 自定义适配器类继承自BaseAdapter ,重写四个抽象方法
	
	③ 定义ListView每个item显示的.xml布局
	
	④ 在自定义适配器里面，加载item布局，获取item里面每个控件对象
	
	⑤ 定义测试数据，添加到List里面
	
	⑥ 将List作为参数传递给自定义适配器类的构造函数
	
	⑦ 在自定义类里面的getCount（）、getView()方法里面获取List里面的Map对象，并且获取Map对象的属性值
	
	⑧ 将Map对象的属性值设置给item控件上



	