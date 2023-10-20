package com.example.dotastatapplication.onboarding.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.dotastatapplication.R
import com.example.dotastatapplication.databinding.FragmentOnboardingBinding
import com.example.dotastatapplication.di.getAppComponent
import com.example.dotastatapplication.onboarding.presenter.item.OnboardingItem
import com.example.dotastatapplication.onboarding.presenter.models.OnboardingModel
import com.example.dotastatapplication.utils.BaseViewModelFactory
import com.example.dotastatapplication.utils.viewBinding
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.launch


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

    private fun setupView() {
        with(binding) {
            vpOnboarding.apply {
                adapter = itemAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        setupCurrentIndicator(position)
                    }
                })
            }
            onboardingButtonNext.setOnClickListener {
                if (vpOnboarding.currentItem + 1 < itemAdapter.itemCount) {
                    vpOnboarding.currentItem += 1
                }
                if (vpOnboarding.currentItem <= itemAdapter.itemCount) {
                    lifecycleScope.launch {
                        viewModel.setOnboarded()
                    }
                    //TODO выполнить навигацию в овервью
                }
            }
            iconSkip.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.setOnboarded()
                }
                //TODO выполнить навигацию в овервью
            }

        }
    }

    //Выполняется динамическая установка индикаторов, в зависимости от их количества
    private fun setupIndicators() {
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(12, 0, 12, 0)
        for (i in 0 until itemAdapter.itemCount) {
            val indicator = ImageView(context)
            indicator.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_onboarding
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorsContainer.addView(it)
            }
        }
    }

    //Выполняется установка текущего индикатора, в зависимости от положения в списке
    private fun setupCurrentIndicator(position: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_onboarding_activated
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_onboarding
                    )
                )
            }
        }
    }

    private fun setupOnboardingItems() {
        val models = setupOnboardingModels()
        val result = mutableListOf<OnboardingItem>()
        for (i in models.indices) {
            result.add(OnboardingItem(models[i]))
        }
        itemAdapter.update(result)
    }

    private fun setupOnboardingModels(): List<OnboardingModel> = listOf(
        OnboardingModel(
            onboardingImage = R.drawable.icon_dota,
            title = getString(R.string.onboaring_title_1),
            description = getString(R.string.onboaring_description_1)
        ),
        OnboardingModel(
            onboardingImage = R.drawable.icon_dota,
            title = getString(R.string.onboaring_title_2),
            description = getString(R.string.onboaring_description_2)
        ),
        OnboardingModel(
            onboardingImage = R.drawable.icon_dota,
            title = getString(R.string.onboaring_title_3),
            description = getString(R.string.onboaring_description_3)
        ),
        OnboardingModel(
            onboardingImage = R.drawable.icon_dota,
            title = getString(R.string.onboaring_title_4),
            description = getString(R.string.onboaring_description_4)
        ),
    )

}