package com.timark.cache

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV

class CacheSp(fileName : String) {

    companion object{
        fun init(ctx : Context){
            MMKV.initialize(ctx)
        }
    }

    private val mSp = MMKV.mmkvWithID(getKey(fileName), MMKV.MULTI_PROCESS_MODE)

    private fun getKey(key: String) : String{
        return "key_${key.hashCode()}"
    }

    fun hasKey(key: String) : Boolean{
        return mSp.containsKey(getKey(key))
    }

    fun keys() : Array<out String>?{
        return mSp.allKeys()
    }

    fun clearAll(){
        mSp.clearAll()
    }

    fun putString(key : String, value : String?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun getString(key : String, defValue : String?) : String?{
        return mSp.decodeString(getKey(key), defValue)
    }

    fun putBoolean(key : String, value : Boolean?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun getBoolean(key : String, defValue : Boolean) : Boolean{
        return mSp.decodeBool(getKey(key), defValue)
    }

    fun putInt(key : String, value : Int?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun getInt(key : String, defValue : Int) : Int{
        return mSp.decodeInt(getKey(key), defValue)
    }

    fun putLong(key : String, value : Long?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun getLong(key : String, defValue : Long) : Long{
        return mSp.decodeLong(getKey(key), defValue)
    }

    fun putFloat(key : String, value : Float?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun getFloat(key : String, defValue : Float) : Float{
        return mSp.decodeFloat(getKey(key), defValue)
    }

    fun putDouble(key : String, value : Double?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun getDouble(key : String, defValue : Double) : Double{
        return mSp.decodeDouble(getKey(key), defValue)
    }

    fun putParcelable(key : String, value : Parcelable?){
        if (value == null){
            mSp.remove(getKey(key))
        } else {
            mSp.encode(getKey(key), value)
        }
    }

    fun<T : Parcelable> getParcelable(key : String, clazz : Class<T>) : T?{
        return mSp.decodeParcelable(getKey(key), clazz)
    }
}