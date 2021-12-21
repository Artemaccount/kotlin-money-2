const val MIN_COMMISSION = 35_000
const val VK_PAY = "vkPay"
const val MASTERCARD = "MasterCard"
const val MAESTRO = "Maestro"
const val VISA = "Visa"
const val MIR = "Мир"
const val MONTH_LIMIT = 75_000_000

fun main(){
    printCommission(getCommission(VK_PAY, 100, 6000))
    printCommission(getCommission(MAESTRO, remittanceAmount = 6000))
    printCommission(getCommission(MIR, 100, remittanceAmount = 6000))
    printCommission(getCommission(MASTERCARD, 100000000, 6000))
}

fun getCommission(type:String=VK_PAY, monthTransferSum:Int=0, remittanceAmount:Int): Int{
    return when{
        monthTransferSum <= MONTH_LIMIT && (type == MASTERCARD || type == MAESTRO) -> 0
        type == VK_PAY -> 0
        type == VISA || type == MIR -> {
            if((remittanceAmount/10000*75) < MIN_COMMISSION) 35 else (remittanceAmount/10000*75)
        }
        else -> remittanceAmount/1000*60 + 20000
    }
}

fun printCommission(commission:Int){
    println("Коммиссия составляет: $commission копеек")
}