ListView �Ż�����

ʹ��convertView+ViewHolder

  *ViewHolder����һ����̬�࣬ʹ�� ViewHolder �Ĺؼ��ô��ǻ�������ʾ���ݵ���ͼ��View�����ӿ��� UI ����Ӧ�ٶȡ�
  *���ж� convertView == null  ʱ���ͻ��������ListViewÿ��item����õ�.xml���֣����ø�convertView.
  *ʵ����һ��ViewHolder��������converView����ĸ���View�ؼ���XML�����������Щ�ؼ�����
  *convertViewͨ��setTag��viewHolder���õ�Tag�У��Ա�ϵͳ�ڶ��λ���ListViewʱ��Tag��ȡ����
  *���convertView��Ϊ�յ�ʱ�򣬾ͻ�ֱ����convertView��getTag()�������һ��ViewHolder����


С�Ż�
  
public View getView(int position, View contextView, ViewGroup parent) {

    	// ��ȡָ������ֵ������
		String string = list.get(position);

		//�����ļ�Ϊ��ʱ
		if (contextView == null) {
			// ͨ��LayoutInflater ��� from ���� �� ʹ�� inflate()�����õ�ָ���Ĳ���
			// �õ�ListView��Ҫ��ʾ����Ŀ�Ĳ���
			LayoutInflater from = LayoutInflater.from(context);
			contextView = from.inflate(R.layout.item, null);
			// ��Ҫ��ʾ����Ŀ������ ���ָ�������
			Temp.tv = (TextView) contextView.findViewById(R.id.tv);
		}


		// ������ֵ
		Temp.tv.setText(string);

		// ���ز���
		return contextView;
	}

	//��̬�ڲ���,��֤��һֱ���Ҵ˶���(�Ż�)
	static class Temp {
		static TextView tv;
	}
	
	
	
	
��ʾ
	
//�������ȶ���,ViewHold��̬��
	static class ViewHolder
	{
		public ImageView img;
		public TextView title;
		public TextView info;
	}
//Ȼ����дgetView
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