package com.petsprogramingtest.uifragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.petsprogramingtest.R
import com.petsprogramingtest.adapters.PetAdapter
import com.petsprogramingtest.databinding.FragmentPetsBinding
import com.petsprogramingtest.interfaces.OnClickPetItem
import com.petsprogramingtest.models.Pet
import com.petsprogramingtest.models.Pets
import com.petsprogramingtest.utils.Constants
import com.petsprogramingtest.utils.getJsonDataFromAsset
import com.petsprogramingtest.utils.isBetween9To18
import com.petsprogramingtest.utils.isSaturdayOrSunday


class PetsFragment : Fragment() {
    private lateinit var binding: FragmentPetsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pets,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isSaturdayOrSunday()) {
            Navigation.findNavController(view)
                .navigate(R.id.action_petsFragment_to_messageFragment)
        }

        if (!isBetween9To18()) {
            Navigation.findNavController(view)
                .navigate(R.id.action_petsFragment_to_messageFragment)
        }

        /**Getting pets_list.json file as string*/
        val jsonFileString = getJsonDataFromAsset(requireContext(), Constants.petListFile)

        /** Converting the json string to Pets object using gson*/
        val gson = Gson()
        val pets: Pets = gson.fromJson(jsonFileString, Pets::class.java)

        /** Create adapter object and passing list to recyclerview*/
        val adapter = PetAdapter(petList = pets.petList)
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvPets.context,
            LinearLayoutManager.VERTICAL
        )
        binding.decorator = dividerItemDecoration
        binding.adapter = adapter

        /** adding click listener to adapter*/
        adapter.setListener(listener)
    }


    private val listener = object : OnClickPetItem {
        override fun onClickPet(pet: Pet) {
            val bundle = Bundle()
            bundle.putString(Constants.KEY_CONTENT, pet.contentUrl)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_petsFragment_to_detailFragment, bundle)
        }
    }

}