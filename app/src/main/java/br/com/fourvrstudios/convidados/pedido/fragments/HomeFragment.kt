package br.com.fourvrstudios.convidados.pedido.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.fourvrstudios.convidados.pedido.R
import br.com.fourvrstudios.convidados.pedido.databinding.FragmentHomeBinding
import br.com.fourvrstudios.convidados.pedido.network.dto.request.FoodRequestDTO
import br.com.fourvrstudios.convidados.pedido.util.MaskFormatUtil
import br.com.fourvrstudios.convidados.pedido.viewmodels.RetrofitViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: RetrofitViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentHomeBinding.bind(view)

        viewModel.findAll()

        binding.buttonSaveFood.setOnClickListener {
            val customerFoodRequestDTO: FoodRequestDTO = FoodRequestDTO(
                binding.textName.text.toString(),
                binding.textPhone.text.toString(),
                binding.textFood.text.toString()
            )

            val requestSaveded = viewModel.save(customerFoodRequestDTO)

            viewModel.response.observe(viewLifecycleOwner, Observer {
                if (it!=null){
                    binding.textNumberRequest.text= "Numero do pedido : ${it.code}"
                    Toast.makeText(context,"Pedido salvo com sucesso!",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Erro ao salvar pedido!",Toast.LENGTH_SHORT).show()
                }
            })
            viewModel.findAll()
        }
    }
}