package com.example.weathercheck2000.ui.cityDetail

// TODO To be removed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weathercheck2000.R
import com.example.weathercheck2000.database.cities.Cities

class CityDetailFragment : Fragment() {

    private val viewModel by viewModels<CityDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {



                    val uiState by viewModel.uiState.collectAsState()

                    //CityDetailScreen(uiState = uiState)
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        /*// ----------- VYMAZAT --------------------------

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cityDetailFragment = this@CityDetailFragment  // it kinda works/ does not work even without this


        var temperatureMin = "(loading...)"
        var temperatureMax = "(loading...)"
        //var temperatureNow = "(loading...)"
        var weatherCode = "XX"
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // fetch forecast
                val forecastResult = WeatherApi.retrofitService.getForecast(viewModel.city.value!!.lat,viewModel.city.value!!.lon)
                temperatureMin = forecastResult.daily.temperature2mMin.first().toString()
                temperatureMax = forecastResult.daily.temperature2mMax.first().toString()
                // fetch current weather
                val actualWeather = WeatherApi.retrofitService.requestCurrentWeather(viewModel.city.value!!.lat,viewModel.city.value!!.lon)
                //temperatureNow = actualWeather.currentWeather.temperature
                weatherCode = actualWeather.currentWeather.weatherCode.toString() // TODO in future ENUM
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
            binding.temperatureMinValue.text        = "$temperatureMin °C"
            binding.temperatureMaxValue.text        = "$temperatureMax °C"
           // binding.temperatureCurrentValue.text    = "$temperatureNow °C"

            // Weather codes images
            if (!mapOfWeatherCodes.containsKey(weatherCode)) weatherCode = "X"

            binding.weathercodeImage.setImageResource(mapOfWeatherCodes[weatherCode]!!.imageId)
            binding.weathercodeExplanation.text = mapOfWeatherCodes[weatherCode]!!.description
            // ^^ same thing
        }
        */

    }

    //----------------------------
    fun deleteCity(cities: Cities){
      //  viewModel.deleteSelectedCity(cities)
        findNavController().navigate(R.id.action_navigation_city_detail_to_navigation_home)
    }
}