package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore:Int):ViewModel() {
    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _onPlayAgain = MutableLiveData<Boolean>()
    val onPlayerAgain: LiveData<Boolean>
        get() = _onPlayAgain

    init {
        _score.value = finalScore
    }

    fun onPlayAgain(){
        _onPlayAgain.value = true

    }

    fun onPlayAgainComplete(){
        _onPlayAgain.value = false
    }

}