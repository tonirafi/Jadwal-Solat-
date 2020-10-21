package com.sirem.jadwalsolat.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sirem.jadwalsolat.R
import com.sirem.jadwalsolat.ui.home.DataAzan
import kotlinx.android.synthetic.main.item_time_pray.view.*


class ListPrayAdapter(private var ctx: Context) :
    RecyclerView.Adapter<ListPrayAdapter.ViewHolder>() {
    private val ITEM_VIEW_TYPE_CONTENT = 0
    private val ITEM_VIEW_TYPE_LOADING = 1
    private var listData: ArrayList<DataAzan>? = ArrayList()
    private var isLoadingAdded = false

    init {
        this.listData = java.util.ArrayList<DataAzan>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ctx = parent.context
        return when (viewType) {
            ITEM_VIEW_TYPE_CONTENT -> ViewHolderContent(
                layoutInflater.inflate(R.layout.item_time_pray, parent, false)
            )
            else -> ViewHolderLoading(
                layoutInflater.inflate(R.layout.item_data_loading, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {


        when (getItemViewType(i)) {
            ITEM_VIEW_TYPE_CONTENT -> {
                val data = listData!!.get(i)
                holder.itemView.apply {

                    when {
                        data.namaWaktu.equals("Subuh") -> {
                            img_icon_pray.setImageResource(R.drawable.ic_subuh)
                        }
                        data.namaWaktu.equals("Terbit") -> {
                            img_icon_pray.setImageResource(R.drawable.ic_terbit)

                        }
                        data.namaWaktu.equals("Dzuhur") -> {
                            img_icon_pray.setImageResource(R.drawable.ic_zuhur)
                        }
                        data.namaWaktu.equals("Asar") -> {
                            img_icon_pray.setImageResource(R.drawable.ic_asar)
                        }
                        data.namaWaktu.equals("Maghrib") -> {
                            img_icon_pray.setImageResource(R.drawable.ic_magrib)
                        }
                        data.namaWaktu.equals("Isya") -> {
                            img_icon_pray.setImageResource(R.drawable.ic_isa)
                        }
                    }

                    tv_name_pray.text=data.namaWaktu
                    tv_time_pray.text="${data.jam}:${data.menit}"


                }


            }
            else -> {
                /** nothing to do in here */
            }
        }
    }






    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getList(): ArrayList<DataAzan>? {
        return listData
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0 else listData!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listData?.size!! - 1 && isLoadingAdded) ITEM_VIEW_TYPE_LOADING else ITEM_VIEW_TYPE_CONTENT
    }

    fun add(r: DataAzan) {
        listData?.add(r)
        notifyItemInserted(listData?.size!! - 1)
    }

    fun addAll(dataList: ArrayList<DataAzan>) {
        for (result in dataList) {
            add(result)
        }
    }

    fun remove(r: DataAzan) {
        val position = listData?.indexOf(r)
        if (position != null) {
            if (position > -1) {
                listData?.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
//        add(Pesanan)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = listData?.size?.minus(1)
        val result = position?.let { getItem(it) }
        if (result != null) {
            position.let { listData?.removeAt(it) }
            position.let { notifyItemRemoved(it) }
        }
    }

    fun getItem(position: Int): DataAzan {
        return listData?.get(position)!!
    }

    fun hideLoader() {
        isLoadingAdded = false
    }

    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    class ViewHolderContent(itemView: View) : ViewHolder(itemView)

    class ViewHolderLoading(itemView: View?) : ViewHolder(itemView)

}