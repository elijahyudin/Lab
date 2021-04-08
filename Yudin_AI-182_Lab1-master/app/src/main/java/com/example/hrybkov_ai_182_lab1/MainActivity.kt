package com.example.hrybkov_ai_182_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.hrybkov_ai_182_lab1.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()

        val animation = AnimationUtils.loadAnimation(this, R.anim.button_alpha)

        binding.tvOne.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("1")

        }

        binding.tvTwo.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("2")
        }

        binding.tvThree.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("3")
        }

        binding.tvFour.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("4")
        }

        binding.tvFive.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("5")
        }

        binding.tvSix.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("6")
        }

        binding.tvSeven.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("7")
        }

        binding.tvEight.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("8")
        }

        binding.tvNine.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("9")
        }

        binding.tvZero.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("0")
        }

        binding.tvPlus.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("+")
        }

        binding.tvMinus.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("-")
        }

        binding.tvMul.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("*")
        }

        binding.tvDivide.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression("/")
        }

        binding.tvDot.setOnClickListener {
            it.startAnimation(animation)
            evaluateExpression(".")
        }

        binding.tvClear.setOnClickListener {
            it.startAnimation(animation)
            binding.tvInput.text = ""
            binding.tvResult.text = ""
        }

        binding.tvClearLast.setOnClickListener {
            it.startAnimation(animation)
            if (binding.tvInput.text.isNotEmpty()) {
                binding.tvInput.text =
                    binding.tvInput.text.substring(0, binding.tvInput.text.length - 1)
            }
        }

        binding.tvEquals.setOnClickListener {
            it.startAnimation(animation)
            val text = binding.tvInput.text.toString()
            try {
                val expression = ExpressionBuilder(text).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.tvResult.text = longResult.toString()
                } else {
                    binding.tvResult.text = result.toString()
                }
            } catch (e: IllegalArgumentException) {
                if (binding.tvInput.text.isNotEmpty()) {
                    binding.tvResult.text = getString(R.string.error_message)
                } else {
                    binding.tvResult.text = ""
                }
            }
        }

        binding.tvBack.setOnClickListener {
            val text = binding.tvInput.text
            if (text.isNotEmpty()) {
                binding.tvInput.text = text.substring(1, text.length)
            }

            binding.tvResult.text = ""
            it.startAnimation(animation)
        }

    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun evaluateExpression(string: String) {
        binding.tvResult.text = ""
        binding.tvInput.append(string)
    }

}