package com.aangles_cmestas_mquispeyn.laboratorio06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aangles_cmestas_mquispeyn.laboratorio06.R
import com.aangles_cmestas_mquispeyn.laboratorio06.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private val viewModel by viewModels<ActivityViewModel>()
    private val adapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = setContentView(this, R.layout.activity_main)
        setProductsAdapter()
        getProducts()
        setProgressBarAccordingToLoadState()

    }

    private fun setProductsAdapter() {
        dataBinding.recyclerView.adapter = adapter
    }

    private fun getProducts() {
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setProgressBarAccordingToLoadState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                dataBinding.progressBar.isVisible = it.append is LoadState.Loading
            }
        }
    }

}