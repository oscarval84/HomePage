package com.lightbox.test.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.lightbox.test.R
import com.lightbox.test.databinding.ButtonLayoutBinding
import com.lightbox.test.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycle.addObserver(viewModel)
        setUpButtons()
    }

    private fun setUpButtons() {
        addButton(0.4F, getString(R.string.title_1), getString(R.string.subtitle_1))
        addButton(0.4F, getString(R.string.title_2), getString(R.string.subtitle_2))
        addButton(0.4F, getString(R.string.title_3), getString(R.string.subtitle_3))
        addButton(0.4F, getString(R.string.title_4), getString(R.string.subtitle_4))
        addButton(0.4F, getString(R.string.title_5), getString(R.string.subtitle_5))
    }

    private fun addButton(width: Float, title: String, subTitle: String) = addButton(width, title, subTitle, null)

    private fun addButton(width: Float, title: String, subTitle: String, icon: Int?) {
        var buttonBinding: ButtonLayoutBinding = ButtonLayoutBinding.inflate(layoutInflater)
        val view = buttonBinding.root
        buttonBinding.textViewButtonTitle.text = title
        buttonBinding.textViewButtonSubtitle.text = subTitle
        icon?.let {
            buttonBinding.imageViewFront.visibility = VISIBLE
            buttonBinding.imageViewFront.setImageResource(icon)
        }
        view.layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT)
        view.id = View.generateViewId()
        var set = ConstraintSet()
        set.constrainPercentWidth(view.id, width)
        binding.main.addView(view)
        set.applyTo(binding.main)
        binding.homeFlow.addView(view)
    }

}