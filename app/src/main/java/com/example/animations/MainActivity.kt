package com.example.animations


import android.animation.*
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.animations.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val animationSet = AnimatorSet()
        animationSet.playSequentially(
            translateYUsingValueAnimatorUsingCode(),
            translateYUsingValueAnimatorWithXml(),
            translateXUsingObjectAnimator(),
            fadeAnimation(), rotationY(), changePivotXofView(), changePivotYofView()
        )
        animationSet.start()
    }

    private fun changePivotXofView(): ValueAnimator {
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.pivotx
        ) as ObjectAnimator
        objectAnimator.target = binding.animText
        return objectAnimator
    }

    private fun changePivotYofView(): ValueAnimator {
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.pivoty
        ) as ObjectAnimator
        objectAnimator.target = binding.animText
        return objectAnimator
    }

    private fun rotationY(): ValueAnimator {
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.rotate
        ) as ObjectAnimator
        objectAnimator.repeatMode = ValueAnimator.REVERSE;
        objectAnimator.repeatCount = 1;
        objectAnimator.target = binding.animText
        return objectAnimator
    }

    private fun scaleXAnimation(): ValueAnimator {
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.scale_x
        ) as ObjectAnimator
        objectAnimator.repeatMode = ValueAnimator.REVERSE;
        objectAnimator.repeatCount = 1
        objectAnimator.target = binding.animText
        return objectAnimator
    }

    private fun scaleYAnimation(): ValueAnimator {
        //increase text size along y -axis
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.scale_y
        ) as ObjectAnimator
        objectAnimator.repeatMode = ValueAnimator.REVERSE;
        objectAnimator.repeatCount = 1;
        objectAnimator.target = binding.animText
        return objectAnimator
    }

    private fun fadeAnimation(): ValueAnimator {
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.fade
        ) as ObjectAnimator
        objectAnimator.target = binding.animText
        return objectAnimator
    }
    /*
       Interpolator
       1) Anticipate-- deaccelerate reverse before anim start.
       2)AnticipateOvershoot --  deaccelerate reverse before anim start+deacclerate reverse after anim completion.
       3)over shoot- deacclerate reverse after anim completion
       4)Bounce - bounce after anim completion.
       5) cycle - complete anim in given and opposite direction and come back to original.
       6) linear -normally perform animation in given direction.
       7) Path --- ???
     */

    private fun translateYUsingValueAnimatorUsingCode(): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(0f, 500f)
        valueAnimator.interpolator =
            AccelerateDecelerateInterpolator() // increase the speed first and then decrease

        valueAnimator.duration = 2000
        valueAnimator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            binding.animText.setTranslationY(progress)
            // no need to use invalidate() as it is already present in             //the text view.
        }
        return valueAnimator
    }

    private fun translateXUsingObjectAnimator(): ValueAnimator {
        val objectAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.translate_object_animator
        ) as ObjectAnimator
        objectAnimator.repeatMode = ObjectAnimator.REVERSE
        objectAnimator.repeatCount = 4
        objectAnimator.target = binding.animText
        return objectAnimator
    }

    private fun translateYUsingValueAnimatorWithXml(): ValueAnimator {
        val valueAnimator = AnimatorInflater.loadAnimator(
            this, R.animator.translate_value_animator
        ) as ValueAnimator

        valueAnimator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Float
            binding.animText.translationY = progress
        }
        return valueAnimator
    }
}