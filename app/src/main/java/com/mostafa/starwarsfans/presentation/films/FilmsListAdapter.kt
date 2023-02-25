package com.mostafa.starwarsfans.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.starwarsfans.data.films.model.Films
import com.mostafa.starwarsfans.databinding.ListItemAllPeopleBinding
import com.mostafa.starwarsfans.data.people.model.People
import com.mostafa.starwarsfans.databinding.ListItemAllFilmsBinding

class FilmsListAdapter() : PagingDataAdapter<Films,FilmsListAdapter.ViewHolder>(FilmsDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ListItemAllFilmsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val itemBinding: ListItemAllFilmsBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(film:Films){
            itemBinding.filmTitle.text = film.title
            itemBinding.filmDirector.text = film.director
            itemBinding.filmProducer.text = film.producer
            itemBinding.filmReleaseDate.text = film.release_date
        }
    }

    class FilmsDiffCallback : DiffUtil.ItemCallback<Films>() {

        override fun areItemsTheSame(oldItem: Films, newItem: Films): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Films, newItem: Films): Boolean {
            return oldItem == newItem
        }
    }

}