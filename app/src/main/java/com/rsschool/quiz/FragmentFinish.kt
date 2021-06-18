package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentFinishBinding

class FragmentFinish : Fragment() {

    private var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!

    private var listener: FragmentFinishInterface? = null

    private var sum = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentFinishInterface
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)

        if (arguments?.getString(CAPITAL) == "London") sum += 1
        if (arguments?.getString(CHAMPION) == "France") sum += 1
        if (arguments?.getString(COLOR) == "Brown") sum += 1
        if (arguments?.getString(COMPANY) == "Toyota") sum += 1
        if (arguments?.getString(POPULATION) == "China") sum += 1

        binding.resultTextview.text = "Your result: $sum from 5"


        binding.shareButton.setOnClickListener {
            listener?.shareFromFragmentFinish()
        }

        binding.backButton.setOnClickListener {
            listener?.repeateFromFragmentFinish()
        }

        binding.closeButton.setOnClickListener {
            listener?.closeFromFragmentFinish()
        }


        return binding.root
    }

    interface FragmentFinishInterface {
        fun shareFromFragmentFinish()
        fun closeFromFragmentFinish()
        fun repeateFromFragmentFinish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(
            capital: String?,
            champion: String?,
            color: String?,
            company: String?,
            population: String?
        ): FragmentFinish {
            val fragment = FragmentFinish()
            val args = Bundle()
            args.putString(CAPITAL, capital)
            args.putString(CHAMPION, champion)
            args.putString(COLOR, color)
            args.putString(COMPANY, company)
            args.putString(POPULATION, population)
            fragment.arguments = args
            return fragment
        }

        private const val CAPITAL = "CAPITAL"
        private const val CHAMPION = "CHAMPION"
        private const val COLOR = "COLOR"
        private const val COMPANY = "COMPANY"
        private const val POPULATION = "POPULATION"

    }

}