package com.pdm.ui.list.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.pdm.R
import com.pdm.model.Message
import com.pdm.ui.list.viewholder.MessageViewHolder

class MessageAdapter(
    private var message: ArrayList<Message>
        ): Adapter<MessageViewHolder>() {

    fun interface OnClickMessageListener {
        fun onClick(message: Message)
    }

    private val expandedMap = SparseBooleanArray()
    private var listener: OnClickMessageListener? = null

    companion object consts {
        private val TYPE_MESSAGE_ENVIADA = 0
        private val TYPE_MESSAGE_RECEBIDA = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);

            val itemView = layoutInflater.inflate(
                R.layout.mensagem_enviada_activity,
                parent, false
            )
            return MessageViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = this.message[position]
        holder.bind(message)
    }

    fun getExpandedMap(): SparseBooleanArray {
        return this.expandedMap
    }

    override fun getItemCount(): Int {
        return this.message.size
    }

    fun setOnClickMessageListener(listener: OnClickMessageListener?) {
        this.listener = listener
    }

    fun getOnClickMessageListener(): OnClickMessageListener? {
        return this.listener
    }

}