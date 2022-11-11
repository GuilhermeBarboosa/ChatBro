package com.pdm

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm.data.DAOChatSingleton
import com.pdm.model.Chat
import com.pdm.ui.list.adapter.ChatAdapter


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
        Log.e("passou", "teste");


    }

    fun onClickInsert(v: View) {
        Log.e("teste", "teste");
        val c = Chat("Hello", "aaaaa")
        DAOChatSingleton.add(c)
        this.rvChatList.adapter?.notifyItemInserted(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when(item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onSupportNavigateUp(): Boolean {
//    val navController = findNavController(R.id.nav_host_fragment_content_main)
//    return navController.navigateUp(appBarConfiguration)
//            || super.onSupportNavigateUp()
//    }
}