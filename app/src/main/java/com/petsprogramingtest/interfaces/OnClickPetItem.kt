package com.petsprogramingtest.interfaces

import com.petsprogramingtest.models.Pet

/** Recyclerview item click listener*/
interface OnClickPetItem {
    fun onClickPet(pet:Pet)
}