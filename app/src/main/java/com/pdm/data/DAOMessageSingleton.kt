package com.pdm.data

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.pdm.model.Chat
import com.pdm.model.Message
import java.time.LocalDateTime
import java.util.logging.Logger
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
object DAOMessageSingleton {
    private var serial: Long = 1
    private val message = ArrayList<Message>()

    init {
        for(i in 1..5) {
            val m = Message(1, "oi, moanoite", true, LocalDateTime.now());
            this.add(m)
        }
    }

    fun add(m: Message) {
        this.message.add(0, m);
        m.id = serial++
    }

    fun getMessage(chat: Chat): ArrayList<Message>? {
        if(!this.message.isEmpty()){
            if(chat.id == this.message.get(0).chatId){
                return this.message
            }
        }
        return null;
    }

    fun getChatById(id: Long): Message? {
        for(c in this.message) {
            if(c.id == id)
                return c
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getChatPositionById(id: Long): Int {
        return this.message.indexOf(Message(id))
    }

}