import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(value = Parameterized::class)
class MainKtAdvancedTest(
    private val userType: String,
    private val monthTransferSum: Int,
    private val expectedDiscount: Int,
    private val remittanceAmount: Int
) {

    @Test
    fun commission_correct_values_test(){
        val actualDiscount = getCommission(userType, monthTransferSum, remittanceAmount)
        assertEquals(expectedDiscount, actualDiscount)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: isValid({0})={1}")
        fun data(): List<Array<out Any>> {
            return arrayListOf(
                arrayOf(VK_PAY, 10, 0, 6000),
                  arrayOf(MAESTRO, 500,  0, 6000),
                   arrayOf(MASTERCARD, 100000000, 20360, 6000),
                arrayOf("---", 500, 20360, 6000),
                        arrayOf(VISA, 10, 35, 35),
            ).toList()
        }
    }


}


