package com.pdm.ui.list.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.R
import com.pdm.model.Chat
import com.pdm.ui.list.viewholder.ChatViewHolder

class ChatAdapter (
    private var chats: ArrayList<Chat>
        ): RecyclerView.Adapter<ChatViewHolder>() {

    fun interface OnClickChatListener {
        fun onClick(chat: Chat)
    }

    private val expandedMap = SparseBooleanArray()
    private var listener: OnClickChatListener? = null

    companion object consts {
        private val TYPE_CHAT = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val itemView = layoutInflater.inflate(
            R.layout.fragment_first, parent, false
        )
        return ChatViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = this.chats[position]
        holder.bind(chat)
    }

    override fun getItemCount(): Int {
        return this.chats.size
    }

    fun setOnClickTaskListener(listener: OnClickChatListener?) {
        this.listener = listener
    }

    fun getOnClickTaskListener(): OnClickChatListener? {
        return this.listener
    }

}