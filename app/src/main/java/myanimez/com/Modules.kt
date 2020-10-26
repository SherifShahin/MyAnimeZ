package myanimez.com

import myanimez.com.Adapter.*
import myanimez.com.DataBase.AppDataBase
import myanimez.com.Model.AnimeCharacter
import myanimez.com.Model.AnimeRecommendation
import myanimez.com.Model.FavouriteAnime
import myanimez.com.Model.SeasonAnime
import myanimez.com.RemoteMediator.TopAnimeRemoteMediator
import myanimez.com.Repository.AnimeDetailsRepository
import myanimez.com.Repository.FavouriteAnimeRepository
import myanimez.com.Repository.SeasonAnimeRepository
import myanimez.com.Repository.TopAnimeRepository
import myanimez.com.ViewModel.AnimeDetailsViewModel
import myanimez.com.ViewModel.FavouriteAnimeViewModel
import myanimez.com.ViewModel.SeasonAnimeViewModel
import myanimez.com.ViewModel.TopAnimeViewModel
import myanimez.com.WebService.AnimeApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { AppDataBase.invoke(androidApplication()).getAppDao() }

    single { AnimeApi.invoke() }

    factory { (subtype : String) -> TopAnimeRemoteMediator(get() , get() ,subtype)}

    single { TopAnimeRepository(get(),androidApplication())}

    viewModel { TopAnimeViewModel(get()) }

    factory { AnimeAdapter(get()) }

    factory { SeasonAnimeRepository(get(),get()) }

    viewModel { SeasonAnimeViewModel(get()) }

    factory { (list : List<SeasonAnime>) -> SeasonAdapter(list) }

    factory { AnimeDetailsRepository(get(),get()) }

    viewModel { AnimeDetailsViewModel(get()) }

    factory { (list : List<AnimeCharacter>) -> AnimeCharactersAdapter(list) }

    factory { (list : List<AnimeRecommendation>) -> AnimeRecommendationsAdapter(list) }

    factory { FavouriteAnimeRepository(get()) }

    viewModel { FavouriteAnimeViewModel(get()) }

    factory { (list : List<FavouriteAnime>) -> FavouriteAdapter(list) }
}
