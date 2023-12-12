package projeto.controle.expressodoinfinito

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.ComponentActivity
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
        val decorView: View = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        decorView.systemUiVisibility = uiOptions

        var ocupacao = Array(3) {IntArray(3)}
        var ocupacaoAux = Array(3) {IntArray(3)}
        var selectedImageView: ImageView? = null
        var imageID: Int
        var lucro = 0
        var dia = 1

        bindingPrincipal = PrincipalBinding.inflate(layoutInflater)
        bindingInicio = InicioBinding.inflate(layoutInflater)
        setContentView(bindingInicio.root)

        bindingInicio.buttonJogar.setOnClickListener {
            setContentView(bindingPrincipal.root)
            //bindingPrincipal.dPad.isVisible = false
        }
        bindingPrincipal.buttonVoltar.setOnClickListener {
            setContentView(bindingInicio.root)
        }

        bindingPrincipal.cardviewDica.setOnClickListener {
            bindingPrincipal.cardviewDica.isVisible = false
        }

        bindingPrincipal.buttonMesa.setOnClickListener {
            //bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                //bindingPrincipal.dPad.isVisible = true
            }

            var i = 0
            var j = 0

            while (i < ocupacao.size - 1) {
                while (j < ocupacao.size - 1) {
                    if (ocupacao[i][j] == 0 && ocupacao[i][j+1] == 0 && ocupacao[i+1][j] == 0 && ocupacao[i+1][j+1] == 0) {
                        ocupacao[i][j] = imageID
                        ocupacao[i][j+1] = imageID
                        ocupacao[i+1][j] = imageID
                        ocupacao[i+1][j+1] = imageID

                        lucro += 1200
                        bindingPrincipal.textViewValor.text = "$" + lucro
                        when (i) {
                            0 -> {
                                when (j) {
                                    0 -> {
                                        val imageSize = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            120f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                        layoutParams.gravity = Gravity.TOP or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.mesa_esquerdo
                                            )
                                        )
                                        bindingPrincipal.frame1.addView(imageView)
                                    }

                                    1 -> {
                                        val imageSize = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            120f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                        layoutParams.gravity = Gravity.TOP or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.mesa_direito
                                            )
                                        )
                                        bindingPrincipal.frame1.addView(imageView)
                                    }
                                }
                            }

                            1 -> {
                                when (j) {
                                    0 -> {
                                        val imageSize = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            160f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.mesa_esquerdo
                                            )
                                        )
                                        bindingPrincipal.frame3.addView(imageView)
                                    }

                                    1 -> {
                                        val imageSize = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            160f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.mesa_direito
                                            )
                                        )
                                        bindingPrincipal.frame3.addView(imageView)
                                    }
                                }
                            }
                        }

                        i = 2
                        j = 2
                    }
                    j++
                }
                i++
                j = 0
            }
        }
        bindingPrincipal.buttonComoda.setOnClickListener {
            //bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                //bindingPrincipal.dPad.isVisible = true
            }

            var i = 0
            var j = 0
            var livre = false
            // Verifica se uma linha inteira é 0
            while (i < ocupacao.size) {
                var contador = 0
                while (j < ocupacao[i].size) {
                    if (ocupacao[i][j] == 0) {
                        contador++
                    }
                    j++
                }
                j = 0

                if (contador == ocupacao[i].size) {
                    ocupacao[i][j] = imageID
                    ocupacao[i][j+1] = imageID
                    ocupacao[i][j+2] = imageID

                    lucro += 900
                    bindingPrincipal.textViewValor.text = "$" + lucro
                    when (i) {
                        0 -> {
                            val imageAltura = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                180f,
                                resources.displayMetrics
                            ).toInt()

                            val imageLargura = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                75f,
                                resources.displayMetrics
                            ).toInt()

                            // Defina as propriedades da ImageView
                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                            layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                            imageView.layoutParams = layoutParams
                            imageView.setImageDrawable(
                                ContextCompat.getDrawable(
                                    this,
                                    R.drawable.comoda_horizontal
                                )
                            )
                            bindingPrincipal.frame1.addView(imageView)
                        }
                        1 -> {
                            val imageAltura = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                216f,
                                resources.displayMetrics
                            ).toInt()

                            val imageLargura = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                90f,
                                resources.displayMetrics
                            ).toInt()

                            // Defina as propriedades da ImageView
                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                            layoutParams.gravity = Gravity.CENTER
                            imageView.layoutParams = layoutParams
                            imageView.setImageDrawable(
                                ContextCompat.getDrawable(
                                    this,
                                    R.drawable.comoda_horizontal
                                )
                            )
                            bindingPrincipal.frame2.addView(imageView)
                        }
                        2 -> {
                            val imageAltura = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                259f,
                                resources.displayMetrics
                            ).toInt()

                            val imageLargura = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                108f,
                                resources.displayMetrics
                            ).toInt()

                            // Defina as propriedades da ImageView
                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                            imageView.layoutParams = layoutParams
                            imageView.setImageDrawable(
                                ContextCompat.getDrawable(
                                    this,
                                    R.drawable.comoda_horizontal
                                )
                            )
                            bindingPrincipal.frame3.addView(imageView)
                        }
                    }
                    i = ocupacao.size + 1
                    j = ocupacao.size + 1
                    livre = true
                }
                i++
            }

            if (!livre) {
                i = 0
                j = 0
                // Verifica se uma coluna inteira é 0
                while (j < ocupacao.size) {
                    var contador = 0
                    while (i < ocupacao.size) {
                        if (ocupacao[i][j] == 0) {
                            contador++
                        }
                        i++
                    }
                    i = 0

                    if (contador == ocupacao.size) {
                        ocupacao[i][j] = imageID
                        ocupacao[i+1][j] = imageID
                        ocupacao[i+2][j] = imageID

                        lucro += 900
                        bindingPrincipal.textViewValor.text = "$" + lucro
                        when (j) {
                            0 -> {
                                val imageAltura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    90f,
                                    resources.displayMetrics
                                ).toInt()

                                val imageLargura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    216f,
                                    resources.displayMetrics
                                ).toInt()

                                // Defina as propriedades da ImageView
                                val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                layoutParams.gravity = Gravity.START or Gravity.CENTER_VERTICAL
                                imageView.layoutParams = layoutParams
                                imageView.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this,
                                        R.drawable.comoda_vertical_e
                                    )
                                )
                                bindingPrincipal.frame3.addView(imageView)
                            }
                            1 -> {
                                val imageAltura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    90f,
                                    resources.displayMetrics
                                ).toInt()

                                val imageLargura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    216f,
                                    resources.displayMetrics
                                ).toInt()

                                // Defina as propriedades da ImageView
                                val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                layoutParams.gravity = Gravity.CENTER
                                imageView.layoutParams = layoutParams
                                imageView.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this,
                                        R.drawable.comoda_vertical
                                    )
                                )
                                bindingPrincipal.frame3.addView(imageView)
                            }
                            2 -> {
                                val imageAltura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    90f,
                                    resources.displayMetrics
                                ).toInt()

                                val imageLargura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    216f,
                                    resources.displayMetrics
                                ).toInt()

                                // Defina as propriedades da ImageView
                                val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                layoutParams.gravity = Gravity.END or Gravity.CENTER_VERTICAL
                                imageView.layoutParams = layoutParams
                                imageView.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        this,
                                        R.drawable.comoda_vertical_d
                                    )
                                )
                                bindingPrincipal.frame3.addView(imageView)
                            }
                        }
                        i = ocupacao.size + 1
                        j = ocupacao.size + 1
                    }
                    j++
                }
            }
        }
        bindingPrincipal.buttonBanco.setOnClickListener {
            //bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                //bindingPrincipal.dPad.isVisible = true
            }
            var i = 0
            var j = 0
            var livre = false

            //Verifica na horizontal
            while (i < ocupacao.size) {
                while (j < ocupacao.size-1) {
                    if (ocupacao[i][j] == 0 && ocupacao[i][j+1] == 0) {
                        ocupacao[i][j] = imageID
                        ocupacao[i][j+1] = imageID

                        lucro += 250
                        bindingPrincipal.textViewValor.text = "$" + lucro

                        when (i) {
                            0 -> {
                                when (j) {
                                    0 -> {
                                        val imageAltura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            102f,
                                            resources.displayMetrics
                                        ).toInt()

                                        val imageLargura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            82f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.TOP or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.banco_horizontal_e
                                            )
                                        )
                                        bindingPrincipal.frame1.addView(imageView)
                                    }

                                    1 -> {
                                        val imageAltura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            102f,
                                            resources.displayMetrics
                                        ).toInt()

                                        val imageLargura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            82f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.TOP or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.banco_horizontal_d
                                            )
                                        )
                                        bindingPrincipal.frame1.addView(imageView)
                                    }
                                }
                            }

                            1 -> {
                                when (j) {
                                    0 -> {
                                        val imageAltura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            128f,
                                            resources.displayMetrics
                                        ).toInt()

                                        val imageLargura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            102f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.CENTER_VERTICAL or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.banco_horizontal_e
                                            )
                                        )
                                        bindingPrincipal.frame2.addView(imageView)
                                    }

                                    1 -> {
                                        val imageAltura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            128f,
                                            resources.displayMetrics
                                        ).toInt()

                                        val imageLargura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            102f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.banco_horizontal_d
                                            )
                                        )
                                        bindingPrincipal.frame2.addView(imageView)
                                    }
                                }
                            }

                            2 -> {
                                when (j) {
                                    0 -> {
                                        val imageAltura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            160f,
                                            resources.displayMetrics
                                        ).toInt()

                                        val imageLargura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            128f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.banco_horizontal_e
                                            )
                                        )
                                        bindingPrincipal.frame3.addView(imageView)
                                    }

                                    1 -> {
                                        val imageAltura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            160f,
                                            resources.displayMetrics
                                        ).toInt()

                                        val imageLargura = TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP,
                                            128f,
                                            resources.displayMetrics
                                        ).toInt()

                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.banco_horizontal_d
                                            )
                                        )
                                        bindingPrincipal.frame3.addView(imageView)
                                    }
                                }
                            }
                        }

                        i = ocupacao.size
                        j = ocupacao.size
                        livre = true
                    }
                    j++
                }
                i++
                j = 0
            }

            if (!livre) {
                i = 0
                j = 0
                //Verifica na vertical
                while (j < ocupacao.size) {
                    while (i < ocupacao.size - 1) {
                        if (ocupacao[i][j] == 0 && ocupacao[i+1][j] == 0) {
                            ocupacao[i][j] = imageID
                            ocupacao[i+1][j] = imageID

                            lucro += 250
                            bindingPrincipal.textViewValor.text = "$" + lucro

                            when (j) {
                                0 -> {
                                    when (i) {
                                        0 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                60f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                130f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.TOP or Gravity.START
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.banco_vertical_e
                                                )
                                            )
                                            bindingPrincipal.frame1.addView(imageView)
                                        }

                                        1 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                74f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                160f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.TOP or Gravity.END
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.banco_vertical_e
                                                )
                                            )
                                            bindingPrincipal.frame3.addView(imageView)
                                        }
                                    }
                                }

                                1 -> {
                                    when (i) {
                                        0 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                60f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                130f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.banco_vertical
                                                )
                                            )
                                            bindingPrincipal.frame1.addView(imageView)
                                        }

                                        1 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                74f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                160f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.banco_vertical
                                                )
                                            )
                                            bindingPrincipal.frame3.addView(imageView)
                                        }
                                    }
                                }

                                2 -> {
                                    when (i) {
                                        0 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                60f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                130f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.TOP or Gravity.END
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.banco_vertical_d
                                                )
                                            )
                                            bindingPrincipal.frame1.addView(imageView)
                                        }

                                        1 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                74f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                160f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.banco_vertical_d
                                                )
                                            )
                                            bindingPrincipal.frame3.addView(imageView)
                                        }
                                    }
                                }
                            }

                            i = 2
                            j = 2
                        }
                        i++
                    }
                    j++
                    i = 0
                }
            }
        }
        bindingPrincipal.buttonPlanta.setOnClickListener {
            //bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                //bindingPrincipal.dPad.isVisible = true
            }

            var i = 0
            var j = 0

            while (i <= 2) {
                while (j <= 2) {
                    if (ocupacao[i][j] == 0) {
                        ocupacao[i][j] = imageID

                        lucro += 200
                        bindingPrincipal.textViewValor.text = "$" + lucro
                        when (i) {
                            0 -> {
                                val imageAltura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    77f,
                                    resources.displayMetrics
                                ).toInt()

                                val imageLargura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    67f,
                                    resources.displayMetrics
                                ).toInt()
                                imageView.isClickable = true
                                imageView.setOnClickListener {
                                    imageView.isSelected = true
                                }

                                when (j) {
                                    0 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.TOP or Gravity.START
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame1.addView(imageView)
                                    }

                                    1 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame1.addView(imageView)
                                    }

                                    2 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.TOP or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame1.addView(imageView)
                                    }
                                }
                            }

                            1 -> {
                                val imageAltura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    96f,
                                    resources.displayMetrics
                                ).toInt()

                                val imageLargura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    84f,
                                    resources.displayMetrics
                                ).toInt()

                                when (j) {
                                    0 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.CENTER_VERTICAL or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame2.addView(imageView)
                                    }

                                    1 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.CENTER
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame2.addView(imageView)
                                    }

                                    2 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame2.addView(imageView)
                                    }
                                }
                            }

                            2 -> {
                                val imageAltura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    120f,
                                    resources.displayMetrics
                                ).toInt()

                                val imageLargura = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    105f,
                                    resources.displayMetrics
                                ).toInt()

                                when (j) {
                                    0 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame3.addView(imageView)
                                    }

                                    1 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame3.addView(imageView)
                                    }

                                    2 -> {
                                        // Defina as propriedades da ImageView
                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                        imageView.layoutParams = layoutParams
                                        imageView.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this,
                                                R.drawable.planta_objeto
                                            )
                                        )

                                        // Adicione a ImageView ao layout
                                        bindingPrincipal.frame3.addView(imageView)
                                    }
                                }
                            }
                        }

                        i = 3
                        j = 3
                    }
                    j++
                }
                i++
                j = 0
            }
        }
        /*
        bindingPrincipal.buttonDireita.setOnClickListener {
            var contador = 0

            for (i in 0..2) {
                for (j in 0..2) {
                    if (ocupacao[i][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                        contador++
                    }
                }
            }

            when (contador) {
                1 -> {
                    for (i in 0..2) {
                        for (j in 0..1) {
                            if (ocupacao[i][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                if (ocupacao[i][j + 1] == 0) {
                                    ocupacao[i][j] = 0
                                    ocupacao[i][j+1] = selectedImageView.toString().substring(25, 27).toInt(16)
                                    when (i) {
                                        0 -> {
                                            when (j) {
                                                0 -> {
                                                    selectedImageView?.let { imageView ->
                                                        // Atualize a posição da ImageView selecionada
                                                        val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                                                        imageView.layoutParams = params
                                                    }
                                                }

                                                1 -> {
                                                    selectedImageView?.let { imageView ->
                                                        // Atualize a posição da ImageView selecionada
                                                        val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                        params.gravity = Gravity.TOP or Gravity.END
                                                        imageView.layoutParams = params
                                                    }
                                                }
                                            }
                                        }

                                        1 -> {
                                            when (j) {
                                                0 -> {
                                                    selectedImageView?.let { imageView ->
                                                        // Atualize a posição da ImageView selecionada
                                                        val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                        params.gravity = Gravity.CENTER
                                                        imageView.layoutParams = params
                                                    }
                                                }

                                                1 -> {
                                                    selectedImageView?.let { imageView ->
                                                        // Atualize a posição da ImageView selecionada
                                                        val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                        params.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                                                        imageView.layoutParams = params
                                                    }
                                                }
                                            }
                                        }

                                        2 -> {
                                            when (j) {
                                                0 -> {
                                                    selectedImageView?.let { imageView ->
                                                        // Atualize a posição da ImageView selecionada
                                                        val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                        params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                                        imageView.layoutParams = params
                                                    }
                                                }

                                                1 -> {
                                                    selectedImageView?.let { imageView ->
                                                        // Atualize a posição da ImageView selecionada
                                                        val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                        params.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = params
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    break
                                }
                            }
                        }
                    }
                }

                2 -> {
                    for (i in ocupacao.indices) {
                        for (j in ocupacao[i].size - 1 downTo 1) {
                            if (ocupacao[i][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                // Verifica se o objeto pode ser movido para a direita
                                if (j < ocupacao[i].size - 1 && ocupacao[i][j + 1] == 0) {
                                    if (ocupacao[i][j-1] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                        ocupacao[i][j + 1] = ocupacao[i][j]
                                        ocupacao[i][j - 1] = 0

                                        when (i) {
                                            0 -> {
                                                selectedImageView?.let { imageView ->
                                                    // Atualize a posição da ImageView selecionada
                                                    val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                    params.gravity = Gravity.TOP or Gravity.END
                                                    imageView.layoutParams = params
                                                    imageView.setImageDrawable(
                                                        ContextCompat.getDrawable(
                                                            this,
                                                            R.drawable.banco_horizontal_d
                                                        )
                                                    )
                                                }
                                            }

                                            1 -> {
                                                selectedImageView?.let { imageView ->
                                                    // Atualize a posição da ImageView selecionada
                                                    val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                    params.gravity = Gravity.CENTER_VERTICAL or Gravity.END
                                                    imageView.layoutParams = params
                                                    imageView.setImageDrawable(
                                                        ContextCompat.getDrawable(
                                                            this,
                                                            R.drawable.banco_horizontal_d
                                                        )
                                                    )
                                                }
                                            }

                                            2 -> {
                                                selectedImageView?.let { imageView ->
                                                    // Atualize a posição da ImageView selecionada
                                                    val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                    params.gravity = Gravity.BOTTOM or Gravity.END
                                                    imageView.layoutParams = params
                                                    imageView.setImageDrawable(
                                                        ContextCompat.getDrawable(
                                                            this,
                                                            R.drawable.banco_horizontal_d
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                break
                            }
                        }
                    }

                    for (j in ocupacao.size - 1 downTo 0) {
                        if (j < ocupacao.size - 1 && ocupacao[1][j + 1] == 0) {
                            if (ocupacao[1][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                ocupacao[1][j + 1] = ocupacao[1][j]
                                ocupacao[1][j] = 0

                                if (ocupacao[0][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                    ocupacao[0][j + 1] = ocupacao[0][j]
                                    ocupacao[0][j] = 0

                                    when (j) {
                                        0 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.banco_vertical
                                                    )
                                                )
                                            }
                                        }

                                        1 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.TOP or Gravity.END
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.banco_vertical_d
                                                    )
                                                )
                                            }
                                        }
                                    }
                                } else {
                                    ocupacao[2][j + 1] = ocupacao[2][j]
                                    ocupacao[2][j] = 0

                                    when (j) {
                                        0 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.banco_vertical
                                                    )
                                                )
                                            }
                                        }

                                        1 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.BOTTOM or Gravity.END
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.banco_vertical_d
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                                break
                            }
                        }
                    }
                }

                3 -> {
                    for (j in ocupacao.size - 1 downTo 1) {
                        if (ocupacao[0][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                           if (j < ocupacao.size - 1 && ocupacao[0][j + 1] == 0 && ocupacao[1][j + 1] == 0 && ocupacao[2][j + 1] == 0) {
                               ocupacao[0][j+1] = ocupacao[0][j]
                               ocupacao[1][j+1] = ocupacao[1][j]
                               ocupacao[2][j+1] = ocupacao[2][j]

                               ocupacao[0][j] = 0
                               ocupacao[1][j] = 0
                               ocupacao[2][j] = 0

                               when (j) {
                                   1 -> {

                                   }

                                   2 -> {

                                   }
                               }
                           }
                        }
                    }
                }

                4 -> {
                    var i = 0
                    var j = 0

                    while (i < 2) {
                        while (j < 2) {
                            if (ocupacao[i][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                if (j < 1 && ocupacao[i][j + 2] == 0 && ocupacao[i+1][j+2] == 0 && i < 1 && ocupacao[i+2][j] != selectedImageView.toString().substring(25, 27).toInt(16)) {
                                    ocupacao[i][j] = 0
                                    ocupacao[i+1][j] = 0
                                    ocupacao[i][j+2] = selectedImageView.toString().substring(25, 27).toInt(16)
                                    ocupacao[i+1][j+2] = selectedImageView.toString().substring(25, 27).toInt(16)
                                    when (i) {
                                        0 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.TOP or Gravity.END
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.mesa_direito
                                                    )
                                                )
                                            }
                                        }

                                        1 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.BOTTOM or Gravity.END
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.mesa_direito
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    i = 2
                                    j = 2
                                }
                                else if (j < 1 && ocupacao[i][j + 2] == 0 && ocupacao[i+1][j+2] == 0 && ocupacao[i-1][j] != selectedImageView.toString().substring(25, 27).toInt(16)) {
                                    ocupacao[i][j] = 0
                                    ocupacao[i+1][j] = 0
                                    ocupacao[i][j+2] = selectedImageView.toString().substring(25, 27).toInt(16)
                                    ocupacao[i+1][j+2] = selectedImageView.toString().substring(25, 27).toInt(16)
                                    when (i) {
                                        0 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.TOP or Gravity.END
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.mesa_direito
                                                    )
                                                )
                                            }
                                        }

                                        1 -> {
                                            selectedImageView?.let { imageView ->
                                                // Atualize a posição da ImageView selecionada
                                                val params = imageView.layoutParams as FrameLayout.LayoutParams
                                                params.gravity = Gravity.BOTTOM or Gravity.END
                                                imageView.layoutParams = params
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.mesa_direito
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    i = 2
                                    j = 2
                                }
                            }
                            j++
                        }
                        j = 0
                        i++
                    }
                }
            }
        }

        bindingPrincipal.headerImage.setOnClickListener {
            bindingPrincipal.dPad.isVisible = false
        }
        */
        bindingPrincipal.buttonDescarregar.setOnClickListener {
            bindingPrincipal.frame1.removeAllViews()
            bindingPrincipal.frame2.removeAllViews()
            bindingPrincipal.frame3.removeAllViews()
            bindingPrincipal.dPad.isVisible = false

            lucro = 0
            bindingPrincipal.textViewValor.text = ""

            for (i in ocupacao.indices)
                for (j in ocupacao[i].indices)
                    ocupacao[i][j] = 0
        }
        bindingPrincipal.buttonIniciar.setOnClickListener {
            bindingPrincipal.resultado.isVisible = true
            bindingPrincipal.textView2.text = "Resultado do dia $dia:\n$$lucro"
            dia++
            bindingPrincipal.title.text = "Dia $dia"
        }
        bindingPrincipal.resultado.setOnClickListener{
            bindingPrincipal.resultado.isVisible = false
            bindingPrincipal.frame1.removeAllViews()
            bindingPrincipal.frame2.removeAllViews()
            bindingPrincipal.frame3.removeAllViews()
            bindingPrincipal.dPad.isVisible = false

            lucro = 0
            bindingPrincipal.textViewValor.text = ""

            for (i in ocupacao.indices)
                for (j in ocupacao[i].indices)
                    ocupacao[i][j] = 0
        }

        var valores = intArrayOf(200, 200, 200, 200, 200, 200, 200, 200, 200, 250, 250, 250, 250, 900, 900, 900, 1200)
        var pesos = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4)
        var capacidade = 9
        var qtdItens = valores.size

        val memoization = Array(qtdItens + 1) { IntArray(capacidade + 1) }

        bindingPrincipal.buttonDica.setOnClickListener {
            bindingPrincipal.cardviewDica.isVisible = true
            //bindingPrincipal.dPad.isVisible = false

            knapSack(memoization, capacidade, pesos, valores, qtdItens)
            /*memoization[16].forEach { e ->
                println(e)
            }*/
            val itensEscolhidos = selecionados(memoization, capacidade, valores, pesos, qtdItens)
            var k = 0
            while (k < itensEscolhidos.size) {
                if (valores[itensEscolhidos[k]] != 0) {
                    println(valores[itensEscolhidos[k]])

                    when (valores[itensEscolhidos[k]]) {
                        200 -> {
                            val imageView = ImageView(this)
                            val strAux = imageView.toString().substring(25, 27)
                            imageID = strAux.toInt(16)
                            imageView.isClickable = true

                            imageView.setOnClickListener {
                                selectedImageView = it as ImageView
                                it.isSelected = true
                                //bindingPrincipal.dPad.isVisible = true
                            }

                            var i = 0
                            var j = 0

                            while (i <= 2) {
                                while (j <= 2) {
                                    if (ocupacaoAux[i][j] == 0) {
                                        ocupacaoAux[i][j] = imageID
                                        when (i) {
                                            0 -> {
                                                val imageAltura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    77f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                val imageLargura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    67f,
                                                    resources.displayMetrics
                                                ).toInt()
                                                imageView.isClickable = true
                                                imageView.setOnClickListener {
                                                    imageView.isSelected = true
                                                }

                                                when (j) {
                                                    0 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame1Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame1Dica.addView(imageView)
                                                    }

                                                    2 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame1Dica.addView(imageView)
                                                    }
                                                }
                                            }

                                            1 -> {
                                                val imageAltura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    96f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                val imageLargura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    84f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                when (j) {
                                                    0 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }

                                                    2 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }
                                                }
                                            }

                                            2 -> {
                                                val imageAltura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    120f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                val imageLargura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    105f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                when (j) {
                                                    0 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }

                                                    2 -> {
                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.planta_objeto
                                                            )
                                                        )

                                                        // Adicione a ImageView ao layout
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }
                                                }
                                            }
                                        }

                                        i = 3
                                        j = 3
                                    }
                                    j++
                                }
                                i++
                                j = 0
                            }
                        }

                        250 -> {
                            val imageView = ImageView(this)
                            val strAux = imageView.toString().substring(25, 27)
                            imageID = strAux.toInt(16)
                            imageView.isClickable = true

                            imageView.setOnClickListener {
                                selectedImageView = it as ImageView
                                it.isSelected = true
                                //bindingPrincipal.dPad.isVisible = true
                            }
                            var i = 0
                            var j = 0
                            var livre = false

                            //Verifica na horizontal
                            while (i < ocupacaoAux.size) {
                                while (j < ocupacaoAux.size-1) {
                                    if (ocupacaoAux[i][j] == 0 && ocupacaoAux[i][j+1] == 0) {
                                        ocupacaoAux[i][j] = imageID
                                        ocupacaoAux[i][j+1] = imageID

                                        when (i) {
                                            0 -> {
                                                when (j) {
                                                    0 -> {
                                                        val imageAltura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            102f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        val imageLargura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            82f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.banco_horizontal_e
                                                            )
                                                        )
                                                        bindingPrincipal.frame1Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        val imageAltura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            102f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        val imageLargura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            82f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.banco_horizontal_d
                                                            )
                                                        )
                                                        bindingPrincipal.frame1Dica.addView(imageView)
                                                    }
                                                }
                                            }

                                            1 -> {
                                                when (j) {
                                                    0 -> {
                                                        val imageAltura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            128f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        val imageLargura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            102f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.banco_horizontal_e
                                                            )
                                                        )
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        val imageAltura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            128f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        val imageLargura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            102f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.banco_horizontal_d
                                                            )
                                                        )
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }
                                                }
                                            }

                                            2 -> {
                                                when (j) {
                                                    0 -> {
                                                        val imageAltura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            160f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        val imageLargura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            128f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.banco_horizontal_e
                                                            )
                                                        )
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        val imageAltura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            160f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        val imageLargura = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            128f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.banco_horizontal_d
                                                            )
                                                        )
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }
                                                }
                                            }
                                        }

                                        i = ocupacaoAux.size
                                        j = ocupacaoAux.size
                                        livre = true
                                    }
                                    j++
                                }
                                i++
                                j = 0
                            }

                            if (!livre) {
                                i = 0
                                j = 0
                                //Verifica na vertical
                                while (j < ocupacaoAux.size) {
                                    while (i < ocupacaoAux.size - 1) {
                                        if (ocupacaoAux[i][j] == 0 && ocupacaoAux[i+1][j] == 0) {
                                            ocupacaoAux[i][j] = imageID
                                            ocupacaoAux[i+1][j] = imageID

                                            when (j) {
                                                0 -> {
                                                    when (i) {
                                                        0 -> {
                                                            val imageAltura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                60f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            val imageLargura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                130f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            // Defina as propriedades da ImageView
                                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                            imageView.layoutParams = layoutParams
                                                            imageView.setImageDrawable(
                                                                ContextCompat.getDrawable(
                                                                    this,
                                                                    R.drawable.banco_vertical_e
                                                                )
                                                            )
                                                            bindingPrincipal.frame2Dica.addView(imageView)
                                                        }

                                                        1 -> {
                                                            val imageAltura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                74f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            val imageLargura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                160f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            // Defina as propriedades da ImageView
                                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                            imageView.layoutParams = layoutParams
                                                            imageView.setImageDrawable(
                                                                ContextCompat.getDrawable(
                                                                    this,
                                                                    R.drawable.banco_vertical_e
                                                                )
                                                            )
                                                            bindingPrincipal.frame3Dica.addView(imageView)
                                                        }
                                                    }
                                                }

                                                1 -> {
                                                    when (i) {
                                                        0 -> {
                                                            val imageAltura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                60f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            val imageLargura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                130f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            // Defina as propriedades da ImageView
                                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                                            imageView.layoutParams = layoutParams
                                                            imageView.setImageDrawable(
                                                                ContextCompat.getDrawable(
                                                                    this,
                                                                    R.drawable.banco_vertical
                                                                )
                                                            )
                                                            bindingPrincipal.frame2Dica.addView(imageView)
                                                        }

                                                        1 -> {
                                                            val imageAltura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                74f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            val imageLargura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                160f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            // Defina as propriedades da ImageView
                                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                                            imageView.layoutParams = layoutParams
                                                            imageView.setImageDrawable(
                                                                ContextCompat.getDrawable(
                                                                    this,
                                                                    R.drawable.banco_vertical
                                                                )
                                                            )
                                                            bindingPrincipal.frame3Dica.addView(imageView)
                                                        }
                                                    }
                                                }

                                                2 -> {
                                                    when (i) {
                                                        0 -> {
                                                            val imageAltura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                60f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            val imageLargura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                130f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            // Defina as propriedades da ImageView
                                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                            imageView.layoutParams = layoutParams
                                                            imageView.setImageDrawable(
                                                                ContextCompat.getDrawable(
                                                                    this,
                                                                    R.drawable.banco_vertical_d
                                                                )
                                                            )
                                                            bindingPrincipal.frame2Dica.addView(imageView)
                                                        }

                                                        1 -> {
                                                            val imageAltura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                74f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            val imageLargura = TypedValue.applyDimension(
                                                                TypedValue.COMPLEX_UNIT_DIP,
                                                                160f,
                                                                resources.displayMetrics
                                                            ).toInt()

                                                            // Defina as propriedades da ImageView
                                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                            imageView.layoutParams = layoutParams
                                                            imageView.setImageDrawable(
                                                                ContextCompat.getDrawable(
                                                                    this,
                                                                    R.drawable.banco_vertical_d
                                                                )
                                                            )
                                                            bindingPrincipal.frame3Dica.addView(imageView)
                                                        }
                                                    }
                                                }
                                            }

                                            i = 2
                                            j = 2
                                        }
                                        i++
                                    }
                                    j++
                                    i = 0
                                }
                            }
                        }

                        900 -> {
                            val imageView = ImageView(this)
                            val strAux = imageView.toString().substring(25, 27)
                            imageID = strAux.toInt(16)
                            imageView.isClickable = true

                            imageView.setOnClickListener {
                                selectedImageView = it as ImageView
                                it.isSelected = true
                                //bindingPrincipal.dPad.isVisible = true
                            }

                            var i = 0
                            var j = 0
                            var livre = false
                            // Verifica se uma linha inteira é 0
                            while (i < ocupacaoAux.size) {
                                var contador = 0
                                while (j < ocupacaoAux[i].size) {
                                    if (ocupacaoAux[i][j] == 0) {
                                        contador++
                                    }
                                    j++
                                }
                                j = 0

                                if (contador == ocupacaoAux[i].size) {
                                    ocupacaoAux[i][j] = imageID
                                    ocupacaoAux[i][j+1] = imageID
                                    ocupacaoAux[i][j+2] = imageID

                                    when (i) {
                                        0 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                180f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                75f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.comoda_horizontal
                                                )
                                            )
                                            bindingPrincipal.frame1Dica.addView(imageView)
                                        }
                                        1 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                216f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                90f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.comoda_horizontal
                                                )
                                            )
                                            bindingPrincipal.frame2Dica.addView(imageView)
                                        }
                                        2 -> {
                                            val imageAltura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                259f,
                                                resources.displayMetrics
                                            ).toInt()

                                            val imageLargura = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP,
                                                108f,
                                                resources.displayMetrics
                                            ).toInt()

                                            // Defina as propriedades da ImageView
                                            val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                            layoutParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                                            imageView.layoutParams = layoutParams
                                            imageView.setImageDrawable(
                                                ContextCompat.getDrawable(
                                                    this,
                                                    R.drawable.comoda_horizontal
                                                )
                                            )
                                            bindingPrincipal.frame3Dica.addView(imageView)
                                        }
                                    }
                                    i = ocupacaoAux.size + 1
                                    j = ocupacaoAux.size + 1
                                    livre = true
                                }
                                i++
                            }

                            if (!livre) {
                                i = 0
                                j = 0
                                // Verifica se uma coluna inteira é 0
                                while (j < ocupacaoAux.size) {
                                    var contador = 0
                                    while (i < ocupacaoAux.size) {
                                        if (ocupacaoAux[i][j] == 0) {
                                            contador++
                                        }
                                        i++
                                    }
                                    i = 0

                                    if (contador == ocupacaoAux.size) {
                                        ocupacaoAux[i][j] = imageID
                                        ocupacaoAux[i+1][j] = imageID
                                        ocupacaoAux[i+2][j] = imageID

                                        when (j) {
                                            0 -> {
                                                val imageAltura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    90f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                val imageLargura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    216f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                // Defina as propriedades da ImageView
                                                val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                layoutParams.gravity = Gravity.START or Gravity.BOTTOM
                                                imageView.layoutParams = layoutParams
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.comoda_vertical_e
                                                    )
                                                )
                                                bindingPrincipal.frame3Dica.addView(imageView)
                                            }
                                            1 -> {
                                                val imageAltura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    90f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                val imageLargura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    216f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                // Defina as propriedades da ImageView
                                                val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                layoutParams.gravity = Gravity.CENTER
                                                imageView.layoutParams = layoutParams
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.comoda_vertical
                                                    )
                                                )
                                                bindingPrincipal.frame3Dica.addView(imageView)
                                            }
                                            2 -> {
                                                val imageAltura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    90f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                val imageLargura = TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP,
                                                    216f,
                                                    resources.displayMetrics
                                                ).toInt()

                                                // Defina as propriedades da ImageView
                                                val layoutParams = FrameLayout.LayoutParams(imageAltura, imageLargura)
                                                layoutParams.gravity = Gravity.END or Gravity.BOTTOM
                                                imageView.layoutParams = layoutParams
                                                imageView.setImageDrawable(
                                                    ContextCompat.getDrawable(
                                                        this,
                                                        R.drawable.comoda_vertical_d
                                                    )
                                                )
                                                bindingPrincipal.frame3Dica.addView(imageView)
                                            }
                                        }
                                        i = ocupacaoAux.size + 1
                                        j = ocupacaoAux.size + 1
                                    }
                                    j++
                                }
                            }
                        }

                        1200 -> {
                            val imageView = ImageView(this)
                            val strAux = imageView.toString().substring(25, 27)
                            imageID = strAux.toInt(16)
                            imageView.isClickable = true

                            imageView.setOnClickListener {
                                selectedImageView = it as ImageView
                                it.isSelected = true
                                //bindingPrincipal.dPad.isVisible = true
                            }

                            var i = 0
                            var j = 0

                            while (i < ocupacaoAux.size - 1) {
                                while (j < ocupacaoAux.size - 1) {
                                    if (ocupacaoAux[i][j] == 0 && ocupacaoAux[i][j+1] == 0 && ocupacaoAux[i+1][j] == 0 && ocupacaoAux[i+1][j+1] == 0) {
                                        ocupacaoAux[i][j] = imageID
                                        ocupacaoAux[i][j+1] = imageID
                                        ocupacaoAux[i+1][j] = imageID
                                        ocupacaoAux[i+1][j+1] = imageID

                                        when (i) {
                                            0 -> {
                                                when (j) {
                                                    0 -> {
                                                        val imageSize = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            120f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.mesa_esquerdo
                                                            )
                                                        )
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        val imageSize = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            120f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.mesa_direito
                                                            )
                                                        )
                                                        bindingPrincipal.frame2Dica.addView(imageView)
                                                    }
                                                }
                                            }

                                            1 -> {
                                                when (j) {
                                                    0 -> {
                                                        val imageSize = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            160f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.START
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.mesa_esquerdo
                                                            )
                                                        )
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }

                                                    1 -> {
                                                        val imageSize = TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP,
                                                            160f,
                                                            resources.displayMetrics
                                                        ).toInt()

                                                        // Defina as propriedades da ImageView
                                                        val layoutParams = FrameLayout.LayoutParams(imageSize, imageSize)
                                                        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                                                        imageView.layoutParams = layoutParams
                                                        imageView.setImageDrawable(
                                                            ContextCompat.getDrawable(
                                                                this,
                                                                R.drawable.mesa_direito
                                                            )
                                                        )
                                                        bindingPrincipal.frame3Dica.addView(imageView)
                                                    }
                                                }
                                            }
                                        }

                                        i = 2
                                        j = 2
                                    }
                                    j++
                                }
                                i++
                                j = 0
                            }
                        }
                    }
                }
                k++
            }
        }
    }

    private fun knapSack(memoization: Array<IntArray>, Capacidade: Int, pesos: IntArray, valores: IntArray, qtdItens: Int): Int {
        for (i in 0..qtdItens) {
            for (w in 0..Capacidade) {
                if (i == 0 || w == 0)
                    memoization[i][w] = 0
                else {
                    if (pesos[i - 1] <= w) {
                        memoization[i][w] = kotlin.math.max(valores[i - 1] + memoization[i - 1][w - pesos[i - 1]], memoization[i - 1][w])
                    }
                    else {
                        memoization[i][w] = memoization[i - 1][w]
                    }
                }
            }
        }
        return memoization[qtdItens][Capacidade]
    }

    private fun selecionados(memoization: Array<IntArray>, capacidade: Int, valores: IntArray, pesos: IntArray, qtdItens: Int): List<Int> {
        /*var j = capacidade
        var i = qtdItens

        while (i > 0 && j > 0) {
            //println(memoization[i][j])
            //println(memoization[i-1][j])
            //println("j = $j")
            //println("--------------------------------")
            if(memoization[i][j] == memoization[i-1][j]) {
                valores[j-1] = 0
                //println("a")
            }
            else {
                var k = 0
                var aux = j
                while (k < pesos[i-1]) {
                    valores[aux--] = 0
                    k++
                    println("k = $k")
                    println(valores[aux])
                }
                //println(memoization[i][j])
                //println("b")
                j -= pesos[i-1]
            }

            i--
        }

        valores.forEach { e ->
            println(e)
        }*/
        var w = capacidade
        var res = memoization[qtdItens][capacidade]
        val itensEscolhidos = mutableListOf<Int>()

        for (i in qtdItens downTo 1) {
            if (res <= 0) break
            // este item está incluído na solução ótima
            if (res == memoization[i - 1][w]) continue
            else {
                // este item está incluído
                itensEscolhidos.add(i - 1)
                // como este item está incluído, seu valor e peso devem ser subtraídos
                res -= valores[i - 1]
                w -= pesos[i - 1]
            }
        }
        return itensEscolhidos
    }
}
