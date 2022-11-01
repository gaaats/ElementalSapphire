package com.imayi.trainbuilder.gameefolder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imayi.trainbuilder.R
import com.imayi.trainbuilder.databinding.FragmentStartBinding

class StartFragment : Fragment(), GameTask {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    val listLogoEnemy = listOf(
        R.drawable.stone,
        R.drawable.stone1,
        R.drawable.bronze,
    )

    private var mGameViev: GameViev? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnStart.setOnClickListener {
            mGameViev =
                GameViev(requireContext(), this, R.drawable.dwarf, listLogoEnemy)
            mGameViev!!.setBackgroundResource(R.drawable.back)
            mGameViev!!.background.alpha = 100
            binding.root.addView(mGameViev)
            binding.btnStart.visibility = View.GONE
            binding.tvScoreGame1.visibility = View.GONE
            binding.imgLogoGame1.visibility = View.GONE
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun closeGame(score: Int) {
        val scoreText = "Score : $score"
        binding.root.removeView(mGameViev)
        binding.tvScoreGame1.text = scoreText
        binding.btnStart.visibility = View.VISIBLE
        binding.tvScoreGame1.visibility = View.VISIBLE
        binding.imgLogoGame1.visibility = View.VISIBLE
        mGameViev = null

        findNavController().navigate(R.id.action_startFragment_to_loseFragment)
    }

}