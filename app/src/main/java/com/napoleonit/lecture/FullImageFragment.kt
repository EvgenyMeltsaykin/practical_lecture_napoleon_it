package com.napoleonit.lecture

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.napoleonit.lecture.databinding.FragmentFullImageBinding


class FullImageFragment : BottomSheetDialogFragment() {

    private val args: FullImageFragmentArgs by navArgs()
    private lateinit var binding: FragmentFullImageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFullImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .asBitmap()
            .load(args.url)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                    binding.ivImage.setImage(ImageSource.bitmap(resource))
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        binding.ivImage.resetScaleAndCenter()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { bsDialog ->
            val d = bsDialog as BottomSheetDialog
            val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)

            bottomSheet?.let {
                val layoutParams = bottomSheet.layoutParams
                layoutParams.height = resources.displayMetrics.heightPixels
                bottomSheet.layoutParams = layoutParams
                BottomSheetBehavior.from(it).apply {
                    peekHeight = resources.displayMetrics.heightPixels / 2
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
        return dialog

    }

}