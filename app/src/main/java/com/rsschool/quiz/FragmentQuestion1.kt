package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuestion1Binding

class FragmentQuestion1 : Fragment() {

    private var _binding: FragmentQuestion1Binding? = null
    private val binding get() = _binding!!

    private var listener: FragmentQuestion1Interface? = null

    private var answer: String? = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentQuestion1Interface
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
        _binding = FragmentQuestion1Binding.inflate(inflater, container, false)

        answer = arguments?.getString(ANSWER)
        binding.nextButton.isEnabled = answer != ""

        binding.nextButton.setOnClickListener {
            listener?.openFragmentQuestion2FromFragmentQuestion1(answer)
        }

        when (arguments?.getString(ANSWER)) {
            "Paris" -> binding.radioGroup.check(R.id.option_one)
            "Oslo" -> binding.radioGroup.check(R.id.option_two)
            "Madrid" -> binding.radioGroup.check(R.id.option_three)
            "London" -> binding.radioGroup.check(R.id.option_four)
            "Moscow" -> binding.radioGroup.check(R.id.option_five)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option_one -> {
                    answer = "Paris"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_two -> {
                    binding.nextButton.isEnabled = true
                    answer =  "Oslo"
                }
                R.id.option_three -> {
                    answer =  "Madrid"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_four -> {
                    answer =  "London"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_five -> {
                    answer =  "Moscow"
                    binding.nextButton.isEnabled = true
                }

            }
        }

        return binding.root
    }

    interface FragmentQuestion1Interface {
        fun openFragmentQuestion2FromFragmentQuestion1(answer: String?)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(answer: String?): FragmentQuestion1 {
            val fragment = FragmentQuestion1()
            val args = Bundle()
            args.putString(ANSWER, answer)
            fragment.arguments = args
            return fragment
        }

        private const val ANSWER = "ANSWER"
    }


}