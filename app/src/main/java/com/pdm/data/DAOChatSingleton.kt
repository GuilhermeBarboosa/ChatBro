package com.pdm.data

import android.util.Log
import com.pdm.model.Chat
import java.util.logging.Logger
import kotlin.random.Random

object DAOChatSingleton {
    private var serial: Long = 1
    private val chats = ArrayList<Chat>()

    init {
        val listArray: ArrayList<String> = ArrayList()
        listArray.add("Guilherme");
        listArray.add("Higor");
        listArray.add("Clebson");
        for(i in 0..2) {
            val c = Chat("${listArray[i]}", "hello");
            this.add(c)
        }
    }

    fun add(c: Chat) {
        this.chats.add(0, c)
        c.id = serial++
    }

    fun getChats(): ArrayList<Chat> {
        return this.chats
    }

    fun getChatById(id: Long): Chat? {
        for(c in this.chats) {
            if(c.id == id)
                return c
        }
        return null
    }

    fun getChatPositionById(id: Long): Int {
        return this.chats.indexOf(Chat(id))
    }
}