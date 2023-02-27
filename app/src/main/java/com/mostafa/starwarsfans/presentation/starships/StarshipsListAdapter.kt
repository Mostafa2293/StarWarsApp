package com.mostafa.starwarsfans.presentation.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.databinding.ListItemAllPeopleBinding
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.data.startships.model.Starship
import com.mostafa.starwarsfans.databinding.ListItemAllPlantesBinding
import com.mostafa.starwarsfans.databinding.ListItemAllSpeciesBinding
import com.mostafa.starwarsfans.databinding.ListItemAllStarshipsBinding

class StarshipsListAdapter() : PagingDataAdapter<Starship,StarshipsListAdapter.ViewHolder>(StarshipsDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAllStarshipsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val itemBinding: ListItemAllStarshipsBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(starship: Starship){
            itemBinding.starshipCrew.text = starship.crew
            itemBinding.starshipClass.text = starship.starship_class
            itemBinding.starshipLength.text = starship.length
            itemBinding.starshipMGLT.text = starship.MGLT
            itemBinding.starshipCargoCapacity.text = starship.cargo_capacity
            itemBinding.starshipConsumables.text = starship.consumables
            itemBinding.starshipCostInCredits.text = starship.cost_in_credits
            itemBinding.starshipManufacturer.text = starship.manufacturer
            itemBinding.starshipHyperDriveRating.text = starship.hyperdrive_rating
            itemBinding.starshipMaxAtmospheringSpeed.text = starship.max_atmosphering_speed
            itemBinding.starshipName.text = starship.name
            itemBinding.starshipModel.text = starship.model
            itemBinding.starshipPassengers.text = starship.passengers
        }
    }

    class StarshipsDiffCallback : DiffUtil.ItemCallback<Starship>() {
        override fun areItemsTheSame(oldItem: Starship, newItem: Starship): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Starship, newItem: Starship): Boolean {
            return oldItem == newItem
        }
    }

}