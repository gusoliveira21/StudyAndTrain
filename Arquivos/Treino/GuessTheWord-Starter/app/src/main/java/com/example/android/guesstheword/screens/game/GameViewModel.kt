package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment

class GameViewModel: ViewModel() {

    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private var _endGame = MutableLiveData<Boolean>()
    val endGame: LiveData<Boolean>
        get() = _endGame

    init {
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()
        _endGame.value = finalDaLista()

    }

    override fun onCleared() {
        super.onCleared()
        Log.e("Game","onCleared()")
    }

    private lateinit var wordList: MutableList<String>

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            _word.value = wordList.removeAt(0)
        }
    }

    fun skip() {
        nextWord()
        _score.value = _score.value?.minus(1)
    }

    fun correct() {
        if(!finalDaLista()){
            _score.value = _score.value?.plus(1)
            nextWord()
        }
    }

    private fun finalDaLista():Boolean {
        if (wordList.size == 0) {
            _endGame.value = true
            return true
        }
        return false
    }

    fun endGame() = { _endGame.value = true }

    //fun getScore() = score.value?:0


}