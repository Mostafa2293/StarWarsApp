package com.mostafa.starwarsfans.presentation.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.databinding.ListItemAllPeopleBinding
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.databinding.ListItemAllPlantesBinding

class PlanetsListAdapter() : PagingDataAdapter<Planet,PlanetsListAdapter.ViewHolder>(PlanetsDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAllPlantesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val itemBinding: ListItemAllPlantesBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(planet:Planet){
            itemBinding.planetName.text = planet.name
            itemBinding.planetClimate.text = planet.climate
            itemBinding.planetDiameter.text = planet.diameter
            itemBinding.planetGravity.text = planet.gravity
            itemBinding.planetOrbitalPeriod.text = planet.orbital_period
            itemBinding.planetPopulation.text = planet.population
            itemBinding.planetRotationPeriod.text = planet.rotation_period
            itemBinding.planetTerrain.text = planet.terrain
        }
    }

    class PlanetsDiffCallback : DiffUtil.ItemCallback<Planet>() {

        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem == newItem
        }
    }

}