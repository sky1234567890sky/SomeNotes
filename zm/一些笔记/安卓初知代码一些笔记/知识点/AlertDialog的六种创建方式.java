AlertDialog�����ִ�����ʽ

����AlerDialog�Ĳ���:
	1.����AlertDialog.Builder����
	2.����Builder����� setTitle�������ñ���,setIcon��������ͼ��
	3.����Builder��ط����� setMessage������setItems������setSingleChoiceItems������setMultiChoiceItems������
							setAdapter������setView�������ò�ͬ���͵ĶԻ������ݡ�
	4.����setPositiveButton��setNegativeButton��setNeutralButton���ö����ť
	5.����Builder�����create() ��������AlertDialog����
	6.����AlertDialog�����show()�������Ի�����ʾ����
	
	
һ��setMessage�����öԻ�������Ϊ���ı�����

// ����������
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // ���ò���
        builder.setTitle("������ѡ��")
				.setIcon(R.drawable.ic_launcher)
                .setMessage("��������")
                .setPositiveButton("��", new OnClickListener() {// ����

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, "��ϲ������", 0)
                                        .show();
                            }
                        }).setNegativeButton("����", new OnClickListener() {// ����

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, "һ��Ҳ����ʵ", 0)
                                        .show();
                            }
                        }).setNeutralButton("��֪��", new OnClickListener() {// �м伶

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(MainActivity.this, "�������۳��", 0)
                                        .show();
                            }
                        });
        builder.create().show();
		
		
		
����setItem�������ı�������Ϊ���б���

// ��������       
 final String[] items = new String[] { "����", "�Ϻ�", "����", "����" };
// �����Ի��򹹽���
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
    // ���ò���       
	builder.setIcon(R.drawable.ic_launcher).setTitle("��ʾ")
     .setItems(items, new OnClickListener() {

    @Override
     public void onClick(DialogInterface dialog, int which) {
     Toast.makeText(MainActivity.this, items[which],
       Toast.LENGTH_SHORT).show();
		}
	});
	
     builder.create().show();


����setSingleChoiceItems()���öԻ�������Ϊ��ѡ�б���

// ��������        final String[] items = new String[] { "����", "�Ϻ�", "����", "����" };
// �����Ի��򹹽���
AlertDialog.Builder builder = new AlertDialog.Builder(this);

// ���ò���        builder.setIcon(R.drawable.ic_launcher).setTitle("��ʾ")
        .setSingleChoiceItems(items, 0, new OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
         // TODO Auto-generated method stub
            Toast.makeText(MainActivity.this, items[which],
                                Toast.LENGTH_SHORT).show();
			}
        });
builder.create().show();


�ġ�setMultiChoiceItems()���öԻ�������Ϊ��ѡ���б�

��������// ��������        final String[] items = new String[] { "����", "�Ϻ�", "����", "����" };
        // �����Ի��򹹽���
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher)
                .setTitle("��ʾ")
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
		
		
		
�塢setAdapter()���öԻ�������Ϊ�Զ����б��������һ�����֣�


// ��������
        final String[] items = new String[] { "����", "�Ϻ�", "����", "����" };
        // �����Ի��򹹽���
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("��ʾ")
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




����setView()���öԻ���Ϊ�Զ���View


// �����Ի��򹹽���        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// ��ȡ����
View view2 = View.inflate(MainActivity.this, R.layout.login, null);

// ��ȡ�����еĿؼ�        final EditText username = (EditText) view2.findViewById(R.id.username);
final EditText password = (EditText) view2.findViewById(R.id.password);
final Button button = (Button) view2.findViewById(R.id.btn_login);

// ���ò���        builder.setTitle("Login").setIcon(R.drawable.ic_launcher)
        .setView(view2);
		
// �����Ի���        final AlertDialog alertDialog = builder.create();
button.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub                String uname = username.getText().toString().trim();
        String psd = password.getText().toString().trim();
        if (uname.equals("shenhua") && psd.equals("123456")) {
            Toast.makeText(MainActivity.this, "��¼�ɹ�", 0).show();
        }
        Toast.makeText(MainActivity.this, "��¼ʧ��", 0).show();
        alertDialog.dismiss();// �Ի�����ʧ            }

});

alertDialog.show();		



	 