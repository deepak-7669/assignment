package com.example.assignment.util
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
abstract class PaginationScrollListener
(var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    var firstimecalled = true
    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
/*        Log.d("visibleItemCount", visibleItemCount.toString())
        Log.d("totalItemCount", totalItemCount.toString())
        Log.d("dx", dx.toString())
        Log.d("dy", dy.toString())*/
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (firstVisibleItemPosition==0 && firstimecalled){
            Log.d("calledomce","called")
            firstimecalled = false
            loadgrpdata(layoutManager.findFirstVisibleItemPosition(),layoutManager.findLastVisibleItemPosition())
        }
        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }//                    && totalItemCount >= ClothesFragment.itemsCount
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
       /* Log.d("SCROLL_STATE_IDLE", newState.toString())*/
        if (newState == RecyclerView.SCROLL_STATE_IDLE /*|| newState == RecyclerView.SCROLL_STATE_SETTLING*/){
            Log.d("SCROLL_STATE_IDLES", recyclerView.adapter!!.itemCount.toString())
            val firstposition: Int = layoutManager.findFirstVisibleItemPosition()
            val lastposition: Int = layoutManager.findLastVisibleItemPosition()
            loadgrpdata(firstposition,lastposition)
        }
    }
    abstract fun loadMoreItems()
    abstract fun loadgrpdata(firstposition: Int, lastposition: Int)
}