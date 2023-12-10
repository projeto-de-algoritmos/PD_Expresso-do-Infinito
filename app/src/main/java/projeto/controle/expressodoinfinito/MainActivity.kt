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
        window?.statusBarColor = ContextCompat.getColor(this, R.color.black)
        val decorView: View = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        decorView.systemUiVisibility = uiOptions

        var ocupacao = Array(3) {IntArray(3)}
        var selectedImageView: ImageView? = null
        var imageID: Int

        bindingPrincipal = PrincipalBinding.inflate(layoutInflater)
        bindingInicio = InicioBinding.inflate(layoutInflater)
        setContentView(bindingInicio.root)

        bindingInicio.button.setOnClickListener {
            setContentView(bindingPrincipal.root)
            bindingPrincipal.dPad.isVisible = false
        }
        bindingPrincipal.buttonVoltar.setOnClickListener {
            setContentView(bindingInicio.root)
        }
        bindingPrincipal.buttonDica.setOnClickListener {
            bindingPrincipal.cardviewDica.isVisible = true
            bindingPrincipal.dPad.isVisible = false
        }
        bindingPrincipal.cardviewDica.setOnClickListener {
            bindingPrincipal.cardviewDica.isVisible = false
        }

        bindingPrincipal.buttonMesa.setOnClickListener {
            bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                bindingPrincipal.dPad.isVisible = true
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
            bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                bindingPrincipal.dPad.isVisible = true
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
            bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                bindingPrincipal.dPad.isVisible = true
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
            bindingPrincipal.dPad.isVisible = false
            val imageView = ImageView(this)
            val strAux = imageView.toString().substring(25, 27)
            imageID = strAux.toInt(16)
            imageView.isClickable = true

            imageView.setOnClickListener {
                selectedImageView = it as ImageView
                it.isSelected = true
                bindingPrincipal.dPad.isVisible = true
            }

            var i = 0
            var j = 0

            while (i <= 2) {
                while (j <= 2) {
                    if (ocupacao[i][j] == 0) {
                        ocupacao[i][j] = imageID
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
                /*
                2 -> {
                    for (i in 0..1) {
                        for (j in 0..1) {
                            if (ocupacao[i][j] == selectedImageView.toString().substring(25, 27).toInt(16)) {
                                if (ocupacao[i][j + 1] == ocupacao[i][j]) {
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

                3 -> {}
                */
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

        bindingPrincipal.buttonDescarregar.setOnClickListener {
            bindingPrincipal.frame1.removeAllViews()
            bindingPrincipal.frame2.removeAllViews()
            bindingPrincipal.frame3.removeAllViews()
            bindingPrincipal.dPad.isVisible = false

            for (i in ocupacao.indices)
                for (j in ocupacao[i].indices)
                    ocupacao[i][j] = 0
        }

        var valores = intArrayOf(200, 200, 200, 200, 200, 200, 200, 200, 200, 250, 250, 250, 250, 900, 900, 900, 1200)
        var pesos = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4)
        var capacidade = 9
        var qtdItens = valores.size

        val memoization = Array(qtdItens + 1) { IntArray(capacidade + 1) }

        knapSack(memoization, capacidade, pesos, valores, qtdItens)

        //selecionados(memoization, capacidade, pesos, qtdItens)
/*
        memoization.forEach { linha ->
            println(linha.joinToString(" "))
        }*/
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

    private fun selecionados(memoization: Array<IntArray>, capacidade: Int, pesos: IntArray, qtdItens: Int) {
        var i = capacidade
        var j = qtdItens

        while (i != 0 || j != 0) {
            if(memoization[i][j] != memoization[i][j-1]) {


                j -= pesos[i]
            }

            i--
        }
    }
}
