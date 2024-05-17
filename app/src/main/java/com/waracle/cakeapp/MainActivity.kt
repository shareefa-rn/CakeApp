package com.waracle.cakeapp
// MainActivity.kt
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.waracle.cakeapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CakeViewModel

    private lateinit var binding: ActivityMainBinding
    //private val cakeViewModel: CakeViewModel by viewModels() // Ensure this is properly initialized

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CakeViewModel::class.java]
        viewModel.fetchCakes()

        val adapter = CakeAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.observeCakeLiveData().observe(this, Observer { movieList ->
            adapter.setCakeList(movieList)
        })
/*
        viewModel.isLoading.observe(this, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.errorMessage.observe(this, { message ->
            if (message != null) {
                // Display error message (e.g., using a Toast)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })*/
    }
}
