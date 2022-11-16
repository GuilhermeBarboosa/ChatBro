package com.pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.data.DAOChatSingleton

class ChatActivity : AppCompatActivity() {
    private lateinit var txtNomePessoa: TextView
    private lateinit var imgPessoa: ImageView

    private var chatId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        this.txtNomePessoa = findViewById(R.id.nomePessoa)
        this.imgPessoa = findViewById(R.id.imgPessoa)

        this.chatId = intent.getLongExtra("chatId", -1)
        val chat = DAOChatSingleton.getChatById(this.chatId)
        if(chat == null) {
            Toast.makeText(this,
                R.string.chat_not_found, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        this.txtNomePessoa.text = chat.name;
        this.imgPessoa.setImageResource(R.drawable.buzz)
//        this.rvSubtasks.layoutManager = LinearLayoutManager(this)
//        this.rvSubtasks.adapter = TaskAdapter(task.getSubtasks())
    }
}
