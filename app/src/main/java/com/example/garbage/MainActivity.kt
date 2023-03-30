package com.example.garbage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.garbage.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var dataList: MutableList<DataList>
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.navigationView.setNavigationItemSelectedListener(this)

        dataList = mutableListOf<DataList>()
        for (i in 1..50) {
            if(i % 2 == 1){
                dataList.add(DataList("홍길동${i}", "${20 + i}세", "rlawn1${i}@gmail.com", R.drawable.man))
            } else {
                dataList.add(DataList("홍길여${i}", "${20 + i}세", "rlawn2${i}@gmail.com", R.drawable.woman))
            }
        }

        val customAdapter = CustomAdapter(dataList)
        binding.recyclerview.adapter = customAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.addItemDecoration(DividerItemDecoration(this,1 ))

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_info -> Toast.makeText(this, "앱정보 클릭", Toast.LENGTH_SHORT).show()
            R.id.item_user -> Toast.makeText(this, "사용자정보 클릭", Toast.LENGTH_SHORT).show()
            R.id.item_age -> Toast.makeText(this, "사용자나이 클릭", Toast.LENGTH_SHORT).show()
            R.id.item_email -> Toast.makeText(this, "사용자이메일 클릭", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}