package com.ictech.mcvideo.adapteritem

import com.ictech.mcvideo.model.Faq
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ictech.mcvideo.R
import com.ictech.mcvideo.databinding.ItemFaqsBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class FaqsItem(val faq: Faq) :
    AbstractBindingItem<ItemFaqsBinding>() {

    override val type: Int
        get() = R.id.fastadapter_faqs_item_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemFaqsBinding {
        return ItemFaqsBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemFaqsBinding, payloads: List<Any>) {
        with(binding) {
            tvTitle.text = faq.title
            tvDescription.text = faq.description
        }

    }

    override fun unbindView(binding: ItemFaqsBinding) {
        with(binding) {
            tvTitle.text = null
            tvDescription.text = null
        }
    }


}