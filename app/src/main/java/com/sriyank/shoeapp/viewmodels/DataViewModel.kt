package com.sriyank.shoeapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sriyank.shoeapp.data.ShoeListData

class DataViewModel : ViewModel() {

    private var shoesList = mutableListOf<ShoeListData>()

    // create liveData to more encapsulation
    private var _shoeListLiveData =MutableLiveData<List<ShoeListData>>()

    val dataShoeList : LiveData<List<ShoeListData>>
            get() =_shoeListLiveData

    fun onSave(shoeName: String ,shoeCompany: String ,shoeSize:String ,shoeDescription: String){
        val newItem =ShoeListData(shoeName,shoeCompany,shoeSize,shoeDescription)
        newItem.let {
            shoesList.add(it)
            _shoeListLiveData.value = shoesList
        }
    }

}