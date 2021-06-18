package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuestion2Binding

class FragmentQuestion2 : Fragment() {

    private var _binding: FragmentQuestion2Binding? = null
    private val binding get() = _binding!!

    private var listener: FragmentQuestion2.FragmentQuestion2Interface? = null

    private var answer: String? = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentQuestion2.FragmentQuestion2Interface
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestion2Binding.inflate(inflater, container, false)

        answer = arguments?.getString(ANSWER)
        binding.nextButton.isEnabled = answer != ""

        binding.previousButton.setOnClickListener {
            listener?.openFragmentQuestion1FromFragmentQuestion2(answer)
        }
        binding.nextButton.setOnClickListener {
            listener?.openFragmentQuestion3FromFragmentQuestion2(answer)
        }

        when (arguments?.getString(ANSWER)) {
            "Russia" -> binding.radioGroup.check(R.id.option_one)
            "Italy" -> binding.radioGroup.check(R.id.option_two)
            "England" -> binding.radioGroup.check(R.id.option_three)
            "Germany" -> binding.radioGroup.check(R.id.option_four)
            "France" -> binding.radioGroup.check(R.id.option_five)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option_one -> {
                    answer = "Russia"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_two -> {
                    binding.nextButton.isEnabled = true
                    answer =  "Italy"
                }
                R.id.option_three -> {
                    answer =  "England"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_four -> {
                    answer =  "Germany"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_five -> {
                    answer =  "France"
                    binding.nextButton.isEnabled = true
                }

            }
        }


        return binding.root
    }

    interface FragmentQuestion2Interface {
        fun openFragmentQuestion1FromFragmentQuestion2(answer: String?)
        fun openFragmentQuestion3FromFragmentQuestion2(answer: String?)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(answer: String?): FragmentQuestion2 {
            val fragment = FragmentQuestion2()
            val args = Bundle()
            args.putString(ANSWER, answer)
            fragment.arguments = args
            return fragment
        }

        private const val ANSWER = "ANSWER"
    }

}