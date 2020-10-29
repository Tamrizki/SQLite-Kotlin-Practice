package tam.pa.datamahasiswa

import android.content.Context
import tam.pa.datamahasiswa.helper.DbHelper
import tam.pa.datamahasiswa.model.User

class Presenter(view: IView, ctx: Context){
    val view = view
    val dbHelper = DbHelper(ctx)

    fun onSimpanData(user: User){
        if (dbHelper.insertData(user)){
            view.getMsg("Penyimpanan data berhasil")
            var list: MutableList<User> = dbHelper.readData()
            view.onUpdate(list)
        }else{
            view.getMsg("Penyimpanan data tidak berhasil!")
        }
    }
    fun onTampilData(){
        var list: MutableList<User> = dbHelper.readData()
        view.getData(list)
    }
}