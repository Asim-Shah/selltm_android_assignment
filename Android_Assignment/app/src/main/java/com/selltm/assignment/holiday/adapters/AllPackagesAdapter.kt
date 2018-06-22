package com.selltm.assignment.holiday.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.selltm.assignment.R
import com.squareup.picasso.Picasso

/**
 * Created by Asim on 22/06/18.
 */
class AllPackagesAdapter : RecyclerView.Adapter<AllPackagesAdapter.AllPackagesViewHolder>() {
    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPackagesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.all_packages_single_item, parent, false)
        return AllPackagesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AllPackagesViewHolder, position: Int) {
        Picasso.with(context).load(R.drawable.image2).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return 5
    }

    class AllPackagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView? = null

        init {
            imageView = view.findViewById(R.id.images_view)
        }
    }

    companion object {
        fun getInstance(context: Context) : AllPackagesAdapter {
            val allPackagesAdapter = AllPackagesAdapter()
            allPackagesAdapter.context = context
            return allPackagesAdapter
        }
    }

}