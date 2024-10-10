package com.example.myreadwritefile

import android.content.Context

internal object FileHelper {

    // menyimpan masukan user
    fun writeToFile(fileModel: FileModel, context: Context){
        context.openFileOutput(fileModel.filename, Context.MODE_PRIVATE).use {
            it.write(fileModel.data?.toByteArray())
        }
    }

    // membaca masukan user
    fun readFromFile(context: Context, filename: String): FileModel{
        val fileModel = FileModel()
        fileModel.filename = filename

        fileModel.data = context.openFileInput(filename).bufferedReader().useLines { lines ->
            lines.fold(""){ some, text ->
                "$some$text\\n"
            }
        }
        return fileModel
    }

}