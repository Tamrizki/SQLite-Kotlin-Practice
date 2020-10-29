package tam.pa.datamahasiswa.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import tam.pa.datamahasiswa.model.User

val DATABASE_NAME: String  = "myDb"
val TABLE_NAME: String = "mahasiswa"
val COL_NIM: String = "nim"
val COL_NAME: String = "nama"
val COL_PRODY: String = "prody"
val COL_ABSEN: String = "absen"

class DbHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME+" (" +
                COL_NIM+" INTEGER PRIMARY KEY," +
                COL_NAME+" VARCHAR(35)," +
                COL_PRODY+" VARCHAR(35)," +
                COL_ABSEN+" INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: User): Boolean{
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NIM, user.nim)
        cv.put(COL_NAME, user.name)
        cv.put(COL_PRODY, user.prody)
        cv.put(COL_ABSEN, user.absen)
        var result = db.insert(TABLE_NAME, null, cv)

        return result == -1.toLong()
    }

    fun readData(): MutableList<User>{
        var list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM "+ TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                list.add(User(
                    result.getInt(result.getColumnIndex(COL_NIM)),
                    result.getString(result.getColumnIndex(COL_NAME)),
                    result.getString(result.getColumnIndex(COL_PRODY)),
                    result.getInt(result.getColumnIndex(COL_ABSEN))
                ))
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list;
    }

    fun deleteData(nim: String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COL_NIM+" =?", arrayOf(nim.toString()))
        db.close()
    }
}