package com.example.apptez.view.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptez.R
import com.example.apptez.databinding.FragmentHomeBinding
import com.example.apptez.view.viewModel.PersonViewModel


class FragmentHome : Fragment() {
    private lateinit var binding:FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.homeNesnesi=this

        val sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val name = sharedPref?.getString("key", "İsim").toString()
        binding.userName.text=name+"!"

        return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    fun navigateFragmentList (){
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentList)

    }
    fun navigateFragmentPharmacy(){
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentPharmacy)

    }
    fun navigateDocDate(){
        findNavController().navigate(R.id.action_fragmentHome_to_fragmentCheckUpList)

    }

    override fun onDestroy() {
        super.onDestroy()

    }

}