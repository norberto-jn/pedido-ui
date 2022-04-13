package br.com.fourvrstudios.convidados.pedido.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.fourvrstudios.convidados.pedido.R
import br.com.fourvrstudios.convidados.pedido.databinding.FragmentFoodItemBinding
import br.com.fourvrstudios.convidados.pedido.databinding.FragmentHomeBinding
import br.com.fourvrstudios.convidados.pedido.viewmodels.RetrofitViewModel

class FoodItemFragment : Fragment(R.layout.fragment_food_item) {

    private lateinit var binding: FragmentFoodItemBinding
    private val viewModel: RetrofitViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFoodItemBinding.bind(view)

        viewModel.findAll()

        binding.buttonFindOneFood.setOnClickListener {

            val numberRequestFood=binding.textSarchString.text.toString().toInt()
            viewModel.findOne(numberRequestFood)

            viewModel.response.observe(viewLifecycleOwner, Observer {
                if (it!=null){
                    binding.textRequestInfo.text=it.name
                }
            })
        }

    }
}