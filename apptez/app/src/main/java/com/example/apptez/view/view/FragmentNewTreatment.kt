package com.example.apptez.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptez.R
import com.example.apptez.databinding.FragmentNewTreatmentBinding
import com.example.apptez.view.model.Drug
import com.example.apptez.view.viewModel.DrugViewModel
import java.util.*


class FragmentNewTreatment : Fragment() {
    private var _binding:FragmentNewTreatmentBinding?=null
    private val drugViewModel:DrugViewModel by viewModels()
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewTreatmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addButton.setOnClickListener {
            val timesPerDay = binding.offenSave.text.toString().toInt()

            val hour = binding.timeInput.hour
            val minute = binding.timeInput.minute
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)
            val time = calendar.timeInMillis
            // Bitiş tarihini alın
            val endDateCalendar = Calendar.getInstance()
            endDateCalendar.set(
                binding.endDatePicker.year,
                binding.endDatePicker.month,
                binding.endDatePicker.dayOfMonth
            )
            val endDate = endDateCalendar.timeInMillis
            val drug = Drug(0,binding.drugNameSave.text.toString(),binding.descriptionSave.text.toString(),time,binding.repeatCheckBox.isChecked,endDate,timesPerDay)
            drugViewModel.impDrug(drug)
            Toast.makeText(requireContext(),"Tedavi Oluşturuldu!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_fragmentNewTreatment_to_fragmentList)
        }

        return view    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}