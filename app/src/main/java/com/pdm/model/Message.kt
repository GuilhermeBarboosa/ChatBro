package com.pdm.model;

import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime

class Message(
    var chatId: Long = 0,
    var message: String = "",
    var recebida: Boolean = true,
    var horaMensagem: LocalDateTime,
    var id: Long = 0
) {
    private val messages = ArrayList<Message>()

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(id: Long): this(0, "", false, LocalDateTime.now()) {
        this.id = id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createMessage(message: String, recebida: Boolean, chatId: Long): Message? {
        var messageObject: Message? = null;
        messageObject?.message = message;
        messageObject?.recebida = recebida;
        messageObject?.chatId = chatId;
        messageObject?.horaMensagem = LocalDateTime.now();
        return messageObject;
    }

    fun addMessage(message: Message) {
        this.messages.add(message)
    }

    fun getMessage(): ArrayList<Message> {
        return this.messages
    }

    override fun equals(other: Any?): Boolean {
        return this.id == (other as? Message)?.id
    }

    override fun toString(): String {
        return "Message(chatId=$chatId, message='$message', recebida=$recebida, horaMensagem=$horaMensagem, id=$id, messages=$messages)"
    }


}
