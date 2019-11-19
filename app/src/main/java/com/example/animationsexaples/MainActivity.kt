package com.example.animationsexaples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import android.net.Uri
import android.annotation.SuppressLint
import android.graphics.drawable.Animatable
import com.facebook.drawee.controller.ControllerListener
import androidx.annotation.Nullable
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
import android.view.View


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        controllerListener.onClick()
    }

    var controllerListener: SimpleAction =
        object : SimpleAction {
            override fun onFailure(id: String?, throwable: Throwable?) {
            }

            override fun onRelease(id: String?) {
            }

            override fun onSubmit(id: String?, callerContext: Any?) {
            }

            override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {
            }

            override fun onIntermediateImageFailed(id: String?, throwable: Throwable?) {
            }

            override fun onClick() {
                anim.start()
            }

            lateinit var anim: Animatable
            override fun onFinalImageSet(
                id: String?,
                @Nullable imageInfo: ImageInfo?,
                @Nullable anim: Animatable?
            ) {
                this.anim = anim!!
            }

        }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        var uri = Uri.parse("res:/${R.drawable.anim}")


        var path = "https://res.cloudinary.com/demo/image/upload/fl_awebp/cell_animation.webp"
        var controller =
            Fresco.newDraweeControllerBuilder().setUri(uri)
                .setControllerListener(controllerListener).build()
        var controller2 =
            Fresco.newDraweeControllerBuilder().setUri(path).setAutoPlayAnimations(true).build()


        var draweeee: SimpleDraweeView = findViewById<SimpleDraweeView>(R.id.mydrawee)
        draweeee.controller = controller
        draweeee.setOnClickListener(this)

        var draweeee2: SimpleDraweeView = findViewById<SimpleDraweeView>(R.id.mydrawee_web)
        draweeee2.controller = controller2

    }
}

interface SimpleAction : ControllerListener<ImageInfo> {
    fun onClick()
}