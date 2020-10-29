package tam.pa.datamahasiswa

import tam.pa.datamahasiswa.model.User

interface IView {
    fun getData(list: MutableList<User>)
    fun onUpdate(list: MutableList<User>)
    fun getMsg(msg: String)
}