import com.sun.org.apache.xpath.internal.operations.Bool

class Product{

    var id: Int = 0
    var code: String = ""
    var name: String = ""
    var rate: Double = 0.00

    private var dataList = mutableListOf<Product>()

    constructor(){

    }

    constructor(id: Int, code: String, name: String, rate: Double) {
        this.id = id
        this.code = code
        this.name = name
        this.rate = rate
    }

    fun save(obj: Product): Boolean {
        return dataList.add(obj)
    }

    fun search(id: Int): Product?{
        return dataList.find { it.id == id }
    }

    fun search(code: String): Product? {
        return dataList.find { it.code == code }
    }

    fun containCode(code: String): Boolean {
        val obj: Product? = search(code)
        return if (obj != null) true else false
    }

    fun getListOfProducts(): MutableList<Product> {
        return dataList
    }
}
