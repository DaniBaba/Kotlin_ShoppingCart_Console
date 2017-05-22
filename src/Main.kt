import java.util.*
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

var product: Product = Product()
var basket: Basket = Basket()
var count: Int = 0

val es_6 = "\t\t\t\t\t\t"
val es_5 = "\t\t\t\t\t"
val es_3 = "\t\t\t "
val es_2 = "\t\t"

fun main(args: Array<String>) {

    product.save( Product(id = 1, code = "CR", name = "Chicken Roll", rate =  (110).toDouble()))
    product.save( Product(id = 2, code = "ZB", name = "Zinger Burger", rate =  (250).toDouble()))
    product.save( Product(id = 3, code = "JB", name = "Jalapeno Burger", rate =  (450).toDouble()))

    println("\nWe have delicious food, Go for your basket. \n")
    println("Code $es_5 Item $es_5 Rate")
    var lstProducts: MutableList<Product> = product.getListOfProducts()
    for (p in lstProducts){
        println(p.code + es_6 + p.name + es_3 + p.rate)
    }
    getOrder()
}

fun getOrder(){

    var scanner = Scanner(System.`in`)
    var input: String = ""
    if(count == 0){
        println("\nDo you want to buy any item? Press (y/n)")
        input = scanner.next()
    }
    else{
        input = "y"
    }

    if(input.equals("y", true)){

        if(count == 0)
            println("\nPlace your order in a basket.")

        count++
        print("Code : ")
        val code: String = scanner.next()
        val isCodeExists: Boolean = product.containCode(code)
        if(isCodeExists){
            val productObj: Product? = product.search(code)
            val productId: Int = if(productObj != null) productObj.id else 0
            print("Qty : ")
            var qty: Int = scanner.nextInt()
            qty = if (qty > 0) qty else 1
            basket.save(Basket(productId, qty))

            println("\nDo you want to place more items? Press (y/n)")
            input = scanner.next()
            if(input.equals("y", true)){
                getOrder()
            }
            else{
                createInvoice()
            }
        }
        else{
            println("Error! Invalid Code.\n")
            createInvoice()
        }
    }
    else{
        println("\nThanks for coming :-)")
        return
    }
}

fun createInvoice(){
    var counter: Int = 1
    val lstBasket: MutableList<Basket> = basket.getItemOfBasket()
    if(lstBasket.size > 0) {
        println("\nInvoice Detail :\n")
        println("SNo $es_2 Code $es_5 Item $es_5 Rate $es_2    Qty $es_2 Amount")
        for (b in lstBasket) {
            val p: Product? = product.search(b.id)
            val amount = if (p != null) p.rate * b.qty else 0
            println(counter.toString() + es_3 + p?.code + es_6 + p?.name + es_3 + p?.rate + es_3 + b.qty + es_3 + amount)
            counter++
        }
    }
}