package com.example.apptez.view.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apptez.R
import com.example.apptez.databinding.RRowBinding
import com.example.apptez.databinding.RRowcuBinding
import com.example.apptez.view.model.CheckUpDate
import com.example.apptez.view.view.FragmentCheckUpList
import com.example.apptez.view.view.FragmentCheckUpListDirections
import com.example.apptez.view.view.FragmentListDirections
import com.example.apptez.view.viewModel.DateViewModel
import com.example.apptez.view.viewModel.PharmacyViewModel
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter(private val viewModel: DateViewModel):RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    private var dateList = emptyList<CheckUpDate>()



//context: Context, val dateDeleteClickInterface: DateDeleteClickInterface

    class ViewHolder(val binding: RRowcuBinding):RecyclerView.ViewHolder(binding.root) {



        fun bind(checkUpDate: CheckUpDate){


            val timestamp = checkUpDate.Date
            val dateObject = Date(timestamp)
            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(dateObject)
            //date.text = formattedDate
            /*
              bu kodda, checkUpDate.Date'i alarak Long türünden Date türüne dönüşüo.
               SimpleDateFormat kullanarak tarihi "dd/MM/yyyy"
               formatında biçimlendiriliyor. Son olarak, biçimlendirilmiş tarihi
               TextView'a yerleşio. Bu şekilde, tarihi doğru bir şekilde
               ayarlıyoz*/
        }


    }

    //val view = LayoutInflater.from(parent.context).inflate(R.layout.r_row,parent,false)
    //return MyViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : RRowcuBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.r_rowcu,parent,false)
        return ViewHolder(binding)
    }

    /*
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentDrug=drugList.get(position)
        holder.bind(currentDrug)
        holder.itemView.setOnClickListener {
            val action = FragmentListDirections.actionFragmentListToTreatmentUpdate(currentDrug)
            holder.itemView.findNavController().navigate(action)
        }
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDate=dateList.get(position)
        holder.binding.dateCheck=currentDate
        holder.itemView.setOnClickListener {
            val action = FragmentCheckUpListDirections.actionFragmentCheckUpListToCheckupUpdate(currentDate)
            holder.itemView.findNavController().navigate(action)

        }
        holder.binding.delete.setOnClickListener {
            viewModel.delete(currentDate)

        }

        /*
        val currentDate=dateList.get(position)
        holder.hostName.text=dateList[position].Hospital
        holder.docName.text=dateList[position].Doctor
        holder.date.text=dateList[position].Date.toString()
        holder.itemView.setOnClickListener {
            val action = FragmentCheckUpListDirections.actionFragmentCheckUpListToCheckupUpdate(currentDate)
            holder.itemView.findNavController().navigate(action)
        }*/


        }

    override fun getItemCount(): Int {
        return dateList.size
    }
    fun updateDate(list : List<CheckUpDate>){

        dateList=list

        notifyDataSetChanged()

    }


}


