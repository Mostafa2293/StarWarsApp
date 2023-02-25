package com.mostafa.starwarsfans.presentation.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.databinding.ListItemAllPeopleBinding
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.databinding.ListItemAllPlantesBinding
import com.mostafa.starwarsfans.databinding.ListItemAllSpeciesBinding

class SpeciesListAdapter() : PagingDataAdapter<Species,SpeciesListAdapter.ViewHolder>(SpeciesDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAllSpeciesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val itemBinding: ListItemAllSpeciesBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(species: Species){
            itemBinding.speciesLang.text=species.language
            itemBinding.speciesName.text=species.name
            itemBinding.speciesAverageHeight.text=species.average_height
            itemBinding.speciesDesignation.text=species.designation
            itemBinding.speciesEyeColor.text=species.eye_colors
            itemBinding.speciesClassification.text=species.classification
            itemBinding.speciesHairColor.text=species.hair_colors
            itemBinding.speciesSkinColor.text=species.skin_colors
        }
    }

    class SpeciesDiffCallback : DiffUtil.ItemCallback<Species>() {
        override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean {
           return oldItem == newItem
        }
    }

}