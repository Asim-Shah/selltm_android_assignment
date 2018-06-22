package com.selltm.assignment.cabs.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.selltm.assignment.R
import com.selltm.assignment.cabs.pojos.Cab

/**
 * Created by Asim on 22/06/18.
 */
class CabsAdapter : RecyclerView.Adapter<CabsAdapter.CabsViewHolder>() {
    private var context: Context? = null
    private var onClickListener: View.OnClickListener? = null
    private var cabArrayList: ArrayList<Cab> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.cab_list_single_item, parent, false)
        return CabsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CabsViewHolder, position: Int) {
        val cab = cabArrayList[position]
        holder.carTypeTV?.text = cab.carType

        holder.carNamesTV?.text = cab.carNames1
        holder.pickupDateTimeTV?.text = cab.pickupDateAndTime
        holder.fareTV?.text = context?.getString(R.string.string_fare, cab.fare.toString())
        holder.totalSeatsTV?.text = context?.getString(R.string.string_total_seats, cab.totalSeats)
        holder.smallSeatsTV?.text = cab.smallBags.toString()
        holder.bigSeatsTV?.text = cab.bigBags.toString()
        holder.commissionTV?.text = context?.getString(R.string.string_commission, cab.commission.toString())
        holder.noteTV?.text = context?.getString(R.string.string_note, cab.chargePerKm.toString())

        holder.itemView.setTag(R.id.car_type_TV, position)
        holder.itemView.setOnClickListener(onClickListener)
    }

    fun setData(cabArrayList: ArrayList<Cab>) {
        this.cabArrayList = cabArrayList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cabArrayList.size
    }

    class CabsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var carTypeTV: TextView? = null
        var carNamesTV: TextView? = null
        var pickupDateTimeTV: TextView? = null
        var fareTV: TextView? = null
        var totalSeatsTV: TextView? = null
        var smallSeatsTV: TextView? = null
        var bigSeatsTV: TextView? = null
        var commissionTV: TextView? = null
        var noteTV: TextView? = null

        init {
            carTypeTV = view.findViewById(R.id.car_type_TV)
            carNamesTV = view.findViewById(R.id.cars_names_TV)
            pickupDateTimeTV = view.findViewById(R.id.pickup_date_time_TV)
            fareTV = view.findViewById(R.id.fare_TV)
            totalSeatsTV = view.findViewById(R.id.total_seats_TV)
            smallSeatsTV = view.findViewById(R.id.small_seats_TV)
            bigSeatsTV = view.findViewById(R.id.big_seats_TV)
            commissionTV = view.findViewById(R.id.commission_TV)
            noteTV = view.findViewById(R.id.note_TV)
        }
    }

    companion object {
        fun getInstance(context: Context, onClickListener: View.OnClickListener) : CabsAdapter {
            val cabsAdapter = CabsAdapter()
            cabsAdapter.context = context
            cabsAdapter.onClickListener = onClickListener
            return cabsAdapter
        }
    }
}

/*



 */