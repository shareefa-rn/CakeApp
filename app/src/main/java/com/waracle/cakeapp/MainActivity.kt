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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

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

        val adapter = CakeAdapter { cake -> showDescriptionDialog(cake) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        viewModel.observeCakeLiveData().observe(this, Observer { movieList ->
            adapter.setCakeList(movieList)
        })

    /*    viewModel.isLoading.observe(this, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
*/
/*
        viewModel.isLoading.observe(this, { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading // Set refresh state
        })
*/
/*
        viewModel.errorMessage.observe(this, { message ->
            if (message != null) {
                // Display error message (e.g., using a Snackbar)
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
            }
        })*/
    }
    private fun refreshCakes() {
        // Implement logic to reload the cake list
        viewModel.fetchCakes()
    }
    private fun showDescriptionDialog(cake: Cake) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(cake.title)
            .setMessage(cake.desc)
            .setPositiveButton("OK", null)
            .create()
        dialog.show()
    }
}
