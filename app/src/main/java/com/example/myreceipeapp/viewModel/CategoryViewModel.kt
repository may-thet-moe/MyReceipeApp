package com.example.myreceipeapp.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myreceipeapp.model.Category
import com.example.myreceipeapp.receipeService
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _categoryState = mutableStateOf(CategoryState())
    val categoryState: State<CategoryState> = _categoryState

    init {
        fetchCategory()
    }

    private fun fetchCategory(){
        viewModelScope.launch{
            try {
                val response = receipeService.getCategory()
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "",
                    categories = response.categories
                )
            }catch (e: Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = e.toString(),
                    categories = emptyList()
                )
            }
        }
    }

    data class CategoryState(
        val loading: Boolean = true,
        val error: String = "",
        val categories: List<Category> = emptyList()
    )
}