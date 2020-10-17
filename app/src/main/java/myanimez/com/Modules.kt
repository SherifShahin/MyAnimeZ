package myanimez.com

import myanimez.com.Adapter.AnimeAdapter
import myanimez.com.DataBase.AppDataBase
import myanimez.com.RemoteMediator.TopAnimeRemoteMediator
import myanimez.com.Repository.TopAnimeRepository
import myanimez.com.ViewModel.TopAnimeViewModel
import myanimez.com.WebService.TopAnimeApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { AppDataBase.invoke(androidApplication()).getAppDao() }

    single { TopAnimeApi.invoke() }

    factory { (subtype : String) -> TopAnimeRemoteMediator(get() , get() ,subtype)}

    factory { TopAnimeRepository(get(),androidApplication())}

    viewModel { TopAnimeViewModel(get()) }

    factory { AnimeAdapter() }
}
