package com.germanofilho.app.feature.home.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.germanofilho.app.core.extension.fadeAnimation
import com.germanofilho.app.data.model.entity.Result
import com.germanofilho.chamaapp.R
import kotlinx.android.synthetic.main.item_place.view.*

class PlaceAdapter(private val results : List<Result> = arrayListOf()) : RecyclerView.Adapter<PlaceViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlaceViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_place, p0, false)
        return PlaceViewHolder(itemView, p0.context)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(results[position])
    }
}

class PlaceViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view){
    fun bind(result: Result) = with(view){
        cl_item_parent.fadeAnimation()
        txt_title.text = result.name
        txt_address.text = result.vicinity
        txt_rating.text = result.rating.toString()
        Glide.with(view)
            .load(result.icon)
            .centerCrop()
            .into(img_item)

        setOpeningHoursText(txt_open_closed, result.openingHours?.openNow)
    }


    private fun setOpeningHoursText(view: TextView, isOpened: Boolean?){
        if(isOpened == null || !isOpened){
            view.setTextColor(ContextCompat.getColorStateList(context, R.color.text_closed))
            view.background = ContextCompat.getDrawable(context, R.drawable.border_closed)
            view.text = context.getText(R.string.text_closed)
        } else {
            view.setTextColor(ContextCompat.getColorStateList(context, R.color.text_opened))
            view.background = ContextCompat.getDrawable(context, R.drawable.border_opened)
            view.text = context.getText(R.string.text_opened)
        }
    }
}
