package myanimez.com.Response

import myanimez.com.Model.AnimeRecommendation

data class AnimeRecommendationsResponse (
    val recommendations : List<AnimeRecommendation>
)