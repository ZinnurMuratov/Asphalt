package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.mikepenz.fastadapter.IItem
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.FeedLayout
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import zinnur.iot.rockylabs.asphalt.data.entity.FeedEntity
import zinnur.iot.rockylabs.asphalt.data.entity.GetFeedResponseEntity
import zinnur.iot.rockylabs.asphalt.presentation.UI.adapters.items.FeedItem
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.FeedPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.FeedView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.FeedViewState


/**
 * Created by Zinnur on 16.02.17.
 */

class FeedController :  MvpViewStateController<FeedView, FeedPresenter, FeedViewState>(), FeedView{

    private lateinit var fastItemAdapter: FastItemAdapter<FeedItem>
    private lateinit var footerAdapter: FooterAdapter<ProgressItem>

    private val viewBinder = FeedLayout()
    lateinit var rv: RecyclerView

    override fun createPresenter(): FeedPresenter = daggerComponent.feedPresenter()

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun onNewViewStateInstance() {}

    override fun createViewState(): FeedViewState = FeedViewState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = viewBinder.bind(this)
        (activity as MainView).changeTitle("Feed")
        initAdapter()
        return view
    }

    fun initAdapter(){
        fastItemAdapter = FastItemAdapter()
        footerAdapter = FooterAdapter()
        rv.layoutManager = LinearLayoutManager(activity)
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = footerAdapter.wrap(fastItemAdapter)
        rv.addOnScrollListener(scrollListener(footerAdapter))
    }


    fun scrollListener(footerAdapter: FooterAdapter<ProgressItem>): EndlessRecyclerOnScrollListener{
        return object :EndlessRecyclerOnScrollListener(footerAdapter){
            override fun onLoadMore(currentPage: Int) {
                Log.d("page", " -> " + currentPage)
                footerAdapter.clear()
                footerAdapter.add(ProgressItem().withEnabled(false))
                presenter.getFeed(currentPage)
            }
        }
    }

    override fun showError() {}

    override fun setFeed(feed: FeedEntity) {
        fastItemAdapter.add(fastItemAdapter.adapterItemCount, FeedItem().withImage(feed.imageUrl))
    }




}

