package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment

class GameViewModel : ViewModel() {
    companion object {
        //Time when the game is over
        private const val DONE = 0L
        //Countdown time interval
        private const val ONE_SECOND = 1000L
        //Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }
    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    var currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

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

        timer = object:CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                //onGameFinish()
            }

        }
        timer.start()


    }




    override fun onCleared() {
        super.onCleared()
        timer.cancel()
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
        if (wordList.isEmpty())
            resetList()
        else
            _word.value = wordList.removeAt(0)


    }

    fun skip() {
        nextWord()
        _score.value = _score.value?.minus(1)
    }

    fun correct() {
        if (!finalDaLista()) {
            _score.value = _score.value?.plus(1)
            nextWord()
        }
    }

    private fun finalDaLista(): Boolean {
        if (wordList.size == 0) {
            _endGame.value = true
            return true
        }
        return false
    }

    fun endGame() = { _endGame.value = true }

    //fun getScore() = score.value?:0


}