package com.petsprogramingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.petsprogramingtest.R
import com.petsprogramingtest.databinding.PetRowBinding
import com.petsprogramingtest.interfaces.OnClickPetItem
import com.petsprogramingtest.models.Pet

class PetAdapter(private val petList: List<Pet>) :
    RecyclerView.Adapter<PetAdapter.ViewHolder>() {
   private lateinit var onClickPet:OnClickPetItem


    class ViewHolder(private val binding: PetRowBinding, private val onClickPet: OnClickPetItem) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: Pet) {
            binding.petModel = pet
            binding.onPetClick = onClickPet
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<PetRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.pet_row,
            parent,
            false
        )
        return ViewHolder(binding, onClickPet)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = petList[position]
        holder.bind(pet)
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    fun setListener(onClickPet:OnClickPetItem){
        this.onClickPet = onClickPet
    }
}