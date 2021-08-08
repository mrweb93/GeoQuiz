package io.github.mrweb93.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId:Int, val answer: Boolean)