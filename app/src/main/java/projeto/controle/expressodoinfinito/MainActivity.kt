package projeto.controle.expressodoinfinito

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.ui.input.key.Key.Companion.K
import androidx.compose.ui.input.key.Key.Companion.W
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import projeto.controle.expressodoinfinito.databinding.InicioBinding
import projeto.controle.expressodoinfinito.databinding.PrincipalBinding

class MainActivity : ComponentActivity() {
    private lateinit var bindingPrincipal: PrincipalBinding
    private lateinit var bindingInicio: InicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
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
        bindingPrincipal.buttonVoltar.setOnClickListener {
            setContentView(bindingInicio.root)
        }

        var ocupacao = Array(3) {IntArray(3)}
        var valores = intArrayOf(200, 200, 200, 200, 200, 200, 200, 200, 200, 250, 250, 250, 250, 900, 900, 900, 1200)
        var pesos = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4)
        var capacidade = 9
        var qtdItens = valores.size

        val memoization = Array(qtdItens + 1) { IntArray(capacidade + 1) }

        knapSack(memoization, capacidade, pesos, valores, qtdItens)

        memoization.forEach { linha ->
            println(linha.joinToString(" "))
        }
    }
    private fun knapSack(memoization: Array<IntArray>, Capacidade: Int, pesos: IntArray, valores: IntArray, qtdItens: Int): Int {
        for (i in 0..qtdItens) {
            for (w in 0..Capacidade) {
                if (i == 0 || w == 0)
                    memoization[i][w] = 0
                else if (pesos[i - 1] <= w)
                    memoization[i][w] = kotlin.math.max(valores[i - 1] + memoization[i - 1][w - pesos[i - 1]], memoization[i - 1][w])
                else
                    memoization[i][w] = memoization[i - 1][w]
            }
        }
        return memoization[qtdItens][Capacidade]
    }

}
