package com.hubiao.view.module.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hubiao.view.R

class SimpleRecyclerViewAdapter :
    RecyclerView.Adapter<SimpleRecyclerViewAdapter.SimpleViwHolderView> {

    private val mContext: Context
    private val mLayoutInflater: LayoutInflater

    private var mDataList: List<() -> String>? = null

    constructor(context: Context) : super() {
        mContext = context

        mLayoutInflater = LayoutInflater.from(mContext)
    }

    fun setData(dataList: List<() -> String>) {
        mDataList = dataList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleViwHolderView {
        return SimpleViwHolderView(
            mLayoutInflater.inflate(
                R.layout.item_simple_recyclerview_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SimpleViwHolderView,
        position: Int
    ) {
        holder.contentTv?.text = "Item[$position] is ${mDataList?.get(position)}"
    }

    override fun getItemCount(): Int {
        return mDataList?.size ?: 0
    }

    class SimpleViwHolderView : RecyclerView.ViewHolder {
        var contentTv: TextView? = null

        constructor(itemView: View) : super(itemView) {
            contentTv = super.itemView.findViewById(R.id.tv_simple_recyclerview_content)
        }
    }
}