package myanimez.com

import myanimez.com.Adapter.AnimeAdapter
import myanimez.com.Adapter.SeasonAdapter
import myanimez.com.DataBase.AppDataBase
import myanimez.com.Model.Anime
import myanimez.com.Model.SeasonAnime
import myanimez.com.RemoteMediator.TopAnimeRemoteMediator
import myanimez.com.Repository.SeasonAnimeRepository
import myanimez.com.Repository.TopAnimeRepository
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

    factory { TopAnimeRepository(get(),androidApplication())}

    viewModel { TopAnimeViewModel(get()) }

    factory { AnimeAdapter() }

    factory { SeasonAnimeRepository(get(),get()) }

    viewModel { SeasonAnimeViewModel(get()) }

    factory { (list : List<SeasonAnime>) -> SeasonAdapter(list) }
}
