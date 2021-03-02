package com.example.jetpackdemeo.handler

import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityHandlertestLayoutBinding

class HandlerTestActivity : AppCompatActivity() {

    companion object {
        val TAG = HandlerTestActivity::class.simpleName
        var barrierToken = 0
    }

    var viewBindings: ActivityHandlertestLayoutBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    private val handler = TestHandler()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityHandlertestLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        initListener()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListener() {
        viewBindings?.apply {
            btnMessage.setOnClickListener {
                handler.sendMessage(Message.obtain().apply { obj = "message" })
            }

            btnBarrier.setOnClickListener {
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 1" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 2" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 3" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 4" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 5" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 6" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 7" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 8" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 9" }, 1000L)
                handler.sendMessageDelayed(Message.obtain().apply { obj = "synchronous 10" }, 1000L)

                handler.sendMessageDelayed(Message.obtain().apply {
                    obj = "asynchronous 1"
                    isAsynchronous = true
                }, 1000L)

                handler.sendMessageDelayed(Message.obtain().apply {
                    obj = "asynchronous 2"
                    isAsynchronous = true
                    what = 10086
                }, 2000L)


                val queue = handler.looper.queue

                val clz = queue.javaClass

                clz.declaredMethods.forEach {
                    Log.e(TAG, "method  = $it")
                    if ("postSyncBarrier" == it.name) {
                        it.isAccessible = true

                        barrierToken = it.invoke(queue) as Int

                        Log.e(TAG, "postSyncBarrier(), barrierToken = $barrierToken")
                    }
                }
            }

            btnIdlehandler.setOnClickListener {
                handler.looper.queue.addIdleHandler(MessageQueue.IdleHandler {
                    Log.e(TAG, "IdleHandler queueIdle()")
                    false
                })
            }

            btnOtherThread.setOnClickListener {
                Thread {
                    Log.e(TAG, "thread ${Thread.currentThread().name} start")

                    var flag = true

                    if (Looper.myLooper() == null) {
                        Looper.prepare()
                    }

                    val handler = object : Handler() {
                        override fun handleMessage(msg: Message) {
                            super.handleMessage(msg)

                            Log.e(
                                TAG,
                                "handler in ${Thread.currentThread().name}, ${msg.obj}, ${msg.what}"
                            )

                            if (msg.what == 123) {
                                flag = false

                                Log.e(
                                    TAG,
                                    "handler in ${Thread.currentThread().name}, msg.what = ${msg.what}, flag = $flag"
                                )

                                looper.quit()
                            }
                        }
                    }

                    handler.sendMessage(Message.obtain().apply { obj = "message1 in other thread" })
                    handler.sendMessage(Message.obtain().apply { obj = "message2 in other thread" })
                    handler.sendMessageDelayed(Message.obtain().apply {
                        obj = "message3 in other thread"
                        what = 123
                    }, 5000L)

                    Looper.loop()

                    Log.e(TAG, "thread ${Thread.currentThread().name} end")
                }.start()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    class TestHandler() : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            Log.e(TAG, "${msg.obj}, ${msg.what}")

            if (msg.what == 10086) {
                val queue = looper.queue

                val clz = queue.javaClass

                var token = 0
                clz.declaredFields.forEach{
                    if ("mNextBarrierToken" == it.name) {
                        it.isAccessible = true
                        token = it.get(queue) as Int

                        Log.e(TAG, "field = ${it.name}, token = $token")
                    }
                }

                clz.declaredMethods.forEach {
                    Log.e(TAG, "method  = $it")
                    if ("removeSyncBarrier" == it.name) {
                        it.isAccessible = true

                        it.invoke(queue, barrierToken)

                        Log.e(TAG, "invoke()")
                    }
                }
            }
        }
    }
}