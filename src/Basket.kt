class Basket{

    var id: Int = 0
    var qty: Int = 0
    var lstData = mutableListOf<Basket>()

    constructor(){

    }

    constructor(id: Int, qty: Int) {
        this.id = id
        this.qty = qty
    }

    fun save(obj: Basket) {
        lstData.add(obj)
    }

    fun search(id: Int): Basket? {
        return lstData.find { it.id == id }
    }

    fun getItemOfBasket(): MutableList<Basket>{
        return lstData
    }

}