package com.ssokolovskyi.realestate.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.ssokolovskyi.realestate.R
import com.ssokolovskyi.realestate.view.PropertiesListActivity.EmptyStateMode.NetworkUnavailable
import com.ssokolovskyi.realestate.view.PropertiesListActivity.EmptyStateMode.NoEmptyState
import com.ssokolovskyi.realestate.view.model.PropertiesListModelFactory
import com.ssokolovskyi.realestate.view.model.PropertiesListViewModel
import com.ssokolovskyi.realestate.view.model.ViewData
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PropertiesListActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var modelFactory: PropertiesListModelFactory

  private lateinit var recyclerView: RecyclerView
  private lateinit var emptyState: View
  private lateinit var adapter: PropertiesRecyclerViewAdapter
  private lateinit var viewModel: PropertiesListViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_properties_list)
    recyclerView = findViewById(R.id.recycler_view)
    emptyState = findViewById(R.id.empty_state)
    viewModel = ViewModelProviders.of(this, modelFactory)[PropertiesListViewModel::class.java]
    initAdapter()
    observeData()
  }

  private fun initAdapter() {
    adapter = PropertiesRecyclerViewAdapter { action -> viewModel.onViewAction(action) }
    recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    recyclerView.adapter = adapter
  }

  private fun observeData() {
    viewModel.getViewData().observe(this, Observer {
      when (it) {
        is ViewData.ListData -> {
          setEmptyStateMode(NoEmptyState)
          adapter.setData(it.items)
        }
        ViewData.NoNetwork -> {
          setEmptyStateMode(NetworkUnavailable)
        }
      }
    })
  }

  private fun setEmptyStateMode(mode: EmptyStateMode) {
    when (mode) {
      NetworkUnavailable -> {
        emptyState.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
      }
      NoEmptyState -> {
        emptyState.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
      }
    }
  }

  private sealed class EmptyStateMode {
    object NetworkUnavailable : EmptyStateMode()
    object NoEmptyState : EmptyStateMode()
  }
}

sealed class ViewAction {
  class Bookmark(val checked: Boolean, val propertyId: Long) : ViewAction()
}
