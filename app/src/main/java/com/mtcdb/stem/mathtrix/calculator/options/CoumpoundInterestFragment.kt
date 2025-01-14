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
import kotlin.math.pow

class CompoundInterestFragment : Fragment() {

    private lateinit var principalEditText: EditText
    private lateinit var rateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var explainButton: Button
    private lateinit var descriptionTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView =
            inflater.inflate(com.calculator.calculatoroptions.R.layout.fragment_compound_interest, container, false)

        principalEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextPrincipal)
        rateEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextRate)
        timeEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextTime)
        calculateButton = rootView.findViewById(com.calculator.calculatoroptions.R.id.buttonCalculate)
        resultTextView = rootView.findViewById(com.calculator.calculatoroptions.R.id.textViewResult)
        descriptionTextView = rootView.findViewById(R.id.tVCalculationOptionDescription)
        explainButton = rootView.findViewById(com.calculator.calculatoroptions.R.id.explainButton)

        calculateButton.setOnClickListener {
            calculateButton.clearFocus()
            calculateCompoundInterest()
        }

        explainButton.setOnClickListener {
            showExplanationDialog()
            calculateCompoundInterest()
        }

        val description = """
            Compound Interest is calculated using the formula:
            
            A = P * (1 + (R / N))^(N * T)
            
            Where:
            A = Amount after interest
            P = Principal amount (initial amount of money)
            R = Annual interest rate (decimal)
            N = Number of times that interest is compounded per unit T
            T = Time the money is invested or borrowed for, in years
            
            To calculate Compound Interest, use the formula to find the total amount after interest and then subtract the principal amount.
            
            Example:
            Suppose you invest $1,000 at an annual interest rate of 5% compounded quarterly for 2 years.
            
            A = 1000 * (1 + (0.05 / 4))^(4 * 2)
            A = 1104.06
            
            Therefore, the Compound Interest earned is $104.06.
        """.trimIndent()

        descriptionTextView.text = description

        return rootView
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateCompoundInterest() {
        val principal = principalEditText.text.toString().toDoubleOrNull() ?: 0.0
        val rate = rateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val time = timeEditText.text.toString().toDoubleOrNull() ?: 0.0

        val compoundInterest = principal * (1 + rate / 100).pow(time)

        resultTextView.text = (compoundInterest - principal).toString()
    }

    private fun showExplanationDialog() {
        val principal = principalEditText.text.toString().toDoubleOrNull() ?: 0.0
        val rate = rateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val time = timeEditText.text.toString().toDoubleOrNull() ?: 0.0

        val compoundInterest = principal * (1 + rate / 100).pow(time)
        val explanation = """
            Given:
                Principal Amount = $principal
                Annual Interest Rate = $rate
                Time = $time years
                
            Formula:
                A = P * (1 + (R / N))^(N * T)
                
            Solution:
                A = $principal * (1 + ($rate / 100)) ^ $time
                A = $compoundInterest
                
            Therefore, the Compound Interest is ${compoundInterest - principal}    
        """.trimIndent()

        // Display explanation in a custom dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Compound Interest")
            .setMessage(explanation)
            .setPositiveButton("OK", null)
            .show()
    }
}
