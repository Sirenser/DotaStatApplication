package com.example.dotastatapplication.authorization.presenter.item

import android.view.View
import coil.load
import com.example.dotastatapplication.R
import com.example.dotastatapplication.authorization.presenter.models.AccountInfoUI
import com.example.dotastatapplication.databinding.ItemAccountSearchBinding
import com.example.dotastatapplication.utils.OnClickedListener
import com.xwray.groupie.viewbinding.BindableItem


class AccountSearchItem(private val accountInfo: AccountInfoUI, private val listener: OnClickedListener) :
    BindableItem<ItemAccountSearchBinding>() {
    override fun bind(binding: ItemAccountSearchBinding, position: Int) {
        binding.itemSearchName.text = accountInfo.personName
        binding.itemSearchAvatar.load(accountInfo.avatarFull)
        binding.root.setOnClickListener { listener.click() }
    }

    override fun getLayout(): Int = R.layout.item_account_search

    override fun initializeViewBinding(view: View): ItemAccountSearchBinding =
        ItemAccountSearchBinding.bind(view)
}