package com.sriyank.shoeapp.data

import com.sriyank.shoeapp.R

data class ShoeListData(
    var shoeName: String ="",
    var shoeCompany: String = "",
    var shoeSize:String = "",
    var shoeDescription: String= "",
    val images: List<Int> = arrayListOf(R.drawable.shoe_1,R.drawable.shoe_2,R.drawable.shoe_3,R.drawable.shoe_4)

)
