package com.example.apptez.view.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptez.R
import com.example.apptez.databinding.FragmentPersonalInfoBinding
import com.example.apptez.view.model.Drug
import com.example.apptez.view.model.Person
import com.example.apptez.view.viewModel.DrugViewModel
import com.example.apptez.view.viewModel.PersonViewModel


class FragmentPersonalInfo : Fragment() {


    private val personViewModel: DrugViewModel by viewModels()
    private var personUuid:Int=0


    private var _binding:FragmentPersonalInfoBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {binding.buttonSave.setOnClickListener {

        }
            putString("anahtar", "değer")
            apply()
        }*/



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.apply()


        /*  personViewModel.personData.observe(viewLifecycleOwner,{
              it?.let {
                  binding.personName.hint=



              }
          })*/
        binding.buttonSave.setOnClickListener {
            val age = binding.personAge.text.toString()
            val adres=binding.adres.text.toString()
            val blood= binding.spinnerBloodGroup.text.toString()
            val gsm=binding.pGSM.text.toString()
            val userName = binding.personName.text.toString()
            editor.putString("key",userName )
            editor.putString("age",age)
            editor.putString("adres",adres)
            editor.putString("blood",blood)
            editor.putString("gsm",gsm)


            editor.apply()

            findNavController().navigate(R.id.action_fragmentPersonalInfo_to_fragmentAdmin)


        }
        return view    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }


}