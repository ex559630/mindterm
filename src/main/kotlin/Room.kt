open class Room(val name:String){
    protected open val dangerLevel = 5
    open var monster: Monster? = Goblin()
    fun description() = "所在地: $name\n"+
            "危險等級: $dangerLevel\n"+
            "生物: ${monster?.description ?: "空無一物."}"
    open fun load()= "這裡沒什麼可看的..."
}

open class TownSquare : Room("廣場"){
    override val dangerLevel = super.dangerLevel-5
    override var monster: Monster? = null
    private var bellSound = "噹!~咚!~噹!~"
    final override fun load() = "鎮民們在歡呼你的到來。\n${ringBell()}"
    fun ringBell() = "鐘樓在宣布你的到來! $bellSound"
}
open class LongCorridor : Room("長廊"){
    override val dangerLevel = super.dangerLevel
    override fun load() = "火把整齊地放置在長廊兩旁。"
    override var monster:Monster? = Goblin()
}

open class Alley : Room("巷弄中") {
    override val dangerLevel = super.dangerLevel + 7
    override fun load() = "與熱鬧的街上比起來，彷彿是不同世界"
    override var monster: Monster? = Thief()
}
open class Tavern : Room("酒館") {
    override val dangerLevel = super.dangerLevel + 3
    override fun load() = "酒店老闆正在擦玻璃杯!"
    override var monster: Monster? = Drunkard()
}
