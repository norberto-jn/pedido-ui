package br.com.fourvrstudios.convidados.pedido.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fourvrstudios.convidados.pedido.MainActivity
import br.com.fourvrstudios.convidados.pedido.R
import br.com.fourvrstudios.convidados.pedido.fragments.FoodListFragment
import br.com.fourvrstudios.convidados.pedido.fragments.HomeFragment
import br.com.fourvrstudios.convidados.pedido.network.dto.response.FoodResponseDTO
import br.com.fourvrstudios.convidados.pedido.viewmodels.RetrofitViewModel
import org.jetbrains.annotations.NotNull

class ItemAdapter(private val context:Context?,private val dataset:ArrayList<FoodResponseDTO>,private val retrofitViewModel:RetrofitViewModel):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(private val view:View) :RecyclerView.ViewHolder(view){
        val textViewName :TextView=view.findViewById(R.id.textItemName)
        val textViewFood :TextView=view.findViewById(R.id.textItemFood)
        val textViewNumber :TextView=view.findViewById(R.id.textItemNumber)
        val buttonDelete:ImageView =view.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var adapterLayout=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return  ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(@NotNull holder: ItemViewHolder,position: Int) {

        val item=dataset[position]
        holder.textViewName.setText("Nome : ${item.name}")
        holder.textViewFood.setText("Pedido : ${item.food}")
        holder.textViewNumber.setText("N:${item.code}")
        holder.buttonDelete.setOnClickListener {

            val builder: AlertDialog.Builder =AlertDialog.Builder(context)

            builder.setTitle("Deseja apagar pedido de código ${item.code} ?")

            builder.setPositiveButton("SIM",DialogInterface.OnClickListener { dialogInterface, i ->
                retrofitViewModel.delete(item.code)
                dataset.removeAt(position)
                Toast.makeText(context,"O pedido de código ${item.code} foi deletado com sucesso!.",Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            })

            builder.setNegativeButton("NÃO",DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(context,"Cancelado",Toast.LENGTH_SHORT).show()
            })
            builder.show()

        }
    }

    override fun getItemCount() =  dataset.size

}