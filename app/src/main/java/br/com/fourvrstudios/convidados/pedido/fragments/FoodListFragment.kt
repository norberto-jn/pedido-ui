package br.com.fourvrstudios.convidados.pedido.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import br.com.fourvrstudios.convidados.pedido.MainActivity
import br.com.fourvrstudios.convidados.pedido.R
import br.com.fourvrstudios.convidados.pedido.adapter.ItemAdapter
import br.com.fourvrstudios.convidados.pedido.databinding.FragmentFoodItemBinding
import br.com.fourvrstudios.convidados.pedido.databinding.FragmentFoodListBinding
import br.com.fourvrstudios.convidados.pedido.databinding.FragmentHomeBinding
import br.com.fourvrstudios.convidados.pedido.network.dto.response.FoodResponseDTO
import br.com.fourvrstudios.convidados.pedido.viewmodels.RetrofitViewModel

class FoodListFragment : Fragment(R.layout.fragment_food_list) {

    lateinit var recyclerView:RecyclerView
    private lateinit var binding: FragmentFoodListBinding
    private val viewModel: RetrofitViewModel by activityViewModels()
    var foodList:ArrayList<FoodResponseDTO> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentFoodListBinding.bind(view)
        recyclerView = binding.recyclerView
        var layoutManager:RecyclerView.LayoutManager =LinearLayoutManager(context)
        recyclerView.setLayoutManager(layoutManager)

        viewModel.responseList.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                for (foodItem in it){
                    foodList.add(FoodResponseDTO(foodItem.code,foodItem.name,foodItem.phone,foodItem.food))
                }
            }else{
                Toast.makeText(context,"Erro!", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.adapter=ItemAdapter(context,foodList,viewModel)
        if (foodList.size == 0){
            binding.textView.text="Vazio"
        }else{
            binding.textView.text=" "
        }
        foodList.clear()

    }
}