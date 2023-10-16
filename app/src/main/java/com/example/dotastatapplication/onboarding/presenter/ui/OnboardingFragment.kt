package com.example.dotastatapplication.onboarding.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.dotastatapplication.R
import com.example.dotastatapplication.databinding.FragmentOnboardingBinding
import com.example.dotastatapplication.di.getAppComponent
import com.example.dotastatapplication.onboarding.presenter.item.OnboardingItem
import com.example.dotastatapplication.onboarding.presenter.models.OnboardingModel
import com.example.dotastatapplication.utils.viewBinding
import com.xwray.groupie.GroupieAdapter


class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {


    private val binding: FragmentOnboardingBinding by viewBinding()

    private val adapter by lazy {
        GroupieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent().inject(this)
        setupView()
        setupIndicators()
        setupCurrentIndicator(0)
    }

    private fun setupView() {
        with(binding) {
            adapter.addAll(setupOnboardingItems(setupOnboardingModels()))
            vpOnboarding.adapter = adapter
            vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setupCurrentIndicator(position)
                }
            })
            onboardingButtonNext.setOnClickListener {
                if (vpOnboarding.currentItem + 1 < adapter.itemCount) {
                    vpOnboarding.currentItem += 1
                } else if (vpOnboarding.currentItem == adapter.itemCount) {
                    //TODO выполнить навигацию в овервью + сохранить информацию о прохождении онбординга
                }
            }
            iconSkip.setOnClickListener {
                //TODO выполнить навигацию в овервью + сохранить информацию о прохождении онбординга
            }

        }
    }

    //Выполняется динамическая установка индикаторов, в зависимости от их количества
    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)

        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(12, 0, 12, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.let { indicator ->
                indicator.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_onboarding
                    )
                )
                indicator.layoutParams = layoutParams
                binding.indicatorsContainer.addView(indicator)
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

    private fun setupOnboardingItems(models: List<OnboardingModel>): List<OnboardingItem> {
        val result = mutableListOf<OnboardingItem>()
        for (i in models.indices) {
            result.add(OnboardingItem(models[i]))
        }
        return result
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