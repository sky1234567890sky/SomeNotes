// ������

 ����������
һ��ListView�������ò�ͬ��Adapter������ʾ����������˵��õ�ArrayAdapter��SimpleAdapter���Լ���дBaseAdapter�ȷ�����
 ArrayAdapter����򵥣�ֻ����ʾһ�����֡�
 
 SimpleAdapter��SimpleAdapter����չ����ã����Զ�����ָ����Ĳ��ֳ�����
				���Է���ImageView��ͼƬ���������Է���Button����ť����CheckBox����ѡ�򣩵ȵ�.
 
 �Զ���BaseAdapter:����ʵ�ָ�����ʾ��ItemЧ��������ʹ�����������������

 ��SimpleAdapter���һ����ť��ListView����Ŀ�У��ᷢ�ֿ�����ӣ�����ȴ�޷���ý��㣬
				���������ListView��Item�����ǡ���ʱ�����ķ�������ʹ������������BaseAdapter��
				
				
				
һ: ArrayAdapter 


1.ArrayAdapterʵ��ListViewЧ��
     ���裺
 �� ��xml�������< ListView/>���
 �� �ڴ����л�ȡListView�������
 �� ����List������Ӳ�������
 �� ����ArrayAdapter���󣬽�List�������ø�ArrayAdapter����
 �� ��ArrayAdapter����������ʾ��ListView������档   
				
-- ��������

//ʵ����ListView�������
listView=(ListView) findViewById(R.id.listview);
//ʵ��������
dataList=new ArrayList<String>();
//������ݵ��������棬��������
for(int i=0;i<30;i++){
      dataList.add("��������"+i);
   }
//ʵ����ArrayAdapter����
ArrayAdapter<String>  adapter= new ArrayAdapter<String>(MainActivity.this,
			android.R.layout.simple_expandable_list_item_1,dataList);
//�����������ø�ListView
listView.setAdapter(adapter);
				
-- С��
	
	ʵ��������:
		//context, ������
		//resource, ʹ�õ�xml�ļ�
		//textViewResourceId, ʹ�õ����
		//objects	���������
		new ArrayAdapter<T>(context, resource, textViewResourceId, objects)
		
	����:
	
		new ArrayAdapter<String>(MainActivity.this, R.layout.text, R.id.tv, s)

	ArrayAdapterʵ��ListViewЧ����ֻ����ʾ����Ч����������ͼƬ����ť���ؼ���������ʾ��
	�����ļ������Լ�д��Ҳ������ϵͳ�ģ����������õ�ϵͳ�ġ��Լ�д�Ĳ����а���һ��TextView�Ϳ����ˣ�
	��Ȼϵͳ��Ҳ�а���һ��TextView�Ĳ����ļ������� android.R.layout.simple_expandable_list_item_1,��������ȽϷ��㡣
				
	
	

��: SimpleAdapter 


2.SimpleAdapterʵ���Զ���ListViewЧ��
     ���裺
  �� ��xml�������<ListView/>���
  �� ����ListViewÿ��Item��ͼ��Ӧ��xml�ļ�
  �� ����������ListView�������
  �� ����List<Map<String,Object>>������Ӳ��Եļ�ֵ������
  �� ����SimpleAdapter�Ķ���
  �� ��List<HashMap<String,Object>>�������list_item.xml����ÿ���ؼ���
  �� ��SimpleAdapter�Ķ������ø�ListView
	
	
	ʵ��������:
	
	 ��һ������Context��ʾ�����Ķ���		
	 //context,	������
	 
	 �ڶ�������List<Map<String,Object>������Map��ֵ�Եĸ�ʽ��ӵ�List���档
	 //data,	ʹ�õ�����
	 
	 ����������resource��ListViewÿ��item��Ӧ��.xml����
	 //resource, ʹ�õĲ����ļ�
	 
	 ���ĸ����� from��ʾMap�ж����key�����֣��ŵ���������
	 //from,	���ϼ�ֵ�����鼯
	 
	 ���������to,�������飬������Map��ÿ��key-value��Ӧ��ʾ�Ŀؼ�id��
	 //to ���ֵ��Ӧ��λ��

		new SimpleAdapter(context, data, resource, from, to)
	
	����:
	
		String [] from = {"src","text1","text2"};
		int [] to = {R.id.lv,R.id.tv1,R.id.tv2};
		
		new SimpleAdapter(MainActivity.this, list, R.layout.item, from, to)
	
	
-- С��

 ArrayAdapterʵ��ListViewЧ��ʵ���Ͼ����� android�Դ��б��֣�
		���Զ�������������õ�ArrayAdapter���棬Ȼ����ʾ��ListView���档
 ArrayAdapter�ľ�������ֻ��������ʾЧ��������ʵ��ͼƬ�����������ListViewЧ��
 
 SimpleAdapterʵ��ListViewЧ��ʵ�����ǽ������item��Ӧ��.xml�ļ��ؼ�id,
		��Map<String,Object>�����Objectֵ���а�,�����ʾ��ListView����
 SimpleAdapter�ľ����������item�����������Button�������ò��˽���,�޷����õ������
	
	
	
��: BaseAdapter


3.BaseAdapterʵ��ListView

�����������ÿ��Item�мӸ�button�����ҵ��button�ж�Ӧ�Ĳ������Ǹ���ô���أ�
BaseAdapter������Adapter��Щ��һ����������Adapter����ֱ����
      �乹�췽���н������ݵ����ã�������BaseAdapter����Ҫʵ��һ���̳���
     BaseAdapter���࣬������д����ĺܶ෽����
  
  ��д�ķ����У�
       getCount��������ListView�б�ĳ���
       getItem(int position)����ÿ��item��ͼ��Ӧ��ʵ�������
       getItemId(int position) ����ÿ��item��ͼ��Ӧ������
       getView(int position, View convertView, ViewGroup parent) ����ÿ��item��ͼ

// ListView����ԭ��:

  ListView�ڿ�ʼ���Ƶ�ʱ��ϵͳ���ȵ���getCount�������������ݷ���ֵ�õ�ListView�ĳ��ȡ�
  ���ݵõ��ĳ��ȣ�����getView()������һ��һ�еĻ���ListView��ÿһ��item��ͼ��
  
  ���getCount��������ֵ��0��ListViewһ��Ҳ������ʾ��
  ���getCount()����1��ListView��ֻ��ʾһ�С�getCount�������������Ǽ���ListView����ʾ����item��ͼ��
  
  ���ListView����ǧ���itemҪ��ʾ��Ҫ������ǧ���View��
  ���ǲ����ܣ�Android���Ѿ���������ͼView����������Ҫ���ü��ɡ�

// ���Ĵ���

public class MyAdapter  extends BaseAdapter{
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		//����item����.xml
		convertView=mInflater.inflate(R.layout.list_item, null);
        
		//��ȡitem��������ÿ���ؼ�
		ImageView img=(ImageView) convertView.findViewById(R.id.image);
		TextView title=(TextView) convertView.findViewById(R.id.title);
		TextView price=(TextView) convertView.findViewById(R.id.price);
		
		//�Ӽ���List�����ȡ��ǰitem��Ӧ��Map��������
		Map<String,Object> map=mList.get(position);
		
		//��ʼ��������
		img.setImageResource((Integer)map.get("img"));
		title.setText((String)map.get("title"));
		price.setText((String)map.get("price"));
        
		return convertView;         
    }
}

3.BaseAdapterʵ��ListView:

		���裺
	�� ��xml�������<ListView/>���������������ListView�������
	
	�� �Զ�����������̳���BaseAdapter ,��д�ĸ����󷽷�
	
	�� ����ListViewÿ��item��ʾ��.xml����
	
	�� ���Զ������������棬����item���֣���ȡitem����ÿ���ؼ�����
	
	�� ����������ݣ���ӵ�List����
	
	�� ��List��Ϊ�������ݸ��Զ�����������Ĺ��캯��
	
	�� ���Զ����������getCount������getView()���������ȡList�����Map���󣬲��һ�ȡMap���������ֵ
	
	�� ��Map���������ֵ���ø�item�ؼ���



	