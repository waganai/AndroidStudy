package com.example.jetpackdemeo.view.recyclerview

import android.content.Context
import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.jetpackdemeo.databinding.ActivityRecyclerviewLayoutBinding
import com.example.jetpackdemeo.databinding.ItemGlideLayoutBinding
import com.example.jetpackdemeo.thirdframework.glide.BlurTransformation

class GlideRecyclerViewActivity : AppCompatActivity() {

    private var viewBinding: ActivityRecyclerviewLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityRecyclerviewLayoutBinding.inflate(layoutInflater)

        setContentView(viewBinding?.root)

        init()
    }

    fun init() {
        val glideAdapter = GlideListAdapter(this)
        val list1 = listOf(
            "https://b0.bdstatic.com/comment/AbD06ZlrNmmzmXl7Cw5uFA957ac5114bcf7a68d6bd51e8493dfded.jpg@w_967,h_967",
            "https://pics4.baidu.com/feed/50da81cb39dbb6fd555034142b07651f972b37a7.png?token=15bc3f132fe9e56afa75cb516a427b3d&s=31A0D65944504A6558208DD4030090B3",
            "https://pics7.baidu.com/feed/f3d3572c11dfa9ecff3ca7de40f33904908fc190.png?token=e30b89e14518bc96bc758c08346ccc13&s=9DA6FE114B305C965DF83CD8030080B5",
            "https://pics1.baidu.com/feed/c83d70cf3bc79f3de4406afb9d890e16738b2903.jpeg?token=4427538e38c5b850e6de5352ffac1c1f&s=18F30A9E049075D0423D07C90300D0BC",
            "https://pics0.baidu.com/feed/ca1349540923dd54a82102179e2f76d99c824829.jpeg?token=6dfa05def404f6f0d1d8173b225a2e8b&s=7AB616C160132BD248396D17030050C3",
            "https://pics1.baidu.com/feed/cdbf6c81800a19d812315b977cdc478ca61e4624.jpeg?token=c7de1fc8aaf3018648c3eb67e746375b&s=129637C1664257475831B99803008095",
            "https://pics4.baidu.com/feed/f31fbe096b63f624d9ab8ee3c9622eff1b4ca30e.jpeg?token=eb971881fc4c3638a358fe9e442b33f8&s=EE22C54F58C27E590A08D5E00300A011",
            "https://pics6.baidu.com/feed/8b13632762d0f70343d0e7aa47dc943a2697c510.jpeg?token=87b904d7c43ff603fbc268437c8b39ce&s=73A8BC45684205435801E5AA0300704B",
            "https://pics0.baidu.com/feed/d788d43f8794a4c221e8b07e40d2ded2ac6e394d.jpeg?token=9272f5d027a6e660ce95c4ca3f30a386&s=AE9A06CB4A5A60D6D239019803001093",
            "https://pics0.baidu.com/feed/279759ee3d6d55fb4eba65d623048a4d21a4ddf4.jpeg?token=1284f491c0fde14bc5c34f74e063548b&s=59838C57124B7F5DCD35C91503007043",
            "https://pics7.baidu.com/feed/11385343fbf2b2111ffe72399ba6a03f0dd78ea7.jpeg?token=42d0c20a5a5328e0d9eb848cf77ff155&s=E47839C67ADA61D447805CB803000010",
            "https://pics7.baidu.com/feed/18d8bc3eb13533fa6b6c14a9e7f5381840345b09.jpeg?token=a0f2ff056bfd4aa5c3dbe5373ef1eaba&s=BE9055815A5213D6E8A1F88B0300B091",
            "https://pics7.baidu.com/feed/e61190ef76c6a7ef3652885f33e71e56f3de667c.jpeg?token=b872650b0663b6c574032952a2150955",
            "https://pics6.baidu.com/feed/63d9f2d3572c11df91adf844ae3ad3d7f703c27a.jpeg?token=a13f415ae759210ac21e7af69d582dc9",
            "https://pics6.baidu.com/feed/54fbb2fb43166d2296d5c013883eb8f09152d2b9.jpeg?token=4957411eee3b1cf160386342de929455",
            "https://pics6.baidu.com/feed/5ab5c9ea15ce36d378e79864f7ee8b80e850b1ac.jpeg?token=d050596b3b5dbbecb0c131283fc9cd8e",
            "https://pics2.baidu.com/feed/9358d109b3de9c82804814efa19c310d19d84342.jpeg?token=75a136b22c065e891550780d65bb509b",
            "https://t11.baidu.com/it/u=1266903163,2357271426&fm=173&app=25&f=JPEG?w=640&h=360&s=F3921E891A2164940EF578D5030080B2",
            "https://t12.baidu.com/it/u=3892640861,2875757065&fm=173&app=25&f=JPEG?w=640&h=484&s=01A3D25D545375DA1A9D14210300E040",
            "https://t10.baidu.com/it/u=671054607,3918504980&fm=173&app=25&f=JPEG?w=640&h=452&s=E6B054CB40EB7D1BDEB1965A030040D7",
            "https://t10.baidu.com/it/u=4041360744,3616816313&fm=173&app=25&f=JPEG?w=640&h=651&s=22A2DC01DB3A7C9C8BBD74C20300E010",
            "https://t10.baidu.com/it/u=3018700284,4104285643&fm=173&app=25&f=JPEG?w=640&h=360&s=A3A267A4087DCE9A96BF055D0300C0BA",
            "https://t10.baidu.com/it/u=2460689157,598940183&fm=173&app=25&f=JPEG?w=640&h=360&s=C3B33FC58E347D9611A440980300A0C1",
            "https://t10.baidu.com/it/u=622062426,2699878725&fm=173&app=25&f=JPEG?w=640&h=361&s=8EC29E455A043B7C4CB01105030080C3",
            "https://t11.baidu.com/it/u=3691569598,391517964&fm=173&app=25&f=JPEG?w=639&h=361&s=FAE0D54ED8F0E59ED839C5F803004013",
            "https://pics4.baidu.com/feed/d0c8a786c9177f3e0e9443bc7d7887c09e3d5619.png?token=7bc072478cbbd1c45a0bb3d8f8380e6e&s=A823CA141A724C92068234D8030010B8",
            "https://pics6.baidu.com/feed/bba1cd11728b471006b98a69c9797ffafd03233b.png?token=259b95ca6ffe802988d21de25626ecf9&s=9CE7FC044A236C19DAB70ED303008094",
            "https://pics0.baidu.com/feed/fc1f4134970a304e17282a71d87f1b81c8175c75.png?token=e31dc96bcb9b3a0f46e80eec01b52ff2&s=09B04D954F66441102D5DCC8030030B4",
            "https://pics1.baidu.com/feed/0b46f21fbe096b63ca4e28c007843b43eaf8ac0d.png?token=3d8d0e6d3e4163570e68f1ec800bb995&s=39B3069A8971579C05C0A7C1030030BE",
            "https://pics3.baidu.com/feed/b90e7bec54e736d1a7e98a9091e7f3c5d76269ea.png?token=03998a4d256d0113cbe44577dc11e0a9&s=25B6539540D370624EA4149D030010E3",
            "https://pics1.baidu.com/feed/a08b87d6277f9e2f5a1dc5850e875523b999f356.png?token=af0a41763700529925453d8ca698a46e&s=2DA4C610021048695A807DC5030030F0",
            "https://pics0.baidu.com/feed/48540923dd54564e71bcbea5c9fa4b85d0584f79.jpeg?token=a256a2b38ad65bdeeb99a431f0afc2af&s=80A5DA151F71408C59B185F203005035"
        )

        val list2 = MutableList(list1.size) {
            ""
        }

        for (i in 0 until list2.size) {
            list2[i] = list1[list1.size - 1 - i]
        }

        glideAdapter.mContents = list1

        viewBinding?.rcList?.adapter = glideAdapter
        viewBinding?.rcList?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewBinding?.btnRefresh?.setOnClickListener {
            glideAdapter.mContents = list2
            glideAdapter.notifyDataSetChanged()
        }

    }

    class GlideListAdapter(val context: Context) :
        RecyclerView.Adapter<GlideListAdapter.GlideViewHolder>() {

        var mContents: List<String>? = null
        private val mInflater = LayoutInflater.from(context)

        class GlideViewHolder(val viewBinding: ItemGlideLayoutBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
            fun load(url: String) {
                Glide.with(viewBinding.ivGlide)
                    .load(url)
//                    .apply(RequestOptions().circleCrop())
                    .apply(RequestOptions().transform(RoundedCorners(24)))
//                    .apply(RequestOptions().skipMemoryCache(true))
//                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(viewBinding.ivGlide)
            }

            fun loadWithBlur(url: String) {
                Glide.with(viewBinding.ivGlide)
                    .load(url)
                    .apply(RequestOptions().transform(RoundedCorners(24)))
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(viewBinding.ivGlide.context)))
                    .into(viewBinding.ivGlide)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlideViewHolder {
            return GlideViewHolder(ItemGlideLayoutBinding.inflate(mInflater, parent, false))
        }

        override fun onBindViewHolder(holder: GlideViewHolder, position: Int) {
            if (position % 2 == 0) {
                holder.load(mContents?.get(position) ?: "")
            } else {
                holder.loadWithBlur(mContents?.get(position) ?: "")
            }
        }

        override fun getItemCount(): Int {
            return mContents?.size ?: 0
        }
    }
}