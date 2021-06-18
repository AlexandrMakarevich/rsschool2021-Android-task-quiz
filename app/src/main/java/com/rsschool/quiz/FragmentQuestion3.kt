package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuestion3Binding

class FragmentQuestion3 : Fragment() {

    private var _binding: FragmentQuestion3Binding? = null
    private val binding get() = _binding!!

    private var listener: FragmentQuestion3Interface? = null

    private var answer: String? = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentQuestion3Interface
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
        _binding = FragmentQuestion3Binding.inflate(inflater, container, false)

        answer = arguments?.getString(ANSWER)
        binding.nextButton.isEnabled = answer != ""

        binding.previousButton.setOnClickListener {
            listener?.openFragmentQuestion2FromFragmentQuestion3(answer)
        }

        binding.nextButton.setOnClickListener {
            listener?.openFragmentQuestion4FromFragmentQuestion3(answer)
        }


        when (arguments?.getString(ANSWER)) {
            "Green" -> binding.radioGroup.check(R.id.option_one)
            "Red" -> binding.radioGroup.check(R.id.option_two)
            "Yellow" -> binding.radioGroup.check(R.id.option_three)
            "Brown" -> binding.radioGroup.check(R.id.option_four)
            "Blue" -> binding.radioGroup.check(R.id.option_five)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option_one -> {
                    answer = "Green"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_two -> {
                    binding.nextButton.isEnabled = true
                    answer =  "Red"
                }
                R.id.option_three -> {
                    answer =  "Yellow"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_four -> {
                    answer =  "Brown"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_five -> {
                    answer =  "Blue"
                    binding.nextButton.isEnabled = true
                }

            }
        }

        return binding.root
    }

    interface FragmentQuestion3Interface {
        fun openFragmentQuestion2FromFragmentQuestion3(answer: String?)
        fun openFragmentQuestion4FromFragmentQuestion3(answer: String?)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(answer: String?): FragmentQuestion3 {
            val fragment = FragmentQuestion3()
            val args = Bundle()
            args.putString(ANSWER, answer)
            fragment.arguments = args
            return fragment
        }

        private const val ANSWER = "ANSWER"
    }

}