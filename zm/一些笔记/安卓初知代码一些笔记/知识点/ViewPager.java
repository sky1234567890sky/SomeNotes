ViewPager

Android 3.0�������һ��UI�ؼ�����ViewPager(��ͼ�����л�����)��ʵ���벻��������ƺ�����ؼ���
���Ĵ�Ź��ܣ�
	ͨ�����ƻ����������View���л���һ����������APP ������ҳ����ʵ��ͼƬ�ֲ���
	��Ϊ��3.0������ģ�������ڵͰ汾��ʹ�ã�����Ҫ����v4 ���ݰ�Ŷ~������Ҳ���Կ�����
	ViewPager�ڣ�android.support.v4.view.ViewPagerĿ¼��~ �������Ǿ���ѧϰһ������ؼ��Ļ����÷�~ �ٷ�API�ĵ���ViewPager

	
1. ViewPager�ļ򵥽���

	ViewPager����һ���򵥵�ҳ���л���������ǿ��������������View��Ȼ�����ǿ������һ������Ӷ��л���ͬ��View��
		���ǿ���ͨ��setPageTransformer()����Ϊ���ǵ�ViewPager �����л�ʱ�Ķ���Ч������Ȼ���������ǻ�ûѧ����
		�������ǰ�ΪViewPager���ö����ŵ���һ�»�ͼ�붯�������⣡��ǰ��ѧ��ListView��GridViewһ����
		����Ҳ��Ҫһ��Adapter (������)�����ǵ�View��ViewPager���а󶨣���ViewPager����һ���ض���Adapter���� PagerAdapter��
		���⣬Google�ٷ��ǽ�������ʹ��Fragment�����ViewPager�ģ��������Ը��ӷ��������ÿ��Page���Լ�����ÿ��Page���������ڣ�
		
	�������ṩ������Fragment ר�õ�Adapter��FragmentPageAdapter��FragmentStatePagerAdapter ���Ǽ�Ҫ����������������Adapter������

	FragmentPageAdapter��
	
		��PagerAdapterһ����ֻ�Ỻ�浱ǰ��Fragment�Լ����һ�����ұ�һ�������ܹ��Ỻ��3��Fragment���ѣ�������1��2��3��4�ĸ�ҳ�棺

			����1ҳ�棺����1��2
			����2ҳ�棺����1��2��3
			����3ҳ�棺����1ҳ�棬����2��3��4
			����4ҳ�棺����2ҳ�棬����3��4
			����ҳ����������������~ 
			
	FragmentStatePagerAdapter��
	
		��Fragment���û�������ʱ������Fragment�ᱻ���٣�ֻ�ᱣ��Fragment��״̬������ҳ����Ҫ������ʾ��ʱ�򣬻������µ�ҳ�棡		
			
	���ϣ�FragmentPageAdapter�ʺϹ̶���ҳ����ٵĳ��ϣ�
	��FragmentStatePagerAdapter���ʺ���ҳ��϶����ҳ�����ݷǳ�����(��ռ�ô����ڴ�)�������

2. PagerAdapter��ʹ��		
		
����������������ͨ��PagerAdapter�������ʹ�����PagerAdapter��Ҫ��д������ĸ�������
	��Ȼ����ֻ�ǹٷ����飬ʵ��������ֻ����дgetCount()��isViewFromObject()�Ϳ�����~

	getCount():���viewpager���ж��ٸ�view 

	destroyItem():�Ƴ�һ������λ�õ�ҳ�档�����������δ�������ɾ�������ͼ������Ϊ��ȷ����finishUpdate(viewGroup)����ʱ��ͼ�ܹ����Ƴ��� 

�������������������漰��һ��key�Ķ�����

	instantiateItem(): �ٽ�����λ�õ�view��ӵ�ViewGroup(����)��,��������ʾ���� 
					   �ڷ���һ����������ҳ���Object(key),ͨ������ֱ�ӷ���view����Ϳ�����,��Ȼ��Ҳ�����Զ����Լ���key,
					   ����key��ÿ��viewҪһһ��Ӧ�Ĺ�ϵ 

	isViewFromObject(): �ж�instantiateItem(ViewGroup, int)��������������Key��һ��ҳ����ͼ�Ƿ��Ǵ����ͬһ����ͼ(�������Ƿ��Ƕ�Ӧ�ģ�
						��Ӧ�ı�ʾͬһ��View),ͨ������ֱ��д return view == object! 
		
			
			
			