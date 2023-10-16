package com.example.dotastatapplication.onboarding.presenter.item

import android.view.View
import coil.load
import com.example.dotastatapplication.R
import com.example.dotastatapplication.databinding.ItemOnboardingBinding
import com.example.dotastatapplication.onboarding.presenter.models.OnboardingModel
import com.xwray.groupie.viewbinding.BindableItem

class OnboardingItem(private val onboardingModel: OnboardingModel) :
    BindableItem<ItemOnboardingBinding>() {
    override fun bind(binding: ItemOnboardingBinding, position: Int) {
        binding.imageOnboardingItem.load(onboardingModel.onboardingImage)
        binding.textTitle.text = onboardingModel.title
        binding.textDescription.text = onboardingModel.description
    }

    override fun getLayout(): Int = R.layout.item_onboarding

    override fun initializeViewBinding(view: View): ItemOnboardingBinding =
        ItemOnboardingBinding.bind(view)
}