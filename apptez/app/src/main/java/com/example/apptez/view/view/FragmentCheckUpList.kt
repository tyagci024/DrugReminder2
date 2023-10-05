package com.example.apptez.view.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptez.R
import com.example.apptez.databinding.FragmentCheckUpListBinding
import com.example.apptez.databinding.FragmentListBinding
import com.example.apptez.view.adapter.DateAdapter
import com.example.apptez.view.adapter.RwAdapter
import com.example.apptez.view.model.CheckUpDate
import com.example.apptez.view.viewModel.DateViewModel
import com.example.apptez.view.viewModel.DrugViewModel


class FragmentCheckUpList : Fragment() {
    private lateinit var binding : FragmentCheckUpListBinding
    private lateinit var dateAdapter: DateAdapter
    private val dateViewModel: DateViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_check_up_list, container, false)
        binding.listNesnesi=this

        dateViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dateAdapter= DateAdapter(dateViewModel)
                binding.adapter=dateAdapter
                dateAdapter.updateDate(it)

                if(it.size==0){
                    Toast.makeText(context,"liste boş", Toast.LENGTH_LONG).show()
                }
            }
        })
        binding.floatingActionButtonCheck.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentCheckUpList_to_fragmentCheckUpAdd)

        }

        setHasOptionsMenu(true)


        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_bar,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteall){
            dateViewModel.removeAll()
        }
        return super.onOptionsItemSelected(item)
    }




}