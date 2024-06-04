package com.kalex.melichallenge.commons.epoxy

import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.button.MaterialButton
import com.kalex.melichallenge.R
import com.kalex.melichallenge.commons.KotlinEpoxyHolder

@EpoxyModelClass()
abstract class EpoxySearchResultItemModel : EpoxyModelWithHolder<EpoxySearchResultItemModel.Holder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.search_result_item
    }

    @EpoxyAttribute
    lateinit var titleText: String

    @EpoxyAttribute
    lateinit var priceText: String

    @EpoxyAttribute
    lateinit var imgUrl: String
    @EpoxyAttribute
    lateinit var onClickListener: () -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.textView.text = titleText
        holder.imageView.load(imgUrl)
        holder.textPrice.text = priceText
        holder.detailButtonView.setOnClickListener{
            onClickListener.invoke()
        }
        (holder.parentLayout.layoutParams as ViewGroup.MarginLayoutParams)

    }

    inner class Holder() : KotlinEpoxyHolder() {
        val textView by bind<AppCompatTextView>(R.id.textTitle)

        val imageView by bind<ImageView>(R.id.myImageView)
        val detailButtonView by bind<MaterialButton>(R.id.detailButton)
        val textPrice by bind<AppCompatTextView>(R.id.textPrice)
        val parentLayout by bind<ConstraintLayout>(R.id.parentlayout)
    }
}
