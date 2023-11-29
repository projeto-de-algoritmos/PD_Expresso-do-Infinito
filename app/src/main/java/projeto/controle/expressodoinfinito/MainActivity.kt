package projeto.controle.expressodoinfinito

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import projeto.controle.expressodoinfinito.databinding.InicioBinding
import projeto.controle.expressodoinfinito.databinding.PrincipalBinding

class MainActivity : ComponentActivity() {
    private lateinit var bindingPrincipal: PrincipalBinding
    private lateinit var bindingInicio: InicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        window?.statusBarColor = ContextCompat.getColor(this, R.color.black)
        val decorView: View = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        decorView.systemUiVisibility = uiOptions

        bindingPrincipal = PrincipalBinding.inflate(layoutInflater)
        bindingInicio = InicioBinding.inflate(layoutInflater)
        setContentView(bindingInicio.root)

        bindingInicio.button.setOnClickListener {
            setContentView(bindingPrincipal.root)
        }
    }
}
