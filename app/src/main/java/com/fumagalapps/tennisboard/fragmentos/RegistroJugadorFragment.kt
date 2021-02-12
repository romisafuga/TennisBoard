package com.fumagalapps.tennisboard.fragmentos

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.fumagalapps.tennisboard.R
import com.fumagalapps.tennisboard.interfaces.IComunicaFragmentos
import com.google.android.material.textfield.TextInputEditText

import android.content.pm.PackageManager.*
import android.provider.MediaStore
import android.text.Editable
import android.widget.*
import com.fumagalapps.tennisboard.adapter.AdminJugadores
import com.fumagalapps.tennisboard.model.TbJugadores
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import kotlinx.android.synthetic.main.fragment_registro_jugadores.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistroJugadorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroJugadorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var comunica: IComunicaFragmentos
    lateinit var actividad: Activity
    lateinit var edtNombre: TextInputEditText
    lateinit var rdgGenero: RadioGroup
    lateinit var rdbMasculino: RadioButton
    lateinit var rdbFemenino: RadioButton
    lateinit var rdgBrazo:RadioGroup
    lateinit var rdbDerecho: RadioButton
    lateinit var rdbIzquierdo: RadioButton
    lateinit var btnGaleria: Button
    lateinit var btnFoto: Button
    lateinit var btnGrabar: ImageButton
    lateinit var btnAtras: ImageButton
    lateinit var famMenAcc: FloatingActionsMenu
    lateinit var fabConfirmar: FloatingActionButton
    lateinit var fabBorra: FloatingActionButton
    lateinit var fabAgrega: FloatingActionButton
    lateinit var imvFoto: ImageView


    var genero: Char = 'N'
    var brazo: Char = 'N'


    val REQUIERE_GALERIA = 1001
    val REQUIERE_CAMERA = 1002
    var foto: Uri? = null
    var direccFoto: String? = null
    var altajug = 1

    var idJug: String? = "-1"

    val AdminJugador = AdminJugadores()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_registro_jugadores, container, false)

        edtNombre = vista.findViewById(R.id.tie_Nombre)
        rdgGenero = vista.findViewById(R.id.rdg_Genero)
        rdgBrazo = vista.findViewById(R.id.rdg_Brazo)
        rdbMasculino = vista.findViewById(R.id.rdb_Masculino)
        rdbFemenino = vista.findViewById(R.id.rdb_Femenino)
        rdbDerecho = vista.findViewById(R.id.rdb_Derecho)
        rdbIzquierdo = vista.findViewById(R.id.rdb_Izquierdo)
        btnGaleria = vista.findViewById(R.id.btn_Galeria)
        btnFoto = vista.findViewById(R.id.btn_Foto)
        btnGrabar = vista.findViewById(R.id.imb_grabar)
        btnAtras = vista.findViewById(R.id.imb_Atras)
        famMenAcc = vista.findViewById(R.id.fam_MenuAcciones)
        fabConfirmar = vista.findViewById(R.id.fab_Confirmar)
        fabBorra = vista.findViewById(R.id.fab_Borrar)
        fabAgrega = vista.findViewById(R.id.fab_Agregar)
        imvFoto = vista.findViewById(R.id.imv_ItemFoto)

        // atrapa el id del jugador en caso de venir de la lista de jugadores
        // para hacer cambios

        idJug = arguments?.getString("idJug", "-1")
        if(idJug != null) {
            altajug=0
            val jugaCambio = AdminJugador.getName(idJug.toString())
            edtNombre.setText(jugaCambio.strNombre)
            imvFoto.setImageURI(Uri.parse(jugaCambio.lnkFoto.toString()))
            direccFoto = jugaCambio.lnkFoto
            if(jugaCambio.chrGenero=='F') rdgGenero.check(R.id.rdb_Femenino)
            else rdgGenero.check(R.id.rdb_Masculino)
            if(jugaCambio.chrBrazo=='D') rdgBrazo.check(R.id.rdb_Derecho)
            else rdgBrazo.check(R.id.rdb_Izquierdo)
        } else idJug = "-1"

        fabAgrega.setOnClickListener {
            // debe limpiar los campos y prender la bandera de dar de alta jugador
            limpiaCampos()
            altajug = 1
            famMenAcc.collapse()
        }

        fabBorra.setOnClickListener {
            // eliminara el registro seleccionado
            famMenAcc.collapse()
        }

        fabConfirmar.setOnClickListener {

            if (valida_campos() == true) {
                val jugador =
                    TbJugadores(idJug!!.toInt(), edtNombre.text.toString(), genero, brazo, direccFoto.toString())
                if (altajug == 1)
                    AdminJugador.addJugador(jugador)
                else
                    AdminJugador.updateJugador(jugador)
                altajug=1
                limpiaCampos()
            }

            famMenAcc.collapse()
        }

        // detectar el boton cuando se pulse para abrir galeria
        btnGaleria.setOnClickListener {
            // verificar la version de android instalada en el telefono se mayor que Mashmelow
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                // acceder al checkSelPermission de Android
                //--1 preguntamos si NO tiene permiso
                if (checkSelfPermission(actividad, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PERMISSION_DENIED
                ) {
                    // Aqui aparece la pantalla de pedir permiso al usuario
                    val permisoArchivos = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permisoArchivos, REQUIERE_GALERIA)
                } else {
                    //--1 si tenia permiso
                    muestraGaleria()
                }
            } else {
                // tiene version de lollipor hacia abajo por default tiene permiso
                muestraGaleria()
            }
        }
        // detectar el boton cuando se pulse para abrir camara
        btnFoto.setOnClickListener() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(actividad, Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(actividad, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    // pedirle permiso al usuario
                    val permisosCamara = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permisosCamara, REQUIERE_CAMERA)
                } else {
                    abreCamara()
                }
            } else {
                abreCamara()
            }
        }

        return vista
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            comunica = actividad as IComunicaFragmentos
        }
    }

    fun valida_campos(): Boolean {
        var genero: Char
        var brazo: Char
        // valida que este capturado el genero
        if (rdbMasculino.isChecked)
            genero = 'M'
        else if (rdbFemenino.isChecked)
            genero = 'F'
        else {
            Toast.makeText(actividad, getString(R.string.toa_error_genero), Toast.LENGTH_LONG)
                .show()
            return false
        }
        // valida que este capturado el brazo
        if (rdbDerecho.isChecked)
            brazo = 'D'
        else if (rdbIzquierdo.isChecked)
            brazo = 'I'
        else {
            Toast.makeText(actividad, getString(R.string.toa_error_brazo), Toast.LENGTH_LONG).show()
            return false
        }
        // valida que este capturada la foto
        if (direccFoto.equals(null)) {
            Toast.makeText(actividad, getString(R.string.toa_error_foto), Toast.LENGTH_SHORT)
                .show()
            return false
        }
        // valida que este capturado el nombre
        if (edtNombre.text.toString() != null
            && edtNombre.text.toString().trim().length != 0
        ) {
            return true
        } else {
            Toast.makeText(actividad, getString(R.string.toa_error_nombre), Toast.LENGTH_SHORT)
                .show()
            return false
        }


    }

    // para limpiar todos los campos
    fun limpiaCampos() {
        edtNombre.text = null
        rdg_Brazo.clearCheck()
        rdg_Genero.clearCheck()
        imv_ItemFoto.setImageURI(null)
        direccFoto = null
        idJug = "-1"
    }

    // checamos la respuesta a la solicitud de permiso por parte del usuario
    override fun onRequestPermissionsResult(
        requestCode: Int,  // respuesta a la solicitud 1-No
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUIERE_GALERIA -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    muestraGaleria()
                else
                    Toast.makeText(
                        actividad, "No puedes acceder a tus imagenes",
                        Toast.LENGTH_SHORT
                    ).show()
            }
            REQUIERE_CAMERA -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    abreCamara()
                else
                    Toast.makeText(
                        actividad, "No puedes acceder a la camara",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }


    fun muestraGaleria() {
        val intentGaleria = Intent(Intent.ACTION_PICK)  // El ACTION_PICK es para permitir
        // recuperar una opcion
        intentGaleria.type = "image/*"  // solo muestra imagenes
        startActivityForResult(intentGaleria, REQUIERE_GALERIA)
    }

    fun abreCamara() {
        var value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "Nueva Imagen")
        value.put(MediaStore.Images.Media.DISPLAY_NAME, "Hola")
        foto = actividad.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
        var camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
        startActivityForResult(camaraIntent, REQUIERE_CAMERA)
    }

    // Recuperamos la imagen seleccionada

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // pregunta si la respuesta es OK y ademas que el requerimiento haya sido de la galeria
        if (resultCode == Activity.RESULT_OK && requestCode == REQUIERE_GALERIA) {
            imv_ItemFoto.setImageURI(data?.data)
            direccFoto = data?.data!!.pathSegments[2]
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUIERE_CAMERA) {
            imv_ItemFoto.setImageURI(foto)
            // asigno la direccion de donde esta la foto
            direccFoto = foto.toString()

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JugadoresFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroJugadorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}