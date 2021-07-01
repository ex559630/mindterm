import java.io.File

class Player (_name:String,
              override var healthPoints:Int = 100,
              val isBlessed:Boolean,
              private val isImmortal:Boolean) : Fightable {

    override val diceCount = 3
    override val diceSides = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed){
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }

    var name = _name
        get() = field.capitalize()
        //get() = "來自 $hometown 的 ${field.capitalize()}"
            private set(value) {
            field = value.trim()
        }
    init {
        name= _name
        require(healthPoints >0){"healPoints must be greater then zero."}
        require(name.isNotBlank()){"Player must have a name."}
    }
    //val hometown by lazy { selectHometown() }
    var currentPosition = Coordinate(0,0)


    /*private fun selectHometown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()*/

    constructor(name:String):this(name,
        isBlessed = true,
        isImmortal = false){
        //if (name.toLowerCase() == "kar") healthPoints = 40
    }
    fun auraColor() = if (healthPoints >= 50 && isBlessed ) "光環" else "無光環"

    fun formatHealthStatus() = when(healthPoints){
        100 -> "狀況良好!"
        in 90..99 -> "有一些擦傷."
        in 75..89 -> if (isBlessed){
            "有一些小傷口，但回復很快."
        }else {
            "有一些小傷口."
        }
        in 15..74 -> "受傷."
        else -> "情況很糟糕."
    }




}
/*
field 不允許直接定義
get 讀取屬性
set 設定屬性
*/