package com.kangdroid.navi_arch.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.navi_arch.data.FileData
import com.kangdroid.navi_arch.databinding.ItemFileBinding

class FileAdapter(val onClick: (FileData) -> Unit): RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    private var fileList: List<FileData> = listOf()

    inner class FileViewHolder(private val itemFileBinding: ItemFileBinding): RecyclerView.ViewHolder(itemFileBinding.root) {
        fun bind(fileData: FileData) {
            itemFileBinding.fileName.text = fileData.fileName
            itemFileBinding.lastModifiedTime.text = fileData.lastModifiedTime.toString()

            itemFileBinding.root.setOnClickListener {
                onClick(fileData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val itemFileBinding: ItemFileBinding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(itemFileBinding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(fileList[position])
    }

    override fun getItemCount(): Int = fileList.size

    fun setFileList(fileList: List<FileData>) {
        this.fileList = fileList
        notifyDataSetChanged()
    }
}