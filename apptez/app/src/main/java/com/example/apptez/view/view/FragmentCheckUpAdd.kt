package com.example.apptez.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptez.R
import com.example.apptez.databinding.FragmentCheckUpAddBinding
import com.example.apptez.view.model.CheckUpDate
import com.example.apptez.view.viewModel.DateViewModel
import java.util.*

class FragmentCheckUpAdd : Fragment() {
    private lateinit var binding: FragmentCheckUpAddBinding
    private val dateViewModel:DateViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_check_up_add, container, false)
        binding.addNesnesi=this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun buttonSave(){
        val hour = binding.timeInput.hour
        val minute = binding.timeInput.minute
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        val time = calendar.timeInMillis
        val endDateCalendar = Calendar.getInstance()
        endDateCalendar.set(
            binding.checkupDate.year,
            binding.checkupDate.month,
            binding.checkupDate.dayOfMonth
        )
        val docDate = endDateCalendar.timeInMillis
        val checkUpDate=CheckUpDate(0,docDate,binding.doctor.text.toString(),binding.hospital.text.toString(),time)
        dateViewModel.impDate(checkUpDate)

        findNavController().navigate(R.id.action_fragmentCheckUpAdd_to_fragmentCheckUpList)
    }


}