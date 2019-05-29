ListView 优化代码

使用convertView+ViewHolder

  *ViewHolder就是一个静态类，使用 ViewHolder 的关键好处是缓存了显示数据的视图（View），加快了 UI 的响应速度。
  *当判断 convertView == null  时，就会加载引用ListView每个item定义好的.xml布局，设置给convertView.
  *实例化一个ViewHolder对象来绑定converView里面的各个View控件（XML布局里面的那些控件）。
  *convertView通过setTag将viewHolder设置到Tag中，以便系统第二次绘制ListView时从Tag中取出。
  *如果convertView不为空的时候，就会直接用convertView的getTag()，来获得一个ViewHolder对象。


小优化
  
public View getView(int position, View contextView, ViewGroup parent) {

    	// 获取指定索引值的数据
		String string = list.get(position);

		//布局文件为空时
		if (contextView == null) {
			// 通过LayoutInflater 类的 from 方法 再 使用 inflate()方法得到指定的布局
			// 得到ListView中要显示的条目的布局
			LayoutInflater from = LayoutInflater.from(context);
			contextView = from.inflate(R.layout.item, null);
			// 从要显示的条目布局中 获得指定的组件
			Temp.tv = (TextView) contextView.findViewById(R.id.tv);
		}


		// 设置数值
		Temp.tv.setText(string);

		// 返回布局
		return contextView;
	}

	//静态内部类,保证不一直查找此对象(优化)
	static class Temp {
		static TextView tv;
	}
	
	
	
	
演示
	
//在外面先定义,ViewHold静态类
	static class ViewHolder
	{
		public ImageView img;
		public TextView title;
		public TextView info;
	}
//然后重写getView
		@Override
		public View getView(int position, View contextView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView == null)
			{
				holder = new ViewHolder();
				convertView = mInflaer.inflate(R.layout.list_item,null);
				holder.img = (ImageView)item.findViewById(R.id.img);
				holder.title = (TextView)item.findViewById(R.id.title);
				holder.info = (TextView)item.findViewById(R.id.info);
				convertView.setTag(holder);
			}else
			{
				holder = (ViewHolder)convertView.getTag();
			}	
				holder.img.setImageResource(R.drawable.ic_launcher);
				holder.title.setText("Hello");
				holder.info.setText("world");
		}
		return convertView;