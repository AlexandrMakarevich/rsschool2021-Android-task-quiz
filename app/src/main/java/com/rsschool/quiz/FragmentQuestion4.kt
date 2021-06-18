package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuestion4Binding

class FragmentQuestion4 : Fragment() {

    private var _binding: FragmentQuestion4Binding? = null
    private val binding get() = _binding!!

    private var listener: FragmentQuestion4Interface? = null

    private var answer: String? = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentQuestion4Interface
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
        _binding = FragmentQuestion4Binding.inflate(inflater, container, false)

        answer = arguments?.getString(ANSWER)
        binding.nextButton.isEnabled = answer != ""

        binding.previousButton.setOnClickListener {
            listener?.openFragmentQuestion3FromFragmentQuestion4(answer)
        }

        binding.nextButton.setOnClickListener {
            listener?.openFragmentQuestion5FromFragmentQuestion4(answer)
        }


        when (arguments?.getString(ANSWER)) {
            "Ford" -> binding.radioGroup.check(R.id.option_one)
            "Geely" -> binding.radioGroup.check(R.id.option_two)
            "Toyota" -> binding.radioGroup.check(R.id.option_three)
            "BMW" -> binding.radioGroup.check(R.id.option_four)
            "Kia" -> binding.radioGroup.check(R.id.option_five)
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option_one -> {
                    answer = "Ford"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_two -> {
                    binding.nextButton.isEnabled = true
                    answer =  "Geely"
                }
                R.id.option_three -> {
                    answer =  "Toyota"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_four -> {
                    answer =  "BMW"
                    binding.nextButton.isEnabled = true
                }
                R.id.option_five -> {
                    answer =  "Kia"
                    binding.nextButton.isEnabled = true
                }

            }
        }

        return binding.root
    }

    interface FragmentQuestion4Interface {
        fun openFragmentQuestion3FromFragmentQuestion4(answer: String?)
        fun openFragmentQuestion5FromFragmentQuestion4(answer: String?)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(answer: String?): FragmentQuestion4 {
            val fragment = FragmentQuestion4()
            val args = Bundle()
            args.putString(ANSWER, answer)
            fragment.arguments = args
            return fragment
        }

        private const val ANSWER = "ANSWER"
    }

}