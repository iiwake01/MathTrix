package com.mtcdb.stem.mathtrix.calculator.options

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mtcdb.stem.mathtrix.R

class SimpleInterestFragment : Fragment() {

    private lateinit var principalEditText: EditText
    private lateinit var rateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var explainButton: Button
    private  lateinit var descriptionTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(com.calculator.calculatoroptions.R.layout.fragment_simple_interest, container, false)

        principalEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextPrincipal)
        rateEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextRate)
        timeEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextTime)
        calculateButton = rootView.findViewById(com.calculator.calculatoroptions.R.id.buttonCalculate)
        resultTextView = rootView.findViewById(com.calculator.calculatoroptions.R.id.textViewResult)
        descriptionTextView = rootView.findViewById(R.id.tVCalculationOptionDescription)
        explainButton = rootView.findViewById(com.calculator.calculatoroptions.R.id.explainButton)

        calculateButton.setOnClickListener {
            calculateButton.clearFocus()
            calculateSimpleInterest()
        }

        explainButton.setOnClickListener {
            showExplanationDialog()
            calculateSimpleInterest()
        }

        val description = """
            Simple Interest is calculated using the formula:
            
            I = P * R * T / 100
            
            Where:
            I = Simple Interest
            P = Principal amount (initial amount of money)
            R = Rate of interest per period
            T = Time (in years)
            
            To calculate Simple Interest, multiply the Principal amount (P), Rate of interest (R), and Time (T), and then divide by 100.
            
            Example:
            Suppose you invest $1,000 at an annual interest rate of 5% for 2 years.
            
            I = 1000 * 5 * 2 / 100
            I = 100
            
            Therefore, the Simple Interest earned is $100.
        """.trimIndent()

        descriptionTextView.text = description


        return rootView
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateSimpleInterest() {
        val principal = principalEditText.text.toString().toDoubleOrNull() ?: 0.0
        val rate = rateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val time = timeEditText.text.toString().toDoubleOrNull() ?: 0.0

        val simpleInterest = (principal * rate * time) / 100.0

        resultTextView.text = simpleInterest.toString()
    }

    private fun showExplanationDialog() {

        val principal = principalEditText.text.toString().toDoubleOrNull() ?: 0.0
        val rate = rateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val time = timeEditText.text.toString().toDoubleOrNull() ?: 0.0

        val multiplied = principal * rate * time
        val simpleInterest = (principal * rate * time) / 100.0

        val explanation = """
            Given:
                Principal Amount = $principal
                Interest Rate = $rate
                Time = $time
                
            Formula:
                I = P * R * T / 100
                
            Solution:
                I = $principal * $rate * $time / 100
                I = $multiplied / 100
                I = $simpleInterest
                
            Therefore, the Simple Interest is $simpleInterest    
        """.trimIndent()

        // Display explanation in a custom dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Simple Interest")
            .setMessage(explanation)
            .setPositiveButton("OK", null)
            .show()
    }
}
