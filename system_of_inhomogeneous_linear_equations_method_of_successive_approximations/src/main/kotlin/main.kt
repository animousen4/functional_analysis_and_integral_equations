import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import kotlin.math.pow

fun main() {
    val solver = SystemOfInhomogeneousLinearEquationsMethodOfSuccessiveApproximations()

    val equation = Variant6MatrixEquation()

    val epsilon = 10.0.pow(-4)
    val X0 = mk.ndarray(mk[mk[0.0, 0.0, 0.0]]).transpose()

    val result = solver.solve(equation, X0, epsilon )

    println(result)
}