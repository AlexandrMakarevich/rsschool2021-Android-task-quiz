package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.content.SharedPreferences
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
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
        val fragmentQuestion1 = FragmentQuestion1.newInstance(sharedPref.getString(PrefNames.CAPITAL.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion1)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.deep_orange_100)
    }

    override fun openFragmentQuestion2FromFragmentQuestion1(answer: String?) {
        sharedPref.edit().putString(PrefNames.CAPITAL.name, answer).apply()
        val fragmentQuestion2 = FragmentQuestion2.newInstance(sharedPref.getString(PrefNames.CHAMPION.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion2)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.yellow_100)
    }

    override fun openFragmentQuestion1FromFragmentQuestion2(answer: String?) {
        sharedPref.edit().putString(PrefNames.CHAMPION.name, answer).apply()
        val fragmentQuestion1 = FragmentQuestion1.newInstance(sharedPref.getString(PrefNames.CAPITAL.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion1)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.deep_orange_100)
    }

    override fun openFragmentQuestion3FromFragmentQuestion2(answer: String?) {
        sharedPref.edit().putString(PrefNames.CHAMPION.name, answer).apply()
        val fragmentQuestion3 = FragmentQuestion3.newInstance(sharedPref.getString(PrefNames.COLOR.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion3)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.light_green_100)
    }

    override fun openFragmentQuestion2FromFragmentQuestion3(answer: String?) {
        sharedPref.edit().putString(PrefNames.COLOR.name, answer).apply()
        val fragmentQuestion2 = FragmentQuestion2.newInstance(sharedPref.getString(PrefNames.CHAMPION.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion2)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.yellow_100)
    }

    override fun openFragmentQuestion4FromFragmentQuestion3(answer: String?) {
        sharedPref.edit().putString(PrefNames.COLOR.name, answer).apply()
        val fragmentQuestion4 = FragmentQuestion4.newInstance(sharedPref.getString(PrefNames.COMPANY.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion4)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.cyan_100)
    }

    override fun openFragmentQuestion3FromFragmentQuestion4(answer: String?) {
        sharedPref.edit().putString(PrefNames.COMPANY.name, answer).apply()
        val fragmentQuestion3 = FragmentQuestion3.newInstance(sharedPref.getString(PrefNames.COLOR.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion3)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.light_green_100)
    }

    override fun openFragmentQuestion5FromFragmentQuestion4(answer: String?) {
        sharedPref.edit().putString(PrefNames.COMPANY.name, answer).apply()
        val fragmentQuestion5 =
            FragmentQuestion5.newInstance(sharedPref.getString(PrefNames.POPULATION.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion5)
        transaction.commit()
        window.statusBarColor = resources.getColor(R.color.deep_purple_100)
    }

    override fun openFragmentQuestion4FromFragmentQuestion5(answer: String?) {
        sharedPref.edit().putString(PrefNames.POPULATION.name, answer).apply()
        val fragmentQuestion4 = FragmentQuestion4.newInstance(sharedPref.getString(PrefNames.COMPANY.name, ""))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentQuestion4)
        transaction.commit()
        window.statusBarColor =
            if (Build.VERSION.SDK_INT >= 23) this.getColor(R.color.cyan_100) else this.resources.getColor(
                R.color.cyan_100
            )
    }

    override fun openFragmentFinishFromFragmentQuestion5(answer: String?) {
        sharedPref.edit().putString("POPULATION", answer).apply()
        val fragmentFinish = FragmentFinish.newInstance(
            sharedPref.getString(PrefNames.CAPITAL.name, ""),
            sharedPref.getString(PrefNames.CHAMPION.name, ""),
            sharedPref.getString(PrefNames.COLOR.name, ""),
            sharedPref.getString(PrefNames.COMPANY.name, ""),
            sharedPref.getString(PrefNames.POPULATION.name, "")
        )
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragmentFinish)
        transaction.commit()
    }

    override fun shareFromFragmentFinish(sum: Int) {

        val text = """
Your result: $sum from 5 
             
1) The capital of Great Britain is ...  
Your answer: ${sharedPref.getString("CAPITAL", "")} 
              
2) The current champion of the Football World is ... 
Your answer: ${sharedPref.getString("CHAMPION", "")}

3) What color is not in the rainbow?
Your answer: ${sharedPref.getString("COLOR", "")}

4) Which company is Japanese?
Your answer: ${sharedPref.getString("COMPANY", "")}
                
5) The largest population in the world in ... 
Your answer: ${sharedPref.getString("POPULATION", "")}"""

        val intentEmail = Intent(ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(EXTRA_EMAIL, "123456qwerty@gmail.com")
            putExtra(EXTRA_SUBJECT, "Dear Friend, ")
            putExtra(EXTRA_TEXT, text)
        }
        startActivity(intentEmail)
    }


    override fun repeateFromFragmentFinish() {
        with(sharedPref.edit()) {
            putString(PrefNames.CAPITAL.name, "")
            putString(PrefNames.CHAMPION.name, "")
            putString(PrefNames.COLOR.name, "")
            putString(PrefNames.COMPANY.name, "")
            putString(PrefNames.POPULATION.name, "")
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
            putString(PrefNames.CAPITAL.name, "")
            putString(PrefNames.CHAMPION.name, "")
            putString(PrefNames.COLOR.name, "")
            putString(PrefNames.COMPANY.name, "")
            putString(PrefNames.POPULATION.name, "")
            apply()
        }
    }

}
