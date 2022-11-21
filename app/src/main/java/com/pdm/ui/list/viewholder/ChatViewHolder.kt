package com.pdm.ui.list.viewholder

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm.R
import com.pdm.model.Chat
import com.pdm.ui.list.adapter.ChatAdapter

class ChatViewHolder (
    itemView: View,
    protected val adapter: ChatAdapter
    ): RecyclerView.ViewHolder(itemView) {

    private var imgProfile =
        itemView.findViewById<ImageView>(R.id.imageProfile)
    private val txtName =
        itemView.findViewById<TextView>(R.id.txtName)
    private val txtMessage =
        itemView.findViewById<TextView>(R.id.txtMessage)
    protected lateinit var currentChat: Chat

    init {
        this.txtMessage.setOnClickListener {
            this.adapter
                .getOnClickChatListener()?.onClick(this.currentChat)
        }
    }

    open fun bind(chat: Chat) {
        this.currentChat = chat
        this.txtName.text = chat.name
        this.txtMessage.text = chat.lastMessage
    }

}