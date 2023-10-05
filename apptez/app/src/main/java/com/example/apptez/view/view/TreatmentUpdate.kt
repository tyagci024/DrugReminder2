package com.example.apptez.view.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptez.R
import com.example.apptez.databinding.FragmentNewTreatmentBinding
import com.example.apptez.databinding.FragmentTreatmentUpdateBinding
import com.example.apptez.view.model.Drug
import com.example.apptez.view.viewModel.DrugViewModel
import java.util.*


class TreatmentUpdate : Fragment() {

    private var _binding: FragmentTreatmentUpdateBinding?=null
    private val drugViewModel: DrugViewModel by viewModels()
    private val binding get() = _binding!!
    private val args by navArgs<TreatmentUpdateArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTreatmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        binding.drugNameUpdate.setText(args.currentDrug.name)
        binding.offenUpdate.setText(args.currentDrug.timesPerDay.toString())
        binding.descriptionUpdate.setText(args.currentDrug.description)
        val hour = binding.timeInputUpdate.hour
        val minute = binding.timeInputUpdate.minute
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        val time = calendar.timeInMillis
        val endDateCalendar = Calendar.getInstance()
        endDateCalendar.set(
            binding.endDatePickerUpdate.year,
            binding.endDatePickerUpdate.month,
            binding.endDatePickerUpdate.dayOfMonth
        )
        val endDate = endDateCalendar.timeInMillis

        /*
            val time: Long,
    val repeat: Boolean,
    val endDate: Long,
    val timesPerDay: Int
        */
        binding.addButtonUpdate.setOnClickListener {
            val updateDrug =Drug(args.currentDrug.id,binding.drugNameUpdate.text.toString(),binding.descriptionUpdate.text.toString(),
                time,binding.repeatCheckBoxUpdate.isChecked
                ,endDate,binding.offenUpdate.text.toString().toInt()
                )
            drugViewModel.update(updateDrug)
            findNavController().navigate(R.id.action_treatmentUpdate_to_fragmentList)

        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_bar,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteall){
            drugViewModel.delete(args.currentDrug)
            findNavController().navigate(R.id.action_treatmentUpdate_to_fragmentList)
        }
        return super.onOptionsItemSelected(item)
    }


}