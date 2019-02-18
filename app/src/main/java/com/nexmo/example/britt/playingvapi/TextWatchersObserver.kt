package com.nexmo.example.britt.playingvapi

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher

class TextWatchersObserver(
    viewModel: MainActivity.CallRequestUiModel,
    val tilToNum: TextInputLayout,
    val tilFromNum: TextInputLayout
) : LifecycleObserver {

    val twToPhone = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            viewModel.toPhone = (s?.toString()) ?: ""
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    }

    val twFromPhone = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            viewModel.fromPhone = (s?.toString()) ?: ""
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        tilToNum.editText?.addTextChangedListener(twToPhone)
        tilFromNum.editText?.addTextChangedListener(twFromPhone)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        tilToNum.editText?.removeTextChangedListener(twToPhone)
        tilFromNum.editText?.removeTextChangedListener(twFromPhone)

    }
}