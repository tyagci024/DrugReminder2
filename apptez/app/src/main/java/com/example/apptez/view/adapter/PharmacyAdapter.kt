package com.example.apptez.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apptez.R
import com.example.apptez.databinding.RRow2Binding
import com.example.apptez.view.model.Drug
import com.example.apptez.view.model.Person
import com.example.apptez.view.model.Pharmacy

class PharmacyAdapter () : RecyclerView.Adapter<PharmacyAdapter.PharmacyHolder>(){
    var pharmacyList = emptyList<Pharmacy>()
    class PharmacyHolder (val binding: RRow2Binding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyHolder {
        val binding:RRow2Binding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.r_row2,parent,false)
        return PharmacyHolder(binding)
    }

    override fun onBindViewHolder(holder: PharmacyHolder, position: Int) {
        val currentPharmacy=pharmacyList.get(position)
        holder.binding.pharmacy=currentPharmacy


    }

    override fun getItemCount(): Int {
        return pharmacyList.size
    }
    fun updateData(newPharmacyList : List<Pharmacy>){
        pharmacyList=newPharmacyList
        notifyDataSetChanged()
    }
}