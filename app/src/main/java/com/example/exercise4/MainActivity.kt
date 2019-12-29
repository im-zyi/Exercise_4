package com.example.exercise4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c:Calendar = Calendar.getInstance();
        var mYear = c.get(Calendar.YEAR)
        var mMonth = c.get(Calendar.MONTH)
        var mDay = c.get(Calendar.DAY_OF_MONTH)

        txtDOB.setOnClickListener(){
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                //display selected date om text view
                txtDOB.setText("%d-%d-%d".format(dayOfMonth,monthOfYear+1,year))
                mYear=year
                mMonth=monthOfYear
                mDay = dayOfMonth
            },mYear, mMonth, mDay)
            dpd.show()

        }
        btnCalculate.setOnClickListener(){
            val age:Int=getAge(mYear,mMonth,mDay)

            displayMaxTransferAmt(age)
        }
        btnReset.setOnClickListener(){
            txtAge.text=""
            txtDOB.text.clear()
            txtMaxTransAmt.text=""
            txtMinSaving.text=""

        }
       
    }
    fun getAge(yearOfBirth:Int,monthOfBirth:Int, dayOfBirth:Int):Int{
        val dob:Calendar = Calendar.getInstance()
        val today:Calendar = Calendar.getInstance()
        dob.set(yearOfBirth, monthOfBirth, dayOfBirth)
        var age:Int = today.get(Calendar.YEAR)-dob.get(Calendar.YEAR)

        if(today.get(Calendar.DAY_OF_YEAR)<dob.get(Calendar.DAY_OF_YEAR))
            age--

        val ageInt:Int = age
        txtAge.text=ageInt.toString()
        return age
    }
    fun displayMaxTransferAmt(age:Int){
        var minSaving:Double =0.00
        var maxTransferAmt:Double
        if(age>=16&&age<=20){
            minSaving=5000.00
        }
        else if(age>=21 && age <= 25){
            minSaving = 14000.00
        }
        else if(age>=26 && age <= 30){
            minSaving = 29000.00
        }
        else if(age>=31 && age <= 35){
            minSaving = 50000.00
        }
        else if(age>=36 && age <= 40){
            minSaving = 78000.00
        }
        else if(age>=41 && age <= 45){
            minSaving = 116000.00
        }
        else if(age>=46 && age <= 50){
            minSaving = 165000.00
        }
        else if(age>=51 && age <= 55){
            minSaving = 228000.00
        }
        maxTransferAmt=minSaving*0.3
        txtMinSaving.text = minSaving.toString()
        txtMaxTransAmt.text = maxTransferAmt.toString()
    }
}
