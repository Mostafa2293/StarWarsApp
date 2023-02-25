package com.mostafa.starwarsfans.presentation.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.databinding.ListItemAllPeopleBinding
import com.mostafa.starwarsfans.data.people.model.People

class PeopleListAdapter() : PagingDataAdapter<People,PeopleListAdapter.ViewHolder>(PeopleDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAllPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val itemBinding: ListItemAllPeopleBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(people:People){
            itemBinding.peopleName.text = people.name
            itemBinding.peopleHeight.text = people.height
            itemBinding.peopleMass.text = people.mass
            itemBinding.peopleBirthday.text = people.birth_year
            itemBinding.peopleGender.text = people.gender
            itemBinding.peopleSkinColor.text = people.skin_color
            itemBinding.peopleEyeColor.text = people.eye_color
            itemBinding.peopleHairColor.text = people.hair_color
        }
    }

    class PeopleDiffCallback : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(
            oldItem: People,
            newItem: People
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: People,
            newItem: People
        ): Boolean {
            return oldItem == newItem
        }
    }

}