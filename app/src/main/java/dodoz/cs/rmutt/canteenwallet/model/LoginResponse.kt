package dodoz.cs.rmutt.canteenwallet.model

data class LoginResponse(val error: Boolean, val message:String, val user: User)

data class getData(val error: Boolean, val  message: String, val user: GetUser)

data class getSearch(val error: Boolean, val  message: String, val user: getNameUser)

data class Transfer(val error: Boolean, val  message: String , val user: Boolean)

data class TransferA(val error: Boolean, val  message: String, val user: Boolean)




