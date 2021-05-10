package com.example.jetpackdemeo.thirdframework.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.renderscript.RSRuntimeException
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import jp.wasabeef.glide.transformations.internal.FastBlur
import jp.wasabeef.glide.transformations.internal.RSBlur
import java.nio.charset.Charset
import java.security.MessageDigest


class BlurTransformation(context: Context, pool: BitmapPool?, radius: Int, sampling: Int) :
    BitmapTransformation() {

    companion object{
        private const val STRING_CHARSET_NAME = "UTF-8"
        private const val ID = "com.kevin.glidetest.BlurTransformation"
        private val CHARSET = Charset.forName(STRING_CHARSET_NAME)
        private val ID_BYTES = ID.toByteArray(CHARSET)

        private const val MAX_RADIUS = 25
        private const val DEFAULT_DOWN_SAMPLING = 1
    }

    private var mContext: Context? = null
    private var mBitmapPool: BitmapPool? = null

    private var mRadius = 0
    private var mSampling = 0

    constructor (context: Context) :
            this(context, Glide.get(context).bitmapPool, MAX_RADIUS, DEFAULT_DOWN_SAMPLING)

    constructor (context: Context, pool: BitmapPool?) :
            this(context, pool, MAX_RADIUS, DEFAULT_DOWN_SAMPLING)


    constructor (context: Context, pool: BitmapPool?, radius: Int) :
            this(context, pool, radius, DEFAULT_DOWN_SAMPLING)


    constructor (context: Context, radius: Int) :
            this(context, Glide.get(context).bitmapPool, radius, DEFAULT_DOWN_SAMPLING)


    constructor(context: Context, radius: Int, sampling: Int) : this(
        context,
        Glide.get(context).bitmapPool,
        radius,
        sampling
    )

    init {
        mContext = context.applicationContext
        mBitmapPool = pool
        mRadius = radius
        mSampling = sampling
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap? {
        val width = toTransform.width
        val height = toTransform.height
        val scaledWidth = width / mSampling
        val scaledHeight = height / mSampling
        var bitmap: Bitmap? = mBitmapPool!![scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888]
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap!!)
        canvas.scale(1 / mSampling.toFloat(), 1 / mSampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(toTransform, 0f, 0f, paint)
        bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                RSBlur.blur(mContext, bitmap, mRadius)
            } catch (e: RSRuntimeException) {
                FastBlur.blur(bitmap, mRadius, true)
            }
        } else {
            FastBlur.blur(bitmap, mRadius, true)
        }

        //return BitmapResource.obtain(bitmap, mBitmapPool);
        return bitmap
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return obj is BlurTransformation
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }
}