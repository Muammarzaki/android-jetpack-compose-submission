package com.dicoding.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.Country
import com.dicoding.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _countries = MutableStateFlow(DataSource.countries)
    val countries = _countries.asStateFlow()

    private val _listFlagsIds = MutableStateFlow(emptyList<Int>())
    val listFlagsIds = _listFlagsIds.asStateFlow()

    init {
        viewModelScope.launch {
            countries.collectLatest {
                val newFlagIds = DataSource.drawableIds.filter { drawableId ->
                    drawableId !in it.map { country -> country.countryFlagId }
                }
                Log.d("FlagId", "Flags id has been updated ${newFlagIds.size}")
                _listFlagsIds.value = newFlagIds
            }
        }
    }

    fun deleteWithIndex(element: Country) {
        viewModelScope.launch {
            Log.d("Delete", "Delete $element")
            _countries.emit(_countries.value.toMutableList().apply {
                remove(element)
            })
        }
    }

    fun add(element: Country) {
        viewModelScope.launch {
            _countries.emit(_countries.value.toMutableList().apply {
                add(element)
            })
        }
    }
}