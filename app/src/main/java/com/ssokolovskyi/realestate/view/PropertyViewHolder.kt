package com.ssokolovskyi.realestate.view

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.squareup.picasso.Picasso
import com.ssokolovskyi.realestate.R
import com.ssokolovskyi.realestate.view.ViewAction.Bookmark
import com.ssokolovskyi.realestate.view.model.PropertyItem

class PropertyViewHolder(view: View) : ViewHolder(view) {

  companion object {
    fun create(parent: ViewGroup): PropertyViewHolder {
      return PropertyViewHolder(
        LayoutInflater
          .from(parent.context)
          .inflate(R.layout.property_list_item, parent, false)
      )
    }
  }

  private var title = view.findViewById<TextView>(R.id.title)
  private var subtitle = view.findViewById<TextView>(R.id.subtitle)
  private var price = view.findViewById<TextView>(R.id.price)
  private var icon = view.findViewById<ImageView>(R.id.icon)
  private var likeButton = view.findViewById<ToggleButton>(R.id.like_button)

  fun bind(property: PropertyItem, bookmark: (Bookmark) -> Unit) {
    title.text = property.title
    subtitle.text = property.address
    price.text = property.price
    property.imageUrl?.let { loadImage(it) }
    likeButton.setOnCheckedChangeListener(null)
    likeButton.isChecked = property.bookmarked
    likeButton.setOnCheckedChangeListener { _, isChecked -> bookmark(Bookmark(isChecked, property.id)) }
  }

  private fun loadImage(url: String) {
    Picasso
      .get()
      .load(url)
      .fit()
      .centerCrop()
      .into(icon)
  }
}
