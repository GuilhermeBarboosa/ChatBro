package com.pdm.model;

import android.widget.ImageView

class Chat(
    val name: String,
    val lastMessage: String,
    var id: Long = 0
) {

    constructor(id: Long): this("", "") {
        this.id = id;
    }

}
