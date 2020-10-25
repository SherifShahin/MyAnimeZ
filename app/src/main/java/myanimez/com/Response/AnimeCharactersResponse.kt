package myanimez.com.Response

import myanimez.com.Model.AnimeCharacter

data class AnimeCharactersResponse (
    val characters : List<AnimeCharacter>
)