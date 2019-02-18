package com.nexmo.example.britt.playingvapi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_action.view.*

interface RemoveItem {
    fun onRemoveItem(position: Int)
}

class NccoAdapter(val actions: MutableList<NccoAction>) : RecyclerView.Adapter<NccoCompVH>(), RemoveItem {
    override fun onRemoveItem(position: Int) {
        actions.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NccoCompVH {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_action, parent, false)
        val holder = when (viewType) {
            ActionType.talk.ordinal -> TalkCompVH(view, this)
            ActionType.stream.ordinal -> StreamCompVH(view, this)
            ActionType.input.ordinal -> InputCompVH(view, this)

            else -> TalkCompVH(view, this)
        }

        view.ivClose.setOnClickListener(holder)

        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return actions[position].actionType.ordinal

    }

    override fun getItemCount(): Int {
        return actions.size
    }

    override fun onBindViewHolder(holder: NccoCompVH, index: Int) {
        holder.bind(actions[index])
    }

}

abstract class NccoCompVH(itemView: View, val removeItemListener : RemoveItem) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    lateinit var actionData: NccoAction

    abstract fun bind(action: NccoAction)

    override fun onClick(v: View?) {
        if (v == itemView.ivClose) {
            removeItemListener.onRemoveItem(adapterPosition)
        }
    }

}

class TalkCompVH(itemView: View, removeItemListener : RemoveItem) : NccoCompVH(itemView, removeItemListener) {

    override fun bind(action: NccoAction) {
        actionData = action as Talk
        itemView.tvText.text = action.text
        itemView.ivIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_talk_24dp))
    }
}

class StreamCompVH(itemView: View, removeItemListener : RemoveItem) : NccoCompVH(itemView, removeItemListener) {

    override fun bind(action: NccoAction) {
        actionData = action as Stream
        itemView.tvText.text = action.tuneName ?: action.streamUrl[0]
        itemView.ivIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_stream_24dp))
    }
}

class InputCompVH(itemView: View, removeItemListener : RemoveItem) : NccoCompVH(itemView, removeItemListener) {

    override fun bind(action: NccoAction) {
        actionData = action as Input
        itemView.tvText.text = "   * * * *   ${action.actionType.name}   * * * *   "
        itemView.ivIcon.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_input_24dp))
    }
}
