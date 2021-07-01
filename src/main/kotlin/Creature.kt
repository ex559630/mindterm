import java.util.*

interface Fightable{
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int
    val damageRoll : Int
        get() = (0 until diceCount).map{
            Random().nextInt(diceSides) + 1
        }.sum()

    fun attack(opponent: Fightable):Int
}
abstract class Monster(val name:String,
                       val description: String,
                       override var healthPoints: Int): Fightable{
            override fun attack (opponent: Fightable): Int{
                val damageDealt = damageRoll
                opponent.healthPoints -= damageDealt
                return damageDealt
            }
}
class Goblin(name: String ="哥布林",
             description: String = "一個長相醜陋哥布林",
             healthPoints: Int = 30): Monster(name,description,healthPoints)
{
    override val diceCount = 2
    override val diceSides = 5
}


class Drunkard(name: String ="醉鬼",
               description: String = "一堆醉鬼",
               healthPoints: Int = 50): Monster(name,description,healthPoints){
    override val diceCount=1
    override val diceSides=15
               }

class Thief(name: String ="小偷",
               description: String = "身手不得了的小偷",
               healthPoints: Int = 50): Monster(name,description,healthPoints){
    override val diceCount=7
    override val diceSides=3
}
