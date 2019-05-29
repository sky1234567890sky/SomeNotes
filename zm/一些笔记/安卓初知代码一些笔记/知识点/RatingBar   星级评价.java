RatingBar   星级评价


// RatingBar概述

     星级评分条与拖动条十分相似，它们甚至有相同的父类：AbsSeekBar。
	 实际上星级评分条与拖动条的用法、功能都十分接近：它们都允许用户通过拖动来改变进度。
	 RatingBar与SeekBar最大区别在于： RatingBar通过星星来表示进度。
 
 
// 常见 xml属性：

     android:isIndicator：//设置该星级评分条是否允许用户改变（true为不允许）
     android:numStarts：//设置该星级评分条总共有多少个星级
     android:rating：//设置该星级评分条默认的星级
     android:stepSize：//设置每次最少需要改变多少个星级

	 监听方法 setOnRatingBarChangeListener(new OnRatingChangeListener)(){
		 //rating 星星个数
		 public void onRatingChanged(RatingBar ratingbar,float rating,boolean fromUser){
			 
			 Toast.makeText(MainActivity.this,""+rating*20,Toast.LENGTH_SHORT).show();
		 }
		 
	 }
	 
	 