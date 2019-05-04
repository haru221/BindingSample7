package com.x0.gadget_tf.bindingsample7

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.x0.gadget_tf.bindingsample7.databinding.ActivityMainBinding
import com.x0.gadget_tf.bindingsample7.databinding.ListExampleBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = ExampleAdapter(this)
        adapter.items = listOf(
            ListItem("ゴミ出し", Date()),
            ListItem("技術書をかく", Date()),
            ListItem("脱稿する", Date())
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    class ExampleAdapter(context: Context): RecyclerView.Adapter<Holder>() {
        var items: List<ListItem> = emptyList()
        private val inflater = LayoutInflater.from(context)

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding: ListExampleBinding = DataBindingUtil.inflate(inflater, R.layout.list_example, parent, false)
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.binding.item = items[position]
            holder.binding.executePendingBindings()
        }
    }

    class Holder(val binding: ListExampleBinding) : RecyclerView.ViewHolder(binding.root)

}
