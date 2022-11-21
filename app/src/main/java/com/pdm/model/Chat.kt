package com.pdm.model;

import android.widget.ImageView

class Chat(
    val name: String,
    var lastMessage: String,
    var id: Long = 0
) {
    fun updateMensagem(mensagemEscrita: String) {
        this.lastMessage = mensagemEscrita;
    }

    constructor(id: Long): this("", "") {
        this.id = id;
    }

}
