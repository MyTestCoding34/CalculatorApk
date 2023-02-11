package ru.appromal.calculatorapk.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.appromal.calculatorapk.domain.usecase.UCUnite

class MainViewModelFactory : ViewModelProvider.Factory {
    private val unite: UCUnite = UCUnite()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(unite = unite) as T
    }
}