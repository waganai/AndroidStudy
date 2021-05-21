package com.example.jetpackdemeo.view.customanimation

import android.graphics.Camera//注意使用的是graphics里的而不是hardware里的
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * An animation that rotates the view on the Y axis between two specified angles.
 * This animation also adds a translation on the Z axis (depth) to improve the effect.
 */
open class Rotate3dAnimation() : Animation() {
    private var mFromDegrees: Float = 0F
    private var mToDegrees: Float = 0F
    private var mCenterX: Float = 0F
    private var mCenterY: Float = 0F
    private var mDepthZ: Float = 0F
    private var mReverse: Boolean = false
    private val mCamera by lazy {
        Camera()
    }

    /**
     * Creates a new 3D rotation on the Y axis. The rotation is defined by its
     * start angle and its end angle. Both angles are in degrees. The rotation
     * is performed around a center point on the 2D space, definied by a pair
     * of X and Y coordinates, called centerX and centerY. When the animation
     * starts, a translation on the Z axis (depth) is performed. The length
     * of the translation can be specified, as well as whether the translation
     * should be reversed in time.
     *
     * @param fromDegrees the start angle of the 3D rotation
     * @param toDegrees the end angle of the 3D rotation
     * @param centerX the X center of the 3D rotation
     * @param centerY the Y center of the 3D rotation
     * @param reverse true if the translation should be reversed, false otherwise
     */
    constructor(
        fromDegrees: Float,
        toDegrees: Float,
        centerX: Float,
        centerY: Float,
        depthZ: Float,
        reverse: Boolean
    ) : this() {
        mFromDegrees = fromDegrees
        mToDegrees = toDegrees
        mCenterX = centerX
        mCenterY = centerY
        mDepthZ = depthZ
        mReverse = reverse
    }

    /**
     *
     * @param interpolatedTime 动画时间点，类似百分比
     * @param t
     */
    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val fromDegrees = mFromDegrees
        val degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime)

        val centerX = mCenterX
        val centerY = mCenterY
        val camera = mCamera

        val matrix = t.matrix

        camera.save()
        if (mReverse) {//远离
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime)
        } else {//靠近
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
        }
        camera.rotateY(degrees)
        camera.getMatrix(matrix)
        camera.restore()

        //移动旋转中心到布局中心
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
    }
}