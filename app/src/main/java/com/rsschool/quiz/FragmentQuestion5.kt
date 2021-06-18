package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuestion5Binding

class FragmentQuestion5 : Fragment() {

    private var _binding: FragmentQuestion5Binding? = null
    private val binding get() = _binding!!

    private var listener: FragmentQuestion5Interface? = null

    private var answer: String? = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentQuestion5Interface
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
        _binding = FragmentQuestion5Binding.inflate(inflater, container, false)

        answer = arguments?.getString(ANSWER)
        binding.nextButton.isEnabled = answer != ""

        binding.previousButton.setOnClickListener {
            listener?.openFragmentQuestion4FromFragmentQuestion5(answer)
        }

        binding.nextButton.setOnClickListener {
            listener?.openFragmentFinishFromFragmentQuestion5(answer)
        }


        when (arguments?.getString(ANSWER)) {
            "China" -> binding.radioGroup.check(R.id.option_one)
            "USA" -> binding.radioGroup.check(R.id.option_two)
            "Belarus" -> binding.radioGroup.check(R.id.option_three)
            "BMW" -> binding.radioGroup.check(R.id.option_four)
            "Kia" -> binding.radioGroup.check(R.id.option_five)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option_one -> {
                    answer = "China"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_two -> {
                    binding.nextButton.isEnabled = true
                    answer =  "USA"
                }
                R.id.option_three -> {
                    answer =  "Belarus"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_four -> {
                    answer =  "Russia"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_five -> {
                    answer =  "India"
                    binding.nextButton.isEnabled = true
                }

            }
        }

        return binding.root
    }

    interface FragmentQuestion5Interface {
        fun openFragmentQuestion4FromFragmentQuestion5(answer: String?)
        fun openFragmentFinishFromFragmentQuestion5(answer: String?)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(answer: String?): FragmentQuestion5 {
            val fragment = FragmentQuestion5()
            val args = Bundle()
            args.putString(ANSWER, answer)
            fragment.arguments = args
            return fragment
        }

        private const val ANSWER = "ANSWER"
    }

}