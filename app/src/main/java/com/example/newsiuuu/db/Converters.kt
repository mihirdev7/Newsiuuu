package com.example.newsiuuu.db

import androidx.room.TypeConverter
import javax.xml.transform.Source

class Converters {

    @TypeConverter
    fun fromSource(source:com.example.newsiuuu.models.Source):String{
        return source.name!!
    }

    @TypeConverter
    fun toSource (name:String):com.example.newsiuuu.models.Source{
        return com.example.newsiuuu.models.Source(name,name)
    }
}