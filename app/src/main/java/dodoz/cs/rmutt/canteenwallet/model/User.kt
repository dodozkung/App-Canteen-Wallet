package dodoz.cs.rmutt.canteenwallet.model

data class User(val wallet_id:Int, val balance:Float, val username:String, val name:String, val idcard:String, val passconfirm:String, val phone:String, val status:String)

data class GetUser(val balance:Float, val name:String, val idcard:String, val status:String)

data class getNameUser(val name1:String)



