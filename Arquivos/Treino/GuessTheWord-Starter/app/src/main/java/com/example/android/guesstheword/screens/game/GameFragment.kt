package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.game_fragment,container,false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        onObserve()
        onListener()
        return binding.root
    }

    private fun onObserve(){
        viewModel.word.observe(viewLifecycleOwner, Observer {
            binding.wordText.text = viewModel.word.value
        })
        viewModel.score.observe(viewLifecycleOwner, Observer {
            binding.scoreText.text = viewModel.score.value.toString()
        })
        viewModel.endGame.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.score_destination)
        })
    }

    private fun onListener(){
        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener {  }
    }

    private fun onSkip() {
        viewModel.skip()
    }

    private fun onCorrect() {

        viewModel.correct()
    }





    private fun updateWordText() {
        binding.wordText.text = viewModel.word.value
    }
    private fun updateScoreText() {
        //binding.scoreText.text = score.toString()
    }
}
