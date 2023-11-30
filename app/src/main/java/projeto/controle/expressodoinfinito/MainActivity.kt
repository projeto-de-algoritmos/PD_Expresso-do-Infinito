package projeto.controle.expressodoinfinito

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
        bindingPrincipal.buttonVoltar.setOnClickListener {
            setContentView(bindingInicio.root)
        }
        bindingPrincipal.buttonMesa.setOnClickListener {
            bindingPrincipal.imageViewMesaTopo.isVisible = true
            //bindingPrincipal.imageViewMesaTopo.setImageResource(R.drawable.mesa_cima)
        }
    }

    class PuzzlePiece(val image: Bitmap) {
        var x: Float = 0f
        var y: Float = 0f
        var isLocked = false
    }

    private var puzzlePiece: PuzzlePiece? = null
    private var selectedPiece: PuzzlePiece? = null
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Verifique se o toque ocorreu em cima de um objeto
                puzzlePiece?.let {
                    if (event.x >= it.x && event.x < it.x + it.image.width &&
                        event.y >= it.y && event.y < it.y + it.image.height
                    ) {
                        // Se o toque foi em cima do objeto, marque-o como selecionado
                        selectedPiece = it
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                // Atualize a posição do objeto com a posição do toque
                selectedPiece?.let {
                    it.x = event.x - it.image.width / 2
                    it.y = event.y - it.image.height / 2
                }
            }
            MotionEvent.ACTION_UP -> {
                // Libere o objeto
                selectedPiece = null
            }
        }
        // Redesenhe a tela
        //invalidate()
        return true
    }
/*
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Desenhe cada peça do quebra-cabeça
        puzzlePieces.forEach { piece ->
            canvas.drawBitmap(piece.image, piece.x, piece.y, null)
        }
    }*/
}
