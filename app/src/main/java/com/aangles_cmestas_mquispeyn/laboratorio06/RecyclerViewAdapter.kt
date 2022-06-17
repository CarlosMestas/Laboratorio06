package com.aangles_cmestas_mquispeyn.laboratorio06

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aangles_cmestas_mquispeyn.laboratorio06.classes.CarPark
import com.aangles_cmestas_mquispeyn.laboratorio06.databinding.CarParkDataBinding
import com.aangles_cmestas_mquispeyn.laboratorio06.RecyclerViewAdapter


class RecyclerViewAdapter: PagingDataAdapter<CarPark, RecyclerViewAdapter.CarParkViewHolder>(Companion){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarParkViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = CarParkDataBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CarParkViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: CarParkViewHolder, position: Int) {
        val carPark = getItem(position) ?: return
        holder.bindProduct(carPark)

    }

    companion object : DiffUtil.ItemCallback<CarPark>() {
        override fun areItemsTheSame(oldItem: CarPark, newItem: CarPark): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarPark, newItem: CarPark): Boolean {
            return oldItem == newItem
        }
    }

    inner class CarParkViewHolder(
        private val dataBinding: CarParkDataBinding
    ): RecyclerView.ViewHolder(dataBinding.root){
        fun bindProduct(carPark: CarPark){
            dataBinding.carpark = carPark
        }

    }

}