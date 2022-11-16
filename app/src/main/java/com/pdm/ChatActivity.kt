package com.pdm

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm.data.DAOChatSingleton
import com.pdm.data.DAOMessageSingleton
import com.pdm.model.Message
import com.pdm.ui.list.adapter.MessageAdapter
import java.time.LocalDateTime

class ChatActivity : AppCompatActivity() {
    private lateinit var txtNomePessoa: TextView
    private lateinit var imgPessoa: ImageView
    private lateinit var botaoEnviar: Button
    private lateinit var txtCampoMensagem : EditText
    private lateinit var rvMessageList: RecyclerView

    private var chatId: Long = -1

    @RequiresApi(Build.VERSION_CODES.O)
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
        this.txtCampoMensagem = findViewById(R.id.txtCampoMensagem);

        this.botaoEnviar = findViewById(R.id.enviarMensagem);

        this.rvMessageList = findViewById(R.id.rvMessageList);

        this.rvMessageList.layoutManager = LinearLayoutManager(this)
        var messageArray = DAOMessageSingleton.getMessage(chat);
        if(messageArray != null){
            val adapter = MessageAdapter(messageArray);
            this.rvMessageList.adapter = adapter
        }else{
            Toast.makeText(this,
                "Não há mensagem", Toast.LENGTH_SHORT).show()
        }

        this.botaoEnviar.setOnClickListener {
            Toast.makeText(this,
                "entrou", Toast.LENGTH_SHORT).show()

            val mensagemEscrita: String = this.txtCampoMensagem.text.toString()
            Log.d("Mensagem escrita", mensagemEscrita);

            val num = (Math.random()*10).toInt()

            if(num%2 == 0){
                val m = Message(chatId, mensagemEscrita, true, LocalDateTime.now());
                DAOMessageSingleton.add(m)
            }else{
                val m = Message(chatId, mensagemEscrita, false, LocalDateTime.now());
                DAOMessageSingleton.add(m)
            }

            this.rvMessageList.adapter?.notifyItemInserted(0)
        }


    }

    override fun onBackPressed() {
        val output = Intent()
        output.putExtra("chatId", this.chatId)
        setResult(RESULT_OK, output)
        // nunca chame finish() antes de settar o resultado
        // super.onBackPressed() chama finish() internamente
        super.onBackPressed()
    }

}
