package com.rsschool.quiz

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    FragmentQuestion1.FragmentQuestion1Interface,
    FragmentQuestion2.FragmentQuestion2Interface,
    FragmentQuestion3.FragmentQuestion3Interface,
    FragmentQuestion4.FragmentQuestion4Interface,
    FragmentQuestion5.FragmentQuestion5Interface,
    FragmentFinish.FragmentFinishInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSharedPreference()
        startFragmentQuestion1()

    }

    private fun startFragmentQuestion1() {
        val fragmentQuestion1 = FragmentQuestion1.newInstance(sharedPref.getString("CAPITAL", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion1)
        transaction.commit()
    }

    override fun openFragmentQuestion2FromFragmentQuestion1(answer: String?) {
        sharedPref.edit().putString("CAPITAL", answer).apply()
        val fragmentQuestion2 = FragmentQuestion2.newInstance(sharedPref.getString("CHAMPION", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion2)
        transaction.commit()
    }

    override fun openFragmentQuestion1FromFragmentQuestion2(answer: String?) {
        sharedPref.edit().putString("CHAMPION", answer).apply()
        val fragmentQuestion1 = FragmentQuestion1.newInstance(sharedPref.getString("CAPITAL", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion1)
        transaction.commit()
    }

    override fun openFragmentQuestion3FromFragmentQuestion2(answer: String?) {
        sharedPref.edit().putString("CHAMPION", answer).apply()
        val fragmentQuestion3 = FragmentQuestion3.newInstance(sharedPref.getString("COLOR", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion3)
        transaction.commit()
    }

    override fun openFragmentQuestion2FromFragmentQuestion3(answer: String?) {
        sharedPref.edit().putString("COLOR", answer).apply()
        val fragmentQuestion2 = FragmentQuestion2.newInstance(sharedPref.getString("CHAMPION", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion2)
        transaction.commit()
    }

    override fun openFragmentQuestion4FromFragmentQuestion3(answer: String?) {
        sharedPref.edit().putString("COLOR", answer).apply()
        val fragmentQuestion4 = FragmentQuestion4.newInstance(sharedPref.getString("COMPANY", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion4)
        transaction.commit()
    }

    override fun openFragmentQuestion3FromFragmentQuestion4(answer: String?) {
        sharedPref.edit().putString("COMPANY", answer).apply()
        val fragmentQuestion3 = FragmentQuestion3.newInstance(sharedPref.getString("COLOR", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion3)
        transaction.commit()
    }

    override fun openFragmentQuestion5FromFragmentQuestion4(answer: String?) {
        sharedPref.edit().putString("COMPANY", answer).apply()
        val fragmentQuestion5 =
            FragmentQuestion5.newInstance(sharedPref.getString("POPULATION", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion5)
        transaction.commit()
    }

    override fun openFragmentQuestion4FromFragmentQuestion5(answer: String?) {
        sharedPref.edit().putString("POPULATION", answer).apply()
        val fragmentQuestion4 = FragmentQuestion4.newInstance(sharedPref.getString("COMPANY", ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion4)
        transaction.commit()
    }

    override fun openFragmentFinishFromFragmentQuestion5(answer: String?) {
        sharedPref.edit().putString("POPULATION", answer).apply()
        val fragmentFinish = FragmentFinish.newInstance(
            sharedPref.getString("CAPITAL", ""),
            sharedPref.getString("CHAMPION", ""),
            sharedPref.getString("COLOR", ""),
            sharedPref.getString("COMPANY", ""),
            sharedPref.getString("POPULATION", "")
        )
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentFinish)
        transaction.commit()
    }

    override fun shareFromFragmentFinish() {
        TODO("Not yet implemented")
    }

    override fun repeateFromFragmentFinish() {
        with(sharedPref.edit()) {
            putString("CAPITAL", "")
            putString("CHAMPION", "")
            putString("COLOR", "")
            putString("COMPANY", "")
            putString("POPULATION", "")
            apply()
        }
        startFragmentQuestion1()
    }

    override fun closeFromFragmentFinish() {
        finish()
    }

    private fun setSharedPreference() {
        sharedPref = getSharedPreferences("mySharedPref", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("CAPITAL", "")
            putString("CHAMPION", "")
            putString("COLOR", "")
            putString("COMPANY", "")
            putString("POPULATION", "")
            apply()
        }
    }


}