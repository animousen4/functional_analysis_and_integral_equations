import org.jetbrains.kotlinx.multik.ndarray.data.D2Array

interface MatrixEquation {
    fun A() : D2Array<Double>
    fun B() : D2Array<Double>
}