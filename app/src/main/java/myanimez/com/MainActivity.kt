package myanimez.com

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.seasonAnimeFragment,
                R.id.topAiringAnimeFragment,
                R.id.topAnimeFragment ,
                R.id.topMovieAnimeFragment,
                R.id.topUpcomingAnimeFragment
            ).setOpenableLayout(drawer_layout)
            .build()

        navigation_view.setupWithNavController(navController)

        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(
            appBarConfiguration)
        || super.onSupportNavigateUp()
    }


    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {

        val current =navController.currentDestination?.id!!

        if(current  == R.id.animeDetailsFragment){
            super.onBackPressed()
        }

        else {
            if (doubleBackToExitPressedOnce) {
                finish()
                return
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

}
