 1.在自定义数据库是
                                                    banner = (Banner) findViewById(R.id.banner);
													list = new ArrayList<>();
													list.add(R.mipmap.icon_home_pager_selected);
													list.add(R.mipmap.icon_knowledge_hierarchy_not_selected);
													list.add(R.mipmap.icon_me_not_selected);
													list.add(R.mipmap.icon_navigation_selected);

													//1.设置图片数据
													banner.setImages(list);

													//2.图片加载器
													banner.setImageLoader(new ImageLoader() {
														/**
														 *
														 * @param context  上下文对象
														 * @param path   图片路径类型  Object
														 * @param imageView  显示图片
														 */
														@Override
														public void displayImage(Context context, Object path, ImageView imageView) {
															Integer p = (Integer) path;
															imageView.setImageResource(p);

														   // Glide.with(context).load(path).into(imageView);
														}
													});

													banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
													//3.开启使用
													banner.start();
						     2.多布局加载(网络解析轮播图)
							 
													public class MyHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

													private Context context;
													private ArrayList<HomeBannerBean.DataBean> bannerList;
													private ArrayList<HomeArticleBean.DataBean.DatasBean> articleList;

													public MyHomeAdapter(Context context, ArrayList<HomeBannerBean.DataBean> bannerList,
																		 ArrayList<HomeArticleBean.DataBean.DatasBean> articleList) {
														this.context = context;
														this.bannerList = bannerList;
														this.articleList = articleList;
													}

													public void setBannerList(ArrayList<HomeBannerBean.DataBean> bannerList) {
														this.bannerList = bannerList;
													}

													public void setArticleList(ArrayList<HomeArticleBean.DataBean.DatasBean> articleList) {
														this.articleList = articleList;
													}

													@NonNull
													@Override
													public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

														RecyclerView.ViewHolder viewHolder = null;

														if (viewType == 0) {
															View inflate = LayoutInflater.from(context).inflate(R.layout.home_item_banner, null);
															viewHolder = new BannerViewHolder(inflate);
														} else {
															View view = LayoutInflater.from(context).inflate(R.layout.home_item_article, null);
															viewHolder = new ArticleViewHolder(view);
														}
														return viewHolder;
													}

													@Override
													public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
														if (holder instanceof BannerViewHolder) {
															BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

															bannerViewHolder.banner.setImages(bannerList);
															bannerViewHolder.banner.setImageLoader(new ImageLoader() {
																@Override
																public void displayImage(Context context, Object path, ImageView imageView) {
																	HomeBannerBean.DataBean dataBean = (HomeBannerBean.DataBean) path;
																	Glide.with(context).load(dataBean.getImagePath()).into(imageView);
																}
															});
															bannerViewHolder.banner.start();
														} else if (holder instanceof ArticleViewHolder) {
															ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;

															int newPosition = position;
															if (bannerList.size() > 0) {
																newPosition = position - 1;
															}
															final HomeArticleBean.DataBean.DatasBean datasBean = articleList.get(newPosition);
															articleViewHolder.tv_name.setText(datasBean.getAuthor());
															articleViewHolder.tv_info.setText(datasBean.getSuperChapterName());
															articleViewHolder.tv_content.setText(datasBean.getTitle());
															articleViewHolder.tv_time.setText(datasBean.getNiceDate());

															articleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
																@Override
																public void onClick(View v) {
																	if (onItemClickListener!=null){
																		onItemClickListener.onItemClick(datasBean);
																	}
																}
															});
														}
													}


													@Override
													public int getItemViewType(int position) {
														if (position == 0) {
															return 0;
														} else {
															return 1;
														}
													}

													@Override
													public int getItemCount() {
														if (bannerList.size() > 0) {
															return articleList.size() + 1;
														} else {
															return articleList.size();
														}
													}

													class BannerViewHolder extends RecyclerView.ViewHolder {

														private Banner banner;

														public BannerViewHolder(@NonNull View itemView) {
															super(itemView);
															banner = itemView.findViewById(R.id.banner);
														}
													}

													class ArticleViewHolder extends RecyclerView.ViewHolder {

														private TextView tv_name;
														private TextView tv_info;
														private TextView tv_content;
														private TextView tv_time;

														public ArticleViewHolder(@NonNull View itemView) {
															super(itemView);

															tv_name = itemView.findViewById(R.id.tv_name);
															tv_info = itemView.findViewById(R.id.tv_info);
															tv_content = itemView.findViewById(R.id.tv_content);
															tv_time = itemView.findViewById(R.id.tv_time);
														}
													}


													//
													private OnItemClickListener onItemClickListener;

													public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
														this.onItemClickListener = onItemClickListener;
													}

													public interface OnItemClickListener{
														void onItemClick(HomeArticleBean.DataBean.DatasBean datasBean);
													}
												}
