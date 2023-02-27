package com.mostafa.starwarsfans.presentation.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.databinding.ListItemAllPeopleBinding
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.data.plantes.model.Planet
import com.mostafa.starwarsfans.data.species.model.Species
import com.mostafa.starwarsfans.data.vehicles.model.Vehicle
import com.mostafa.starwarsfans.databinding.ListItemAllPlantesBinding
import com.mostafa.starwarsfans.databinding.ListItemAllSpeciesBinding
import com.mostafa.starwarsfans.databinding.ListItemAllVehiclesBinding

class VehiclesListAdapter() : PagingDataAdapter<Vehicle,VehiclesListAdapter.ViewHolder>(VehiclesDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAllVehiclesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val itemBinding: ListItemAllVehiclesBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(vehicle: Vehicle){
            itemBinding.vehicleClass.text = vehicle.vehicle_class
            itemBinding.vehiclesCrew.text = vehicle.crew
            itemBinding.vehiclesLength.text = vehicle.length
            itemBinding.vehiclesName.text = vehicle.name
            itemBinding.vehiclesModel.text = vehicle.model
            itemBinding.vehiclesCargoCapacity.text = vehicle.cargo_capacity
            itemBinding.vehiclesConsumables.text = vehicle.cargo_capacity
            itemBinding.vehiclesCostInCredits.text = vehicle.cost_in_credits
            itemBinding.vehiclesManufacturer.text = vehicle.manufacturer
            itemBinding.vehiclesMaxAtmospheringSpeed.text = vehicle.max_atmosphering_speed
            itemBinding.vehiclesPassengers.text = vehicle.passengers
        }
    }

    class VehiclesDiffCallback : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }


    }

}