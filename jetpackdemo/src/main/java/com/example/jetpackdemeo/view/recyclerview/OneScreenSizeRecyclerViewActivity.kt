package com.example.jetpackdemeo.view.recyclerview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackdemeo.databinding.ActivityOnescreenRecyclerviewLayoutBinding
import com.example.jetpackdemeo.databinding.ItemOnescreenSizeLayoutBinding
import com.example.jetpackdemeo.thirdframework.glide.BlurTransformation

class OneScreenSizeRecyclerViewActivity : AppCompatActivity() {
    companion object{
        private val TAG = OneScreenSizeRecyclerViewActivity::class.simpleName
    }

    val viewBinding by lazy {
        ActivityOnescreenRecyclerviewLayoutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        init()
    }

    fun init() {
        viewBinding.run {
            rcList.apply {
                layoutManager = LinearLayoutManager(
                    this@OneScreenSizeRecyclerViewActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = OneScreenSizeAdapter(this@OneScreenSizeRecyclerViewActivity)
            }
        }
    }

    class OneScreenSizeAdapter(val context: Context) :
        RecyclerView.Adapter<OneScreenSizeAdapter.OneScreenViewHolder>() {
        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneScreenViewHolder {
            Log.e(TAG, "onCreateViewHolder()")

            return OneScreenViewHolder(
                ItemOnescreenSizeLayoutBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: OneScreenViewHolder, position: Int) {
            Log.e(TAG, "onBindViewHolder($position)")
        }

        override fun getItemCount(): Int {
            return 4
        }

        class OneScreenViewHolder(private val binding: ItemOnescreenSizeLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {

            init {
                binding.apply {
                    Glide
                        .with(root.context)
                        .load("https://b0.bdstatic.com/comment/AbD06ZlrNmmzmXl7Cw5uFA957ac5114bcf7a68d6bd51e8493dfded.jpg@w_967,h_967")
                        .apply(RequestOptions().circleCrop())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .apply(RequestOptions.placeholderOf(com.hubiao.base.R.mipmap.ic_launcher))
                        .transition(GenericTransitionOptions.withNoTransition())
                        .into(ivContent1)

                    Glide
                        .with(root.context)
                        .load("https://b0.bdstatic.com/comment/AbD06ZlrNmmzmXl7Cw5uFA957ac5114bcf7a68d6bd51e8493dfded.jpg@w_967,h_967")
                        .apply(RequestOptions().transform(RoundedCorners(72)))
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .apply(RequestOptions.placeholderOf(com.hubiao.base.R.mipmap.ic_launcher))
                        .transition(GenericTransitionOptions.withNoTransition())
                        .into(ivContent2)

                    Glide
                        .with(root.context)
                        .load("https://b0.bdstatic.com/comment/AbD06ZlrNmmzmXl7Cw5uFA957ac5114bcf7a68d6bd51e8493dfded.jpg@w_967,h_967")
                        .apply(RequestOptions().transform(RoundedCorners(72)))
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .apply(RequestOptions.skipMemoryCacheOf(true))
                        .apply(RequestOptions.placeholderOf(com.hubiao.base.R.mipmap.ic_launcher))
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(root.context)))
                        .transition(GenericTransitionOptions.withNoTransition())
                        .into(ivContent3)
                }
            }
        }
    }
}