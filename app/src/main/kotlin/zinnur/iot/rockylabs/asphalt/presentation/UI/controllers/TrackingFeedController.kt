package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.presentation.UI.adapters.items.TrackFeedItem
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.TrackingFeedLayout
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.TrackingFeedPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingFeedView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.TrackingFeedViewState
import zinnur.iot.rockylabs.asphalt.presentation.navigator

/**
 * Created by Zinnur on 04.06.17.
 */
class TrackingFeedController :  MvpViewStateController<TrackingFeedView, TrackingFeedPresenter, TrackingFeedViewState>(), TrackingFeedView {

    private lateinit var fastItemAdapter: FastItemAdapter<TrackFeedItem>
    private lateinit var footerAdapter: FooterAdapter<ProgressItem>
    private val LIST_STATE_KEY: String = "LIST_STATE_KEY"
    private var listState: Parcelable? = null
    private val viewBinder = TrackingFeedLayout()
    lateinit var rv: RecyclerView

    override fun createPresenter(): TrackingFeedPresenter = daggerComponent.trackingFeedPresenter()

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {
        Log.d("state", "onViewStateInstanceRestored")

    }

    override fun onNewViewStateInstance() {
        Log.d("state", "onNewViewStateInstance")

    }

    override fun createViewState(): TrackingFeedViewState = TrackingFeedViewState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = viewBinder.bind(this)
        initAdapter()
        Log.d("state", "onCreate")
        return view
    }


    override fun onAttach(view: View) {
        super.onAttach(view)
        (activity as MainView).changeTitle("My roads")
        (activity as MainView).lockDrawer(false)
        (activity as MainView).setHomeEnabled(false)
        Log.d("state", "onAttach")
        listState?.let { rv.layoutManager.onRestoreInstanceState(it) }
    }

    fun initAdapter(){
        fastItemAdapter = FastItemAdapter()
        footerAdapter = FooterAdapter()
        rv.layoutManager = LinearLayoutManager(activity)
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = footerAdapter.wrap(fastItemAdapter)
        rv.addOnScrollListener(scrollListener(footerAdapter))
        fastItemAdapter.withOnClickListener { v, adapter, item, position ->
            val track = item.getTrack()
            retainViewMode = RetainViewMode.RETAIN_DETACH
            navigator.showTrack(track)
            true
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        Log.d("state", "onDestroyView")
    }

    override fun onDetach(view: View) {
        super.onDetach(view)

        Log.d("state", "onDetach")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("state", "onDestroy")

    }

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        Log.d("state", "onRestoreViewState")
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        val bundle = fastItemAdapter.adapterItems
        Log.d("state", "onSaveViewState")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val listState = rv.layoutManager.onSaveInstanceState()
        outState.putParcelable(LIST_STATE_KEY, listState)
        Log.d("state", "on save")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        listState = savedInstanceState.getParcelable(LIST_STATE_KEY)
        Log.d("state", "on restore")

    }


    fun scrollListener(footerAdapter: FooterAdapter<ProgressItem>): EndlessRecyclerOnScrollListener {
        return object : EndlessRecyclerOnScrollListener(footerAdapter){
            override fun onLoadMore(currentPage: Int) {}
        }
    }

    override fun showError() {}

    override fun setFeed(track: Track) {
        fastItemAdapter.add(fastItemAdapter.adapterItemCount, TrackFeedItem().setData(track))
    }




}
