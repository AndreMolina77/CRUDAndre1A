package andre.molina.myapplication

import Modelos.ClaseConexion
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Mandar a llamar a todos los elementos de la vista
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
        val txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val rcvDatos = findViewById<RecyclerView>(R.id.rcvDatos)

        //Ponerle un layout a mi RecyclerView
        rcvDatos.layoutManager = LinearLayoutManager(this)

        //Crear un adaptador. El adaptador es el que revisa si hay datos nuevos en la base de datos, nosotros creamos
        //el adaptador

        val miAdaptador = Adaptador(listaDeDatos)



        //2-Porgramar el botón de agregar
        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                //Guardar datos
                //1- Crear un objeto de la clase conexión
                val objConexion = ClaseConexion().cadenaConexion()

                //2- Crear una variable que sea igual a PrepareStatement
                val addProducto = objConexion?.prepareStatement("insert into tbProduct values(?, ?, ?)")!!
                addProducto.setString(1, txtNombre.text.toString())
                addProducto.setInt(2, txtCantidad.text.toString().toInt())
                addProducto.setInt(3, txtPrecio.text.toString().toInt())
                addProducto.executeUpdate()
            }

            //Mostrar datos


        }

    }
}

//Creamos la clae Adaptador afuera de tod0 lo demás porque
//están dentro de la clase MaINActivity
class Adaptador(private val Datos:Array<String>) {

}