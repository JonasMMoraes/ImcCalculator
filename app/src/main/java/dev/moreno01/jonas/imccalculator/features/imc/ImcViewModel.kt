package dev.moreno01.jonas.imccalculator.features.imc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImcViewModel : ViewModel() {
    private var _imc = MutableLiveData(0.0)

    val imc: LiveData<Double?>
        get() = _imc

    fun calculateImc(height: Double, weight: Double) {
        _imc.value = weight / (height * height)
    }
}