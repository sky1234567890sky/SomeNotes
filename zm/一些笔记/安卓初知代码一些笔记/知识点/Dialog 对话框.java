 Dialog
 
֪ʶĿ�꣺
	�Ի���AlertDialog�Ĺ��ܺ�ʹ��
	ProgressDialog���������ĶԻ��� (�ص�)
	
	
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
	
	
Dialog����:

	��Android�����У�������Ҫ�ڽ����ϵ���һЩ�Ի���������ʾ�û�������Ϣ�������û�����ѡ�������Android�еĶԻ����ܡ�
	��Android�У��Ի�����ʹ��Dialog����ʵ�֡�����
	AlertDiaLog����ʵ����ʾ�;���ĶԻ���
	ProgressDialog����ʵ�ִ��������ĶԻ���
	
 
һ��AlertDialogʵ��

	AlertDialog��Dialog��һ��ֱ�����࣬һ��AlertDialog����������Button����3��Button�����Զ�һ��AlertDialog����title��message��
����ֱ��ͨ��AlertDialog�Ĺ��캯��������һ��AlertDialog��һ�����ɵ�ʱ����ͨ�����ĵ�һ���ڲ���̬��AlertDialog.Builder������� ��
 
	ʾ����������:
Dialog dialog = new AlertDialog.Builder(this) 
	.setTitle("�Ի���") // ���ñ��� 
	.setMessage("��ʾ��ʾ��Ϣ��") // ��ʾ��Ϣ 
	.setIcon(R.drawable.file_icon) // ������ʾ��Icon 
	.create(); // ����Dialog 
dialog.show(); //��ʾ�Ի���
}


�Ի���ķ��ࣺ

    1.������ʾ�Ի���
	������һ����򵥵�Ӧ�ã����ǵ���һ����Ϣ����android�п�������ʵ��,��������:

AlertDialog dialog = new AlertDialog.Builder(self)		self (���java�ļ�����.this)
	.setTitle("����")
	.setMessage("����Ϣ��")
	.setPositiveButton("ȷ��",null)
	.create()
dialog.show();	
	
	2.�����Ǵ�ȷ�Ϻ�ȡ����ť�ĶԻ��� 
	��������:
	
AlertDialog dialog = new AlertDialog.Builder(self)
	.setTitle("ȷ��")
	.setMessage("ȷ����?")
	.setPositiveButton("��",null)	
	.setNegativeButton("��",null)
	.create()
dialog.show();
	
	ע�⣺����������null����������Ҫ�ŵ���ʵ����������ť����ļ������������������ﲻ��Ҫ������Щ���������Դ���nullֵ�򵥺��Ե�������ʵ�ʿ�����ʱ��һ�㶼����Ҫ����������ģ�������Ӧ�û��Ĳ����� 

 
	3.�������ı��ĶԻ���

AlertDialog dialog = new AlertDialog.Builder(self)
	.setTitle("������")
	.setIcon(R.drawable.a)
	.setView(new EditText(self))
	.setPositiveButton("ȷ��",null)
	.setNegativeButton("ȡ��",null)
	.create()
dialog.show() 


�����������Ի��� ProgressDialog

��ʽһ�� new Dialog

	final ProgressDialog dialog = new ProgressDialog(this); 
	dialog.show(); 

��ʽ����ʹ�þ�̬��ʽ��������ʾ�����ֽ�����ֻ����Բ����,����title��Message��ʾ���ݡ�

	ProgressDialog dialog2 = ProgressDialog.show(this, "��ʾ", "���ڵ�½��"); 
	dialog2 .show();


