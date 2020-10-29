package myanimez.com

import myanimez.com.Adapter.*
import myanimez.com.DataBase.AppDataBase
import myanimez.com.Model.*
import myanimez.com.RemoteMediator.TopAnimeRemoteMediator
import myanimez.com.Repository.*
import myanimez.com.Response.ScheduleResponse
import myanimez.com.ViewModel.*
import myanimez.com.WebService.AnimeApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { AppDataBase.invoke(androidApplication()).getAppDao() }

    single { AnimeApi.invoke() }

    factory { (type:String ,subtype : String) -> TopAnimeRemoteMediator(get() , get() ,type,subtype)}

    single { TopRepository(get(),androidApplication())}

    viewModel { TopViewModel(get()) }

    factory { TopAdapter(get()) }

    single { SeasonAnimeRepository(get(),get()) }

    viewModel { SeasonAnimeViewModel(get()) }

    factory { (list : List<SeasonAnime>) -> SeasonAdapter(list) }

    factory { AnimeDetailsRepository(get(),get()) }

    viewModel { AnimeDetailsViewModel(get()) }

    factory { (list : List<AnimeCharacter>) -> AnimeCharactersAdapter(list) }

    factory { (list : List<AnimeRecommendation>) -> AnimeRecommendationsAdapter(list) }

    factory { FavouriteAnimeRepository(get()) }

    viewModel { FavouriteAnimeViewModel(get()) }

    factory { (list : List<FavouriteAnime>) -> FavouriteAdapter(list) }

    single{ SearchRepository(get() ,get()) }

    viewModel { SearchViewModel(get()) }

    factory { (list : List<SearchResult>) -> SearchAdapter(list) }

    single{ ScheduleRepository(get()) }

    viewModel { ScheduleViewModel(get()) }

    factory { (items : ScheduleResponse) -> ScheduleAdapter(items) }
}
