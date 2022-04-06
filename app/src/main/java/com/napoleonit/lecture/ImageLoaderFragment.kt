package com.napoleonit.lecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.napoleonit.lecture.databinding.FragmentImageLoaderBinding

class ImageLoaderFragment: Fragment() {

    private lateinit var binding:FragmentImageLoaderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImageLoaderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoad.setOnClickListener {
            val url = binding.etUrl.text.toString()
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.ivImage)

        }

        binding.ivImage.setOnClickListener {
            val url = binding.etUrl.text.toString()
            val action = ImageLoaderFragmentDirections.actionImagesFragmentToFullImageFragment(url)
            findNavController().navigate(action)
        }
    }

}