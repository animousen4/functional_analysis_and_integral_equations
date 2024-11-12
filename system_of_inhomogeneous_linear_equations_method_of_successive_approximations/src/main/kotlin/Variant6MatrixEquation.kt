
import org.jetbrains.kotlinx.multik.api.*
import org.jetbrains.kotlinx.multik.ndarray.data.*
import org.jetbrains.kotlinx.multik.ndarray.operations.*
class Variant6MatrixEquation : MatrixEquation {
    override fun A(): D2Array<Double> {
        return mk.ndarray(mk[mk[3.2, -11.5, 3.8], mk[0.8, 1.3, -6.4], mk[2.4, 7.2, -1.2]])
    }

    override fun B(): D2Array<Double> {
        return mk.ndarray(mk[mk[2.8, -6.5, 4.5]]).transpose()
    }
}