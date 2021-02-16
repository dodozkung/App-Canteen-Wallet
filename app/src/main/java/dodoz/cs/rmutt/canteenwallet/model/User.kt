package dodoz.cs.rmutt.canteenwallet.model

data class User(val N_ID :Int , val wallet_id:String, val balance:Float, val username:String, val name:String, val idcard:String, val passconfirm:String, val phone:String, val status:String)

data class GetUser(val balance:Float, val name:String, val idcard:String, val status:String)

data class getNameUser(val name1:String)





