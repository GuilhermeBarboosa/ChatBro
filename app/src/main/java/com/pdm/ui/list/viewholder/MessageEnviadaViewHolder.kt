package com.pdm.ui.list.viewholder

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm.R
import com.pdm.data.DAOChatSingleton
import com.pdm.model.Chat
import com.pdm.model.Message
import com.pdm.ui.list.adapter.MessageAdapter
import kotlinx.coroutines.NonDisposableHandle.parent
import org.w3c.dom.Text

class MessageEnviadaViewHolder (
    itemView: View,
    protected val adapter: MessageAdapter
    ): RecyclerView.ViewHolder(itemView) {
    private var nomeMensagemEnviada =
        itemView.findViewById<TextView>(R.id.nomeMensagemEnviada)
    private val mensagemEnviada =
        itemView.findViewById<TextView>(R.id.mensagemEnviada)
    protected lateinit var currentMessage: Message

    init {
        this.mensagemEnviada.setOnClickListener {
            this.adapter
                .getOnClickMessageListener()?.onClick(this.currentMessage)
        }
    }

    open fun bind(message: Message) {
        this.currentMessage = message
        this.nomeMensagemEnviada.text = message.message
        val chat: Chat? = DAOChatSingleton.getChatById(message.chatId);
        this.mensagemEnviada.text = chat?.name;
    }
}