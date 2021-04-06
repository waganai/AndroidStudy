package com.example.jetpackdemeo.view.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemeo.databinding.ItemSimpleLayoutBinding

class SimpleAdapter(context: Context) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

    companion object{
        val TAG = SimpleAdapter::class.simpleName
    }

    private val mContext: Context = context
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var mContentList: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        Log.e(TAG, "onCreateViewHolder()")

        return SimpleViewHolder(ItemSimpleLayoutBinding.inflate(mLayoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.setContent(mContentList?.get(position) ?: "666")
        holder.mPosition = position

        Log.e(TAG, "onBindViewHolder() $holder")
    }

    override fun getItemCount(): Int {
        return mContentList?.size ?: 0
    }

    fun setContentList(contentList: List<String>): SimpleAdapter {
        mContentList = contentList

        return this@SimpleAdapter
    }

    class SimpleViewHolder(binding: ItemSimpleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val mBinding = binding
         var mPosition = -1
        fun setContent(content: String) {
            mBinding.tvContent.text = content
        }

        fun getContent(): String {
            return mBinding.tvContent.text.toString()
        }

        override fun toString(): String {
            return "${mBinding.tvContent.text.toString()}, $mPosition"
        }
    }
}