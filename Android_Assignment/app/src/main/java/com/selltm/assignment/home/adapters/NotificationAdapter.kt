package com.selltm.assignment.home.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.selltm.assignment.R
import com.selltm.assignment.home.pojos.Notification

/**
 * Created by Asim on 22/06/18.
 */
class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.notification_list_single_item, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = Notification.instance
        holder.titleTV?.text = notification.title
        holder.uptoTV?.text = notification.upto
        holder.contentTV?.text = notification.content
        holder.tagTV?.text = notification.tags
    }

    override fun getItemCount(): Int {
        return 5
    }

    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTV: TextView? = null
        var uptoTV: TextView? = null
        var contentTV: TextView? = null
        var tagTV: TextView? = null

        init {
            titleTV = view.findViewById(R.id.title_TV)
            uptoTV = view.findViewById(R.id.upto_TV)
            contentTV = view.findViewById(R.id.content_TV)
            tagTV = view.findViewById(R.id.tags_TV)
        }
    }

    /*companion object {
        fun getInstance(context: Context, onClickListener: View.OnClickListener) : NotificationAdapter {
            val cabsAdapter = NotificationAdapter()
            cabsAdapter.context = context
            cabsAdapter.onClickListener = onClickListener
            return cabsAdapter
        }
    }*/
}

/*



 */