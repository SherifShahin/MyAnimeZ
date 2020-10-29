package myanimez.com.Response

import myanimez.com.Model.Anime

data class ScheduleResponse (
    val monday : List<Anime> ,
    val tuesday : List<Anime> ,
    val wednesday : List<Anime> ,
    val thursday : List<Anime> ,
    val friday : List<Anime> ,
    val saturday : List<Anime> ,
    val sunday : List<Anime>
    )