
// TextView���

-- ����:

TextView�������Ҫ������������ʾ�ı���
	ʵ�������ֿؼ���Ҫ�����ṩ��һ����ǩ����ʾ���������ඨ�����£� 
	java.lang.Object
		android.view.View
			android.widget.TextView

TextView��һ��������ʾ�ḻ���ı���Ϣ�Ŀؼ�


-- ��������	
	
	setText();           //�����ı����ݣ�ͬxml�е�android:text
    setTextSize();       //�����ı������С��ͬxml�е�android:textSize
����setTextColor();      //�����ı���ɫ��ͬxml�е�android:textColor
����setBackgroundColor();  //���ñ�����ɫ��ͬxml�е�android:background   
	android:background  //���ñ�����ɫ����ͼƬ
    android:gravity         //�����ı�λ�ã����ó�"center"���ı���������ʾ
	
	���������С�Ƽ�ʹ��sp��Ϊ��λ
	���ÿ�Ȼ�߶ȵ�����ʱ�Ƽ�ʹ��dp(dip)��Ϊ��λ

-- ����¼���������:
	
	android:autoLink     //�����Ƿ���ʾΪ�ɵ�������ӡ���ѡֵ(none/web/email/phone/map/all)
	android:linksClickable          //���õ��ʱ�Ƿ����ӣ���ʹ������autoLink
	
-- ����ͼƬ����:

	android:drawableBottom      //��text���·����һ��drawable(ͼƬ)

����android:drawableLeft           //��text��������һ��drawable(ͼƬ)

����android:drawableRight         //��text���ұ����һ��drawable(ͼƬ)

����android:drawableTop           //��text�����Ϸ����һ��drawable(ͼƬ)

����android:drawablePadding     //����text��drawable(ͼƬ)�ļ������drawableLeft��drawableRight��drawableTop��drawableBottomһ��ʹ�ã�������Ϊ����������ʹ��û��Ч��


-- �����ı�������

	android:ellipsize       //���õ����ֹ���ʱ���ÿؼ��������ʾ����������������ֵ��"start"ʡ�Ժ���ʾ�ڿ�ͷ;"end��ʡ�Ժ���ʾ�ڽ�β;"middle"ʡ�Ժ���ʾ���м�; "marquee" ������Ƶķ�ʽ��ʾ(���������ƶ�)

	android:marqueeRepeatLimit     //��ellipsize�趨Ϊmarqueeʱ�������ظ������Ĵ���������Ϊmarquee_foreverʱ��ʾ���޴Ρ�

����android:lines                      //�����ı����������������о���ʾ���У���ʹ�ڶ���û������

����android:shadowRadius         //������Ӱ�İ뾶������Ϊ0.1�ͱ���������ɫ�ˣ�һ������Ϊ3.0��Ч���ȽϺ�

����android:shadowColor           //ָ���ı���Ӱ����ɫ����Ҫ��shadowRadiusһ��ʹ��

����android:singleLine               //���õ�����ʾ

����android:textColorLink           //�����������ӵ���ɫ

����android:textScaleX              //��������֮������Ĭ��Ϊ1.0f

����android:textStyle                //�������� bold(����) 0, italic(б��) 1, bolditalic(�ִ���б) 2, ��������һ���������á�|������

����android:typeface                 //�����ı����壬���������³���ֵ֮һ��normal 0, sans 1, serif 2, monospace(�ȿ�����) 3

	android:maxLength		//�����ı��ĸ���

�����Ч��:

	android:ellipSize�������ֹ���ʱ���ÿؼ��������ʾ���أ�

    start��ʡ�Ժ���ʾ�ڿ�ͷ

	end��ʡ�Ժ���ʾ�ڽ�β

	middle��ʡ�Ժ���ʾ���м�

	marquee��������Ƶķ�ʽ��ʾ

<!--�����ε��ܶ�-->

	android:marqueeRepeatLimit=��marquee_forever��

<!--����ʱ��ý���-->

	android:focuseableTouchMode=��true��

	
	
	
	