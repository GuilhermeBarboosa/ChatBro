package com.pdm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm.data.DAOChatSingleton
import com.pdm.model.Chat
import com.pdm.ui.list.adapter.ChatAdapter
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var rvChatList: RecyclerView
    private lateinit var appBarConfiguration: AppBarConfiguration
//  private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        this.rvChatList = findViewById(R.id.rvChatList);

        this.rvChatList.layoutManager = LinearLayoutManager(this)
        val adapter = ChatAdapter(DAOChatSingleton.getChats());
        this.rvChatList.adapter = adapter

        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if(result.resultCode == RESULT_OK && result.data != null) {
                val chatId = result.data!!.getLongExtra("chatId", -1)
                val chatPos = DAOChatSingleton.getChatPositionById(chatId)
                adapter.notifyItemChanged(chatPos)
            }
        }

        adapter.setOnClickChatListener { chat ->
            val openChatIntent =
                Intent(this, ChatActivity::class.java)
            openChatIntent.putExtra("chatId", chat.id)
            resultLauncher.launch(openChatIntent)
        }
    }

    fun onClickInsert(v: View) {
        var listNomes: ArrayList<String> = ArrayList()
        listNomes = addNomes(listNomes);

        var numero = (Math.random() * 10)

        while (numero > listNomes.size) {
            numero = (Math.random() * 10);
        }
        var nome = listNomes.get(numero.toInt());

        val c = Chat("" + nome, "")
        DAOChatSingleton.add(c)
        this.rvChatList.adapter?.notifyItemInserted(0)
    }

    private fun addNomes(listNomes: java.util.ArrayList<String>): java.util.ArrayList<String> {
        listNomes.add("José");
        listNomes.add("Paulo");
        listNomes.add("Lucas");
        listNomes.add("Julia");
        listNomes.add("Carol");
        listNomes.add("Fernanda");
        listNomes.add("Gabriel");
        listNomes.add("Marcos");
        listNomes.add("Felipe");
        return listNomes;
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

}