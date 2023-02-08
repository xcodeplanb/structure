package com.example.structure.data.model

data class Weather(
    val timezone: String = "",
    val timezoneOffset: Int = 0,
    val daily: List<DailyItem> = emptyList(),
    val lon: Double = 0.0,
    val lat: Double = 0.0
) {
    data class DailyItem(
        val moonset: Int = 0,
        val sunrise: Int = 0,
        val temp: Temp? = null,
        val moonPhase: Int = 0,
        val uvi: Double = 0.0,
        val moonrise: Int = 0,
        val pressure: Int = 0,
        val clouds: Int = 0,
        val feelsLike: FeelsLike? = null,
        val windGust: Double = 0.0,
        val dt: Int = 0,
        val pop: Double = 0.0,
        val windDeg: Int = 0,
        val dewPoint: Double = 0.0,
        val sunset: Int = 0,
        val weather: List<WeatherItem>? = null,
        val humidity: Int = 0,
        val windSpeed: Double = 0.0,
        var timezoneText: String = "",
        var cityName: String = "",
        var isHeaderPositon: Boolean
    ) {
        data class WeatherItem(
            val icon: String = "",
            val description: String = "",
            val main: String = "",
            val id: Int = 0,
            var iconUrl: String = ""
        )

        data class Temp(
            val min: Double = 0.0,
            val max: Double = 0.0,
            val eve: Double = 0.0,
            val night: Double = 0.0,
            val day: Double = 0.0,
            val morn: Double = 0.0,
            var celsiusMin: String = "",
            var celsiusMax: String = ""
        )

        data class FeelsLike(
            val eve: Double = 0.0,
            val night: Double = 0.0,
            val day: Double = 0.0,
            val morn: Double = 0.0
        )
    }
}