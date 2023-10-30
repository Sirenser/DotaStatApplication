package com.example.dotastatapplication.onboarding.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.dotastatapplication.R
import com.example.dotastatapplication.databinding.FragmentOnboardingBinding
import com.example.dotastatapplication.di.getAppComponent
import com.example.dotastatapplication.onboarding.presenter.item.OnboardingItem
import com.example.dotastatapplication.utils.BaseViewModelFactory
import com.example.dotastatapplication.utils.viewBinding
import com.xwray.groupie.GroupieAdapter


class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {


    private val binding: FragmentOnboardingBinding by viewBinding()

    private val viewModel: OnboardingViewModel by viewModels {
        BaseViewModelFactory {
            getAppComponent().onboardingViewModel()
        }
    }

    private val itemAdapter by lazy {
        GroupieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent().inject(this)
        setupOnboardingItems()
        setupView()
        setupIndicators()
        setupCurrentIndicator(0)
    }

    private fun setupView() = with(binding) {
        onboardingItemsViewPager.apply {
            adapter = itemAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setupCurrentIndicator(position)
                }
            })
        }
        onboardingButtonNext.setOnClickListener {
            if (onboardingItemsViewPager.currentItem + 1 < itemAdapter.itemCount) {
                onboardingItemsViewPager.currentItem += 1
            } else {
                viewModel.onSkipOrLastItemClicked()
                //TODO выполнить навигацию в овервью
            }
        }
        iconSkip.setOnClickListener {
            viewModel.onSkipOrLastItemClicked()
            //TODO выполнить навигацию в овервью
        }
    }


    //Выполняется динамическая установка индикаторов, в зависимости от их количества
    private fun setupIndicators() {
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(12, 0, 12, 0)
        repeat(itemAdapter.itemCount) {
            val indicatorView = ImageView(context)
            indicatorView.apply {
                this.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_onboarding
                    )
                )
                this.layoutParams = layoutParams

            }
            binding.indicatorsContainer.addView(indicatorView)
        }
    }

    //Выполняется установка текущего индикатора, в зависимости от положения в списке
    private fun setupCurrentIndicator(position: Int) {
        val childCount = binding.indicatorsContainer.childCount
        repeat(childCount) { indicatorNumber ->
            val imageView = binding.indicatorsContainer.getChildAt(indicatorNumber) as ImageView
            val indicatorDrawable = if (indicatorNumber == position) {
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.indicator_onboarding_activated
                )
            } else {
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.indicator_onboarding
                )
            }
            imageView.setImageDrawable(indicatorDrawable)
        }
    }

    private fun setupOnboardingItems() {
        val onboardingItems = viewModel.setupOnboardingModels().map {
            OnboardingItem(it)
       }
        itemAdapter.update(onboardingItems)
    }
}

