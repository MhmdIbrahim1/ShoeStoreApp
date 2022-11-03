package com.sriyank.shoeapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sriyank.shoeapp.data.ShoeListData

class DataViewModel(application: Application) : AndroidViewModel(application) {

    private var shoesList = mutableListOf<ShoeListData>()

    private var _shoeListLiveData =MutableLiveData<List<ShoeListData>>()

    val dataShoeList : LiveData<List<ShoeListData>>
            get() =_shoeListLiveData

    fun onSave(shoeName: String ,shoeCompany: String ,shoeSize:String ,shoeDescription: String,images: List<Int>){
        val newItem =ShoeListData(shoeName,shoeCompany,shoeSize,shoeDescription,images)
        newItem.let {
            shoesList.add(it)
            _shoeListLiveData.value = shoesList
        }
    }

}