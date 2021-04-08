package com.example.hrybkov_l16

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hrybkov_l16.databinding.CountryFragmentBinding

class CountryFragment : Fragment() {
    private lateinit var binding: CountryFragmentBinding
    private lateinit var country: Country

    companion object {
        private const val COUNTRY_ARGUMENT = "COUNTRY_ARGUMENT"
        fun newInstance(country: Country) = CountryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(COUNTRY_ARGUMENT, country)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            country = it.getParcelable(COUNTRY_ARGUMENT) ?: Country("empty", 0, 0, 0.0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCountryName.text =
            context?.resources?.getString(R.string.country, country.countryName)
        binding.tvCountryPopulation.text =
            context?.resources?.getString(R.string.population, country.countryPopulation)
        binding.tvCountrySquare.text =
            context?.resources?.getString(R.string.square, country.countrySquare)
        binding.tvCountryPopulationDensity.text =
            context?.resources?.getString(
                R.string.density_population,
                country.countryPopulationDensity
            )
        binding.tvCountryHDI.text = context?.resources?.getString(R.string.hdi, country.hdi)
    }
}