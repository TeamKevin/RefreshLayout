# RefreshLayout
这个一个带加载更多和下拉刷新的列表控件，是基于RecyclerView实现的。同时这个控件可以自动判断空数据界面、设置断网界面、数据重新加载。只需要添加相应的监听方法即可,此控件的使用方法和RecyclerView相同。代码如下：  
mRefreshRecyclerView = findViewById(R.id.refreshRecyclerView);  
mRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));  
mRefreshRecyclerView.setAdapter(mAdapter);  
下拉刷新的监听:   
mRefreshRecyclerView.setOnRefreshListener(this);            
加载更多的监听:
mRefreshRecyclerView.setOnLoadMoreListener(this); 
网络异常后重新加载:  
mRefreshRecyclerView.setReloadListener(this);    
数据加载完成后刷新界面:  
mRefreshRecyclerView.setRefreshComplete();  
设置网络异常的界面:  
 mRefreshRecyclerView.showLoadError();  
   
