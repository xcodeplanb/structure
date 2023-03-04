package com.example.structure

const val OPEN_WEATHER_URL = "https://api.openweathermap.org/data/3.0/onecall"
const val URL = "https://api.github.com"

//open api 개인키
var github_token = ""
const val ICON_URL = "https://openweathermap.org/img/wn/"

//open api 개인키
var open_weather_app_id = ""

const val KAKAO_DAUM_SEARCH_IMAGE = "https://dapi.kakao.com/v2/search/image"

const val HANGUEL_PATTERN_WITH_CHEONJIIN =
    "^[가-힣\\u318D\\u119E\\u11A2\\u2022\\u2025\\u00B7\\uFE55]+$" // 천지인
const val EMAIL_PATTERN =
    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=~()<>{}-])(?=\\S+\$).{8,}\$"