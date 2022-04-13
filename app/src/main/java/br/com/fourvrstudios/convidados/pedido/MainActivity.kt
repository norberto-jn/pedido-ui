package br.com.fourvrstudios.convidados.pedido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import br.com.fourvrstudios.convidados.pedido.fragments.FoodItemFragment
import br.com.fourvrstudios.convidados.pedido.fragments.FoodListFragment
import br.com.fourvrstudios.convidados.pedido.fragments.HomeFragment
import br.com.fourvrstudios.convidados.pedido.viewmodels.RetrofitViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView:BottomNavigationView
    private val home=HomeFragment()
    private val foodItem=FoodItemFragment()
    private val foodList=FoodListFragment()
    private  lateinit var viewModel: RetrofitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(home)

        bottomNavigationView=findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> replaceFragment(home)
                R.id.item2 -> replaceFragment(foodItem)
                R.id.item3 -> replaceFragment(foodList)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }


}