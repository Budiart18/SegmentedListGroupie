package com.aeryz.segmentedlistgroupie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aeryz.segmentedlistgroupie.databinding.ActivityMainBinding
import com.aeryz.segmentedlistgroupie.model.SectionedData
import com.aeryz.segmentedlistgroupie.viewItems.DataItem
import com.aeryz.segmentedlistgroupie.viewItems.HeaderItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter: GroupieAdapter by lazy {
        GroupieAdapter()
    }

    private fun getListData(): List<SectionedData> = listOf(
        SectionedData("Category A", listOf("Ayam Goreng", "Ayam Bakar", "Ayam Bumbu Kuning")),
        SectionedData("Category B", listOf("Bakwan", "Bakso", "Bebek Goreng")),
        SectionedData("Category C", listOf("Cendol", "Cakes", "Cucur")),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        binding.rvData.adapter = adapter
        val sections = getListData().map { sectionData ->
            val section = Section()
            section.setHeader(HeaderItem(sectionData.name){
                Toast.makeText(this, "Header Clicked : ${sectionData.name}", Toast.LENGTH_SHORT).show()
            })
            val items = sectionData.data.map { data ->
                DataItem(data){
                    Toast.makeText(this, "Item Clicked : $data", Toast.LENGTH_SHORT).show()
                }
            }
            section.addAll(items)
            section
        }
        adapter.addAll(sections)
    }
}