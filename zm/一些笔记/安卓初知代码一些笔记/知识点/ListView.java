ListView ���

// ����
ListView�����Ӧ�ó����п���˵�ǲ��ɻ�ȱ��һ���֣�ListView��Ҫ����ʾ�б����ݣ�ͬʱ���Թ����鿴�б��������ݡ�
ListView��ļ̳нṹ������ʾ��

java.lang.Object
	android.view.View
  		android.view.ViewGroup
  	 		android.widget.AdapterView<T extends android.widget.Adapter>
  	 			android.widget.AbsListView
  	 	 	 		android.widget.ListView


		
		
// �б����ʾ��Ҫ����Ԫ�أ�
 ListVeiw������չʾ�б��View
 ������������������ӳ�䵽ListView�ϵ��н�   
 ���ݼ�������Ľ���ӳ����ַ�����ͼƬ�����߻��������
ע�⣺������Ԫ�ؽ������ʵ��ListViewЧ��
��xml��ζ���ListView�����
 <ListView
        android:id="@+id/listview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" >
 </ListView>
				

// ListView��������
  cacheColorHint���ԣ������ػ�ʱ�Ļ�����ɫ��
        android:cacheColorHintָ��Ϊ͸����#00000000��
  
  fadingEdge���ԡ����ϱߺ��±��к�ɫ����Ӱ��
		android:fadingEdge="none" ���ú�û����Ӱ��
  
  scrollbars���ԡ�������listView�Ĺ�������
		android:scrollbars="none"   
  
  fadeScrollbars���ԡ���android:fadeScrollbars="true" 
		ʵ�ֹ��������Զ����غ���ʾ
  
  divider���ԡ��������м��ķָ��ߣ��ָ��߿����Զ�����ɫ��ͼƬ
  
  android:dividerHeight����----���÷ָ��ߵĸ߶�
  
  listSelector���ԡ��������б���ѡ�л��������ɫ
		��������android:listSelector=��@null����ѡ�л����б���ʱ�ޱ�����ɫ�仯

		
		
		

1.// ����Item֮���޼�϶

android:divider="#00000000" ������javaCode�����¶��壺listView.setDividerHeight(0);

android:divider="@drawable/list_driver" ���÷ָ��ߵ�ͼƬ��Դ�������ֻҪ����Ϊ

android:divider="@drawable/@null" ������ʾ�ָ���

 

android:scrollbars="none" setVerticalScrollBarEnabled(true); ����listView�Ĺ�����

android:fadeScrollbars="true" ����Ϊtrue�Ϳ���ʵ�ֹ��������Զ����غ���ʾ

2.����cacheColorHint���ԣ�

�ܶ���ϣ���ܹ��ı�һ�����ı�����ʹ���ܹ����������UI��ƣ�
�ı䱳�����ܼ�ֻ��Ҫ׼��һ��ͼƬȻ��ָ������ 
android:background="@drawable/bg"��������Ҫ���˵�̫�磬
������ô���Ժ󣬷��ֱ����Ǳ��ˣ����ǵ����϶���
���ߵ��list�հ�λ�õ�ʱ����ListItem����ɺ�ɫ���ˣ��ƻ�������Ч����

�����ֻ�ǻ���������ɫ�Ļ�������ֱ��ָ��android:cacheColorHintΪ����Ҫ����ɫ��
���������ͼƬ�������Ļ�����ҲֻҪ��android:cacheColorHintָ��Ϊ͸����#00000000���Ϳ�����
	

// �Զ���ListView
		����:

1.��xml�ļ��ж���ListView��ǩ
2.����һ������item,�����д���ListView��ҩ��ʾ����Ŀ����
3.����һ����̳г�����BaseAdapter,��ʵ�ֳ������е��ĸ����󷽷�
	����Ҫ��ListView����ʾ�����ݴ��ݸ���������
	BaseAdapter�еĳ��󷽷�
		public int getCount()	//�˷������ؾ�����ListView�������ٸ���Ŀ
		public Object getItem(int arg0)	//��������������Ҫ��ָ��λ����ʾ�����ݶ���
		public long getItemId(int arg0)
		//ÿ����һ����Ŀ�ͻ����һ���������,�ڴ˷�����ָ��������Ŀ����ʽ,
		//����Ҫ��ʾ����Ӧ��������ӵ���Ӧ����Ŀ�ؼ���
		//position ��ʾ��һ�ν�Ҫ���Ƶڶ��ٸ���Ŀ
		//contextView Ĭ��ֵΪnull,���Ǹ������¸�ֵ,����ָ����Ŀ��ʽ
		//����ȡ��Ŀ�е����пؼ�,����Ӧ���Ը�ֵ
		public View getView(int position, View contextView, ViewGroup arg2)
4.�����Զ���Adapter����
5.���Զ���Adapter�����콣����ListView������


// ListView���õ������
	������ʹ��ListView��ʱ��һ�㶼��ΪListView���һ����Ӧ�¼�
	Android.widget.AdapterView.OnItemClickListener
      �������أ��뿴������룺

listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
		����ʵ�ּ����������

    }
	
});


��������:

	�̼���: setOnItemClickListener
	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
						
			}
		});	
	
	
	��������: setOnItemLongClickListener
	
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});	
	
