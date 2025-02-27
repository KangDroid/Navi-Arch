package com.kangdroid.navi_arch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.navi_arch.R
import com.kangdroid.navi_arch.data.FileData
import com.kangdroid.navi_arch.data.FileType
import com.kangdroid.navi_arch.databinding.ItemFileBinding

class FileAdapter(
    val onClick: (FileData, Int) -> Unit,
    val onLongClick: ((FileData) -> Boolean)?,
    var fileList: List<FileData>,
    val pageNumber: Int,
    val currentFolder: FileData
) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    inner class FileViewHolder(private val itemFileBinding: ItemFileBinding) :
        RecyclerView.ViewHolder(itemFileBinding.root) {

        // Bind fileData to object
        fun bind(fileData: FileData) {
            itemFileBinding.fileName.text = fileData.getBriefName()
            itemFileBinding.lastModifiedTime.text = fileData.getFormattedDate()
            itemFileBinding.imgFileType.setImageResource(
                when (fileData.fileType) {
                    FileType.File.toString() -> R.drawable.ic_common_file_24
                    FileType.Folder.toString() -> R.drawable.ic_common_folder_24
                    else -> R.drawable.ic_common_file_24
                }
            )

            // When each row clicked
            itemFileBinding.root.setOnClickListener {
                onClick(fileData, pageNumber)
            }

            // When each row long-clicked
            itemFileBinding.root.setOnLongClickListener {
                onLongClick?.invoke(fileData) ?: false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val itemFileBinding: ItemFileBinding =
            ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(itemFileBinding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(fileList[position])
    }

    override fun getItemCount(): Int = fileList.size
}