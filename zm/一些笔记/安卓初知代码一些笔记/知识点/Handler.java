Handler 

֪ʶĿ�꣺

	�� Handler��ʲô     
	�� ΪʲôҪ��Handler
	�� Handler��ô��
	�� Handlerʵ��
	�� ��ϰThread��Runnable
	�� Looper����ϸ����     

һ��Handler��ʲô

	Handler������android��������һ��ǳ���Ҫ�Ļ��ƣ���Handler��ʲô�أ�Handler��android�ṩ���ڸ���UI��һ�׻��ƣ�Ҳ����Ϣ������ơ�

	Handler����Ҫ������������
        (1).�����������߳��з�����Ϣ
        (2).�����߳��л�ȡ��������Ϣ��

	���ͣ�
      (1) ��Ӧ�ó�������ʱ��Android���ȻῪ��һ�����߳� (Ҳ����UI�߳�) �� ���߳�Ϊ��������е�UI�ؼ��� �����¼��ַ���
		����˵�� ��Ҫ�ǵ��һ�� Button ��Android��ַ��¼���Button�ϣ�����Ӧ��Ĳ�����
		���̣߳�UI�̣߳�����android������������е����ĳ���

	  (2) �����ʱ��Ҫһ����ʱ�Ĳ���������: ������ȡ���ݣ����߶�ȡ���ؽϴ��һ���ļ���ʱ���㲻�ܰ���Щ�����������߳��У�
		�����������߳��еĻ����������ּ������� ���5���ӻ�û����ɵĻ������յ�Androidϵͳ��һ��������ʾ  "ǿ�ƹر�"��

	  (3)���ʱ��������Ҫ����Щ��ʱ�Ĳ���������һ�����߳��У���Ϊ���߳��漰��UI���£�Android���߳����̲߳���ȫ�ģ� Ҳ����˵��
		����UIֻ�������߳��и��£����߳��в�����Σ�յġ�
	  
	  (4)���ʱ��Handler�ͳ����ˡ��������������ӵ����� ������Handler���������߳���(UI�߳���)��  �������߳̿���ͨ��Message�������������ݣ����ʱ��
		Handler�ͳе��Ž������̴߳�������(���߳���sendMessage()��������)Message����(�����������)  �� ����Щ��Ϣ�������̶߳����У�������߳̽��и���UI��

����ΪʲôҪ��Handler

	������ǲ���Handlerȥ������Ϣ������UI������
		
		���ǲ��еġ� Android����Ƶ�ʱ�򣬾ͷ�װ��һ����Ϣ���������ݣ�������ƣ��������ѭ�����Ļ��ƣ���û�а취����UI��Ϣ�ģ�
	�ͻ��׳��쳣��Ϣ��
	�׳��쳣�������������ڷ�UI�߳���ȥ����UI
	�ٸ����ӣ��½�һ�����̣߳����涨��һ���������ϵؼ�1����1�����������ʾ���ı����У�ʵ�ֵ���ʱ ��

���� Handler��ô��

	handler���Էַ�Message�����Runnable�������߳��У� ÿ��Handlerʵ��������󶨵����������߳���(һ����λ�����߳�)��
	�����������ã�
       (1)������Ȱ�����Ϣ��runnable����ʹ�����ڽ�����ĳ���㱻ִ��.
       (2)����һ�������ڲ�ͬ���߳���ִ��.

	Handler�п����̺߳ͷַ���Ϣ��һЩ����:
      post(Runnable)ֱ�ӿ���Runnable�߳�
      postAtTime(Runnable��long)��ָ����ʱ��long����ʼ�����߳�
      postDelayed(Runnable long)���ӳ�longʱ�������Runnable�߳�
      sendEmptyMessage(int) ����ָ������Ϣ��ͨ������int�����ֲ�ͬ����Ϣ
      sendMessage(Message)������Ϣ��UI�߳���

	
	MessageQueue����Ϣ���У��������Handler���͹�������Ϣ��������FIFO����ִ�С���Ȼ�����Message����ʵ������ı��棬
				���ǽ�Message������ķ�ʽ���������ģ��ȴ�Looper�ĳ�ȡ��

	Looper����Ϣ�ã����ϵش�MessageQueue�г�ȡMessageִ�С���ˣ�һ��MessageQueue��Ҫһ��Looper��

	Thread���̣߳��������������Ϣѭ��������Ϣѭ����ִ�г�����

	ʵ�ֻ��ƣ�
      android���ṩ��һ���첽�ص�����Handler,ʹ���������ǿ��������һ����ʱ�����������������Ӧ��֪ͨ��


	handler����ʹ�ã�
        (1)�����߳��У�ʹ��handler�ܼ򵥣�newһ��Handler����ʵ����handleMessage��������handleMessage���ṩ�յ���Ϣ����Ӧ�Ĵ��������ɡ���������Ϣ�����Ҹ���UI��
        (2)�����������߳��з�����Ϣ

�ġ� Handlerʵ��	

	�½������̷߳�����Ϣ��

public class MyThread implements Runnable{ 
	public void run(){
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
		//TODO Auto-generated catch block
		e.printStackTrace();
		}
		Message msg = new Message(); //ʵ������Ϣ����
				msg.arg1 = 98;//Я������
				msg.arg2=99;//Я������
        Person person=new Person();//ʵ��������
				msg.obj=person; //Я������Ϊʵ�������
        handler.sendMessage(msg); //������Ϣ
	}
}	

	�����̣߳�UI�̣߳��н��մ�����Ϣ

private  Handler handler = new  Handler(){

	@Override
	public void handleMessage(Message msg){
		super.handleMessage(msg);
		// �ܻ�ȡ��msg.arg1ֵ
		tv.setText("msg.arg1:"+msg.arg1+"----msg.arg2="+msg.arg2);
	}
}

�塢 Looper����ϸ

	�� Android�е�Looper����Ҫ����������װ��Ϣѭ������Ϣ���еģ�������android�߳��н�����Ϣ����handler����������Ϣ�����в�����Ϣ�Ĳ��Ҷ���Ϣ���д��� 
	
	�� Looper����Ҫ��Ϊÿ���߳̿����ĵ�������Ϣѭ���� Ĭ�������android���µ������߳���û�п�����Ϣѭ���ġ�
	�����̳߳��⣬���߳�ϵͳ���Զ�Ϊ�䴴��Looper���󣬿�����Ϣѭ���� Looper���������MessageQueue��
	��MessageQueue��Ҫ���������handler���͵���Ϣ������һ���߳�ֻ����һ��Looper����Ӧһ��MessageQueue�� 
	
	�� ����ͨ����ͨ��Handler��������Looper���н����ġ�Handler�ɿ�����Looper��һ���ӿڣ�
	������ָ����Looper�е�MessageQueue������Ϣ����Handler�����붨���Լ��Ĵ������� 
	Ĭ�������Handler�����䱻����ʱ�����̵߳�Looper�󶨣���Handler�����߳��ж��壬���������̵߳�Looper�󶨡� 

	mainHandler = new Handler() �ȼ��� new Handler��Looper.myLooper()��
    Looper.myLooper()����ȡ��ǰ���̵�looper���� Looper.getMainLooper() 
    ���ڻ�ȡ���̵߳�Looper���� 
	
	�ڷ����߳���ֱ��new Handler() �ᱨ���µĴ���: 
	Can't create handler inside thread that has not called Looper.prepare() 
	ԭ���Ƿ����߳���Ĭ��û�д���Looper������Ҫ�ȵ���Looper.prepare()����Looper��Ȼ���ٵ���Looper.loop()��

	Looper.loop():����looper�е�ѭ���̣߳�Handler�ͻ����Ϣ������ȡ��Ϣ�����ж�Ӧ����

	Looper��һ��ѭ����MessageQueue�����ȡ��Ϣ�ġ�

Handler�ļ�ʹ��

		��UI�߳��д�����Ա����Handler����,����дhandlerMessage()����
			handlerMessage()�����ᱻϵͳ��UI�߳��е���,�����Ҫ����UI�ؼ����������������.
		�ڴ����Ķ��߳���,ʹ�����̵߳�handler�����ϵͳ������ϢMessage.֪ͨϵͳ���Ե���handlerMessage()������.
		��ϵͳ������Ϣʹ�õķ������ĸ�,���ĸ����������ڶ��߳��е��õ�
		
1.sendEmptyMessage() ��ϵͳ����һ���������ݵ�Message����,֪ͨϵͳ����handlerMessage()����,���Ŀؼ�	
2.sendEmptymessageDelayed(int what, long delay)	����һ���������ݵ�Message����,��������ʱ���ٺ��뷢��	
3.sendMessage(Message msg)��ϵͳ����һ�����в�����Message��Ϣ,ϵͳ������Message���󽻸�handlerMessage()����,���û���ȡ����
4.sendMessageDelayed(Message msg,long delay)��һ���������ݵ���Ϣ��ʱ���ٺ��뷢�͸�ϵͳ

�ڷ�����Ϣ�������и������ǳ��ؼ�:
	Message������һ����Ա����what ,��������ʶ����Ϣ������һ�����̷߳��͹�����
		��what����handlerMessage�������޸ĵ�����һ���ؼ�	
		whatֵ�������Լ�������


-- ���ڶ��߳���ֱ�Ӳ���UI�����ʱ��,�ͻ��׳����´���

	android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.�Ӵ�����߳��е�����View������
			Called ����
			From  ��...�ط�
			Wrong	�����
			Thread	�߳�
			ViewRootImpl View������





























































