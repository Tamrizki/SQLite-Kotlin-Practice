package tam.pa.datamahasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import tam.pa.datamahasiswa.adapter.AdapterList
import tam.pa.datamahasiswa.model.User

class MainActivity : AppCompatActivity(), IView {
    lateinit var tieNim: TextInputEditText
    lateinit var tieNama: TextInputEditText
    lateinit var tiePrody: TextInputEditText
    lateinit var tieAbsen: TextInputEditText
    lateinit var btnSImpan: Button
    lateinit var rvUser: RecyclerView
    lateinit var iView: IView
    private lateinit var adapter: AdapterList
    lateinit var listUser: MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        val presenter = Presenter(iView, this)
        presenter.onTampilData()
        btnSImpan.setOnClickListener {
            presenter.onSimpanData(DataUser())
        }
    }

    fun init(){
        tieNim = findViewById(R.id.tieNim)
        tieNama = findViewById(R.id.tieNama)
        tiePrody = findViewById(R.id.tiePrody)
        tieAbsen = findViewById(R.id.tieAbsen)
        btnSImpan = findViewById(R.id.btnSImpan)
        rvUser = findViewById(R.id.rvUser)
        iView = this
    }

    fun DataUser(): User{
        var user = User(
            tieNim.text.toString().toInt(),
            tieNama.text.toString(),
            tiePrody.text.toString(),
            tieAbsen.text.toString().toInt()
        )
        tieNim.text = "".toEditable()
        tieNama.text = "".toEditable()
        tiePrody.text = "".toEditable()
        tieAbsen.text = "".toEditable()
        return user
    }
    override fun getData(list: MutableList<User>) {
        listUser = list
        adapter = AdapterList(this, listUser)
        val layoutManager = LinearLayoutManager(this)
        rvUser.setHasFixedSize(true)
        rvUser.layoutManager = layoutManager
        rvUser.adapter = adapter
    }

    override fun onUpdate(list: MutableList<User>) {
        listUser = list
        adapter.notifyDataSetChanged()
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun getMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}