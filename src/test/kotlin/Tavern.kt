import kotlin.math.roundToInt
import java.io.File
const val TAVERN_NAME = "Taerny's Folly"
var playerGold = 10
var playerSilver =10
val patronList = mutableListOf("Eli","Mordoc","Sophie")
val lastName = listOf("Ironfoot","Fernsworth","Baggins")
val uniquePatrons = mutableSetOf<String>()
/*
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\r\n")*/
val patronGold=mapOf("Eli" to 10.5,"Mordoc" to 8.0,"Sophie" to 5.5)
fun main(){

    if (patronList.contains("Eli")){
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Elo isn't here.")
    }
    if (patronList.containsAll(listOf("Sophie","Mordoc"))){
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }
    
    patronList.forEachIndexed{index,patron ->
        println("Good evening, $patron - you're #${index+1} in line")
        placeOrder(patron,menuList.shuffled().first())
        println("-----------------------------")
    }
    menuList.forEachIndexed{index,data->
        println("$index : $data")
    }
    (0..9).forEach{
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons+=name
    }
    println(uniquePatrons)
    var orderCount=0
    while (orderCount <= 9){
        placeOrder(uniquePatrons.shuffled().first(),
            menuList.shuffled().first())
        orderCount++
    }
    println(patronGold)
    println(patronGold["Eli"])
    println(patronGold["Mordoc"])
    println(patronGold["Sophie"])
}
fun performPurchase(price: Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    //println("Total purse: $totalPurse")
    println("purchasing item for $price")
    val remainingBalance = totalPurse - price
    //println(remainingBalance)
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")
    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 *100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}
private fun displayBalance(){
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}
private fun toDragonSpeak(phrase: String)=
    phrase.replace(Regex("[aeiouAEIOU]")){
        when(it.value){
            "a","A" -> "4"
            "e","E" -> "3"
            "i","I" -> "1"
            "o","O" -> "0"
            "u","U" -> "|_|"
            else -> it.value
        }

    }

private fun placeOrder(patronName: String,menuData:String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    //println("佐藤 speaks with $tavernMaster about their order.")
    println("$patronName speaks with $tavernMaster about their order")
    val (type,name,price) =menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    //val message = "佐藤 買了一杯 $name ($type) 花了 $price"
    println(message)
    //performPurchase(price.toDouble())
    val phrase = if (name == "Dragon's Breath") {
        "$patronName 驚呼: ${toDragonSpeak("Ah, delicious $name")}"
    } else {
        "$patronName 說: 謝謝這杯$name"
    }

    println(phrase)
}