package com.example.apptez.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apptez.R
import com.example.apptez.databinding.RRowBinding
import com.example.apptez.view.model.Drug
import com.example.apptez.view.view.FragmentListDirections

class RwAdapter:RecyclerView.Adapter<RwAdapter.MyViewHolder>() {
    private var drugList = emptyList<Drug>()

    class MyViewHolder(val binding: RRowBinding):RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : RRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.r_row,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentDrug=drugList.get(position)
        holder.binding.drug=currentDrug
        holder.itemView.setOnClickListener {
            val action = FragmentListDirections.actionFragmentListToTreatmentUpdate(currentDrug)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return drugList.size
    }
    fun updateData(list : List<Drug>){
        drugList=list
        notifyDataSetChanged()

    }


}