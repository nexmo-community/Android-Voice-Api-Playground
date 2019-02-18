package com.nexmo.example.britt.playingvapi

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_talk.view.*
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: CallRequestUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(CallRequestUiModel::class.java)
        lifecycle.addObserver(TextWatchersObserver(viewModel, tilToNum, tilFromNum))


        tilFromNum.editText?.setText(viewModel.fromPhone)
        tilToNum.editText?.setText(viewModel.toPhone)

        list.adapter = NccoAdapter(viewModel.actions)
        list.layoutManager = LinearLayoutManager(this)

        fabMakeCall.setOnClickListener(this)
        btnAddTalk.setOnClickListener(this)
        btnAddStream.setOnClickListener(this)
        btnAddInput.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            fabMakeCall -> executeMakeCall(baseContext, viewModel)
            btnAddTalk -> addTalkAction()
            btnAddStream -> addStreamAction()
            btnAddInput -> addInputAction()
        }
    }

    fun addTalkAction() {
        AlertDialog.Builder(this).apply {
            val v = layoutInflater.inflate(R.layout.dialog_talk, null)
            val onClickListener: (DialogInterface, Int) -> Unit = { _, _ ->
                val talkAction = Talk(
                    text = v.etText.text.toString(),
                    voiceName = v.spVoices.selectedItem.toString()
                )

                addAction(talkAction)
            }

            setView(v)
            setPositiveButton(android.R.string.ok, onClickListener)
            setNegativeButton(android.R.string.cancel) { dialog, id -> dialog.cancel() }

            show()
        }
    }

    fun addStreamAction() {
        var tuneNames: List<String> = Tunes.values().map { it.name }
        // Create SimpleAdapter object.
        val dialogAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, tuneNames)

        AlertDialog.Builder(this).apply {
            setTitle("Select audio stream")
            setAdapter(dialogAdapter) { _, which ->
                val selectedTune = Tunes.values()[which]
                addAction(Stream(listOf(selectedTune.url), selectedTune.name))
            }
            setNegativeButton(android.R.string.cancel) { dialog, id -> dialog.cancel() }

            show()
        }
    }


    fun addInputAction() {
        addAction(Input())
    }

    fun addAction(action: NccoAction) {
        viewModel.actions.add(action)
        list.adapter?.notifyItemInserted(viewModel.actions.size - 1)
    }


    class CallRequestUiModel(
        var fromPhone: String = DEFAULT_CALLER,
        var toPhone: String = DEFAULT_CALLEE,
        var actions: MutableList<NccoAction> = mutableListOf()
    ) : ViewModel()


}