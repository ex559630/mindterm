import java.lang.IllegalArgumentException
import kotlin.system.exitProcess

fun main(){
    Game.play()
}
object Game {
    var player = Player("佐藤")
    private var currentRoom: Room = TownSquare()
    private val roomTavern : Room = Tavern()
    private val roomLongCorridor : Room = LongCorridor()
    private val roomAlley: Room = Alley()

    //private val roomLongCorridor : Room = LongCorridor()
    private var worldMap = listOf(
        listOf(currentRoom, roomTavern,roomAlley),
        listOf(roomLongCorridor,Room("Generic Room")
    ))

    init {
        println("歡迎, 冒險家")
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())
            println("> 輸入你的指令:")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player):String? {
        println("${player.name}")
        println("光環: ${player.auraColor()}\n" +
                "祝福: ${if (player.isBlessed) "是" else "否"}")
        println("血量: ${player.healthPoints}")
        println("身體的狀態: ${player.formatHealthStatus()}")
        return ""
    }
    private class GameInput(arg:String?){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })
        fun processCommand() = when (command.toLowerCase()){
            "move","移動" -> move(argument)
            "fight","戰鬥" -> fight()
            "exit","quit","離開" -> exitSystem()
            "status","狀態" -> printPlayerStatus(player)
            else -> commandNotFound()
        }
    }
    private fun move(directionInput: String)=
        try{
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds){
                throw IllegalStateException("$direction is out bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "你向 $direction 移動到了${newRoom.name}.\n"+
            "${newRoom.load()}" } catch(e: Exception){
                "無效方向: $directionInput"
            }
    private fun fight()= currentRoom.monster?.let{
        while(player.healthPoints >0 && it.healthPoints >0){
            slay(it)
            Thread.sleep(1000)
        }
        "指令完成."
    }?: "這裡沒有戰鬥"


    private fun slay(monster: Monster){
        println("${monster.name} 造成 ${monster.attack(player)} 點傷害!")
        println("${player.name} 造成 ${player.attack(monster)} 點傷害!")

        if (player.healthPoints <= 0){
            println(">>>> 你輸了，謝謝你的遊玩。 <<<<")
            exitProcess(0)
        }
        if (monster.healthPoints <= 0 ){
            println(">>>> ${monster.name} 輸了。 <<<<")
            currentRoom.monster = null
        }
    }
    private fun exitSystem(){
        println("${player.name} 離開了")
        exitProcess(-1)
    }
    private fun commandNotFound() = "我不太確定你要做什麼!"
}