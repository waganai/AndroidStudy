package com.example.jetpackdemeo.view.recyclerview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackdemeo.databinding.ActivityRecyclerviewLayoutBinding
import com.example.jetpackdemeo.databinding.ItemDiffLayoutBinding

class DiffRecyclerViewActivity2 : AppCompatActivity() {

    companion object {
        private val TAG = DiffRecyclerViewActivity2::class.simpleName
    }

    private val viewBinding by lazy {
        ActivityRecyclerviewLayoutBinding.inflate(layoutInflater)
    }

    private val mDataList = mutableListOf(
        DiffDataClass(
            "伊蕾娜",
            1,
            "屑魔女，贪财",
            "https://c-ssl.duitang.com/uploads/blog/202011/10/20201110002652_37054.jpg"
        ),
        DiffDataClass(
            "沙耶",
            2,
            "痴女",
            "https://c-ssl.duitang.com/uploads/blog/202011/08/20201108181920_b60f6.thumb.1000_0.jpg"
        ),
        DiffDataClass(
            "芙兰", 3, "伊蕾娜的老师，伊蕾娜老妈的学生",
            "https://c-ssl.duitang.com/uploads/blog/202011/10/20201110002651_f7954.thumb.1000_0.jpg"
        ),
        DiffDataClass(
            "暗夜魔女",
            4,
            "伊蕾娜老妈的学生，沙耶的老师",
            "http://p8.itc.cn/images01/20201205/046ac5023058490ba714d211278b2543.png"
        ),
        DiffDataClass(
            "魔女妮可",
            5,
            "伊蕾娜老妈",
            "http://tiebapic.baidu.com/forum/w%3D580/sign=8e976a2d322dd42a5f0901a3333a5b2f/1b67247e9e2f070871851544fe24b899a801f220.jpg"
        ),
        DiffDataClass(
            "沙耶妹妹", 6, "沙耶的妹妹，痴女",
            "https://c-ssl.duitang.com/uploads/blog/202101/22/20210122165153_f91f6.thumb.1000_0.jpg"
        ),
        DiffDataClass(
            "薰衣魔女",
            7,
            "穿越过去被玩伴背刺",
            "http://tiebapic.baidu.com/forum/w%3D580/sign=13e11382958ba61edfeec827713597cc/daab1dc7a7efce1b3fc2dd82b851f3deb58f6556.jpg"
        ),
        DiffDataClass(
            "公主",
            8,
            "屠龙",
            "https://c-ssl.duitang.com/uploads/blog/202105/05/20210505204440_11877.thumb.1000_0.jpg"
        ),
        DiffDataClass(
            "瑟蕾娜",
            9,
            "背刺",
            "https://c-ssl.duitang.com/uploads/blog/202012/04/20201204221632_6c110.thumb.1000_0.jpg"
        ),
    )

    private val mDataList2 = MutableList(mDataList.size) {
        DiffDataClass(
            "伊蕾娜",
            1,
            "屑魔女，贪财",
            "https://c-ssl.duitang.com/uploads/blog/202011/10/20201110002652_37054.jpg"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        init()
    }

    fun init() {
        viewBinding.apply {
            rcList.apply {
                layoutManager = LinearLayoutManager(
                    this@DiffRecyclerViewActivity2,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = DiffAdapter2(this@DiffRecyclerViewActivity2)
                (rcList.adapter as DiffAdapter2).mDataList = (mDataList.toList())

//                isNestedScrollingEnabled = false
//                setHasFixedSize(true)
//                isFocusable = false
            }

            btnRefresh.setOnClickListener {
                mDataList.forEachIndexed { index, diffDataClass ->
                    mDataList2[mDataList2.size - 1 - index] = diffDataClass
                }
                mDataList2.forEachIndexed { index, diffDataClass ->
                    mDataList[index] = diffDataClass
                }

                (rcList.adapter as DiffAdapter2).mDataList = (mDataList2.toList())
                rcList.adapter?.notifyDataSetChanged()
            }
        }
    }

    data class DiffDataClass(val name: String, val id: Int, val content: String, val url: String)

    class DiffAdapter2(val context: Context) :
        RecyclerView.Adapter<DiffAdapter2.DiffViewHolder>() {

        private val mLayoutInflater = LayoutInflater.from(context)
         var mDataList = listOf<DiffDataClass>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffViewHolder {
            return DiffViewHolder(ItemDiffLayoutBinding.inflate(mLayoutInflater, parent, false))
        }

        override fun onBindViewHolder(holder: DiffViewHolder, position: Int) {
            holder.bindData(mDataList[position])
        }

        override fun onBindViewHolder(
            holder: DiffViewHolder,
            position: Int,
            payloads: MutableList<Any>
        ) {
            if (payloads.size > 0) {
                val payload = payloads[0]
                Log.e(TAG, "onBindViewHolder() payload = $payload")

                if (payload is Int) {
                    if (payload == 0x000) {
                        holder.bindData(mDataList[position])
                        return
                    }

                    if (payload and 0x011 == 0x000) {
                        holder.changeName(mDataList[position])

                    }
                    if (payload and 0x101 == 0x000) {
                        holder.changeUrl(mDataList[position])

                    }
                    if (payload and 0x110 == 0x000) {
                        holder.changeContent(mDataList[position])
                    }

                    return
                }
            }

            Log.e(TAG, "onBindViewHolder() payloads = $payloads")

            holder.bindData(mDataList[position])
        }

        override fun getItemCount(): Int {
            return mDataList.size
        }

        class DiffViewHolder(val viewBinding: ItemDiffLayoutBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {

            fun bindData(data: DiffDataClass) {
                viewBinding.apply {
                    ivContent.apply {
                        Glide.with(context)
                            .load(data.url)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
                            .into(this)
                    }

                    tvName.text = data.name
                    tvContent.text = data.content
                }
            }

            fun changeName(data: DiffDataClass) {
                viewBinding.apply {
                    tvName.text = data.name
                }
            }

            fun changeUrl(data: DiffDataClass) {
                viewBinding.apply {
                    ivContent.apply {
                        Glide.with(context)
                            .load(data.url)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
                            .into(this)
                    }
                }
            }

            fun changeContent(data: DiffDataClass) {
                viewBinding.apply {
                    tvContent.text = data.content
                }
            }
        }
    }
}