package com.github.johnnysc.jokeapp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.core.presentation.CommonViewModel

/**
 * @author Asatryan on 20.06.2021
 **/
class FavoriteDataView : LinearLayout {

    private lateinit var checkBox: CheckBox
    private lateinit var textView: CorrectTextView
    private lateinit var changeButton: CorrectImageButton
    private lateinit var actionButton: CorrectButton
    private lateinit var progress: CorrectProgress

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    //endregion
    private fun init(attrs: AttributeSet) {
        orientation = VERTICAL
        (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view, this, true)
        checkBox = getChildAt(0) as CheckBox
        val linear = getChildAt(1) as LinearLayout
        textView = linear.findViewById(R.id.textView)
        changeButton = linear.findViewById(R.id.changeButton)
        progress = getChildAt(2) as CorrectProgress
        actionButton = getChildAt(3) as CorrectButton

        context.theme.obtainStyledAttributes(attrs, R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtonText = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText = getString(R.styleable.FavoriteDataView_chekBoxText)
                checkBox.text = checkBoxText
                actionButton.text = actionButtonText
            } finally {
                recycle()
            }
        }
    }

    fun linkWith(commonViewModel: CommonViewModel) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            commonViewModel.chooseFavorites(isChecked)
        }
        changeButton.setOnClickListener {
            commonViewModel.changeItemStatus()
        }
        actionButton.setOnClickListener {
            commonViewModel.getItem()
        }
    }

    fun show(state: State) = state.show(progress, actionButton, textView, changeButton)
}