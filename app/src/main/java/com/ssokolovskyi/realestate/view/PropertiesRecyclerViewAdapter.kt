package com.ssokolovskyi.realestate.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ssokolovskyi.realestate.view.ViewAction.Bookmark
import com.ssokolovskyi.realestate.view.model.PropertyItem

class PropertiesRecyclerViewAdapter(private val bookmark: (Bookmark) -> Unit) :
  RecyclerView.Adapter<PropertyViewHolder>() {

  private var properties: List<PropertyItem> = emptyList()

  fun setData(items: List<PropertyItem>) {
    properties = items
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
    return PropertyViewHolder.create(parent)
  }

  override fun getItemCount(): Int {
    return properties.size
  }

  override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
    holder.bind(properties[position], bookmark)
  }
}
