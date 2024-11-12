import org.jetbrains.kotlinx.multik.ndarray.data.D2Array

interface TaskSolver {
    fun solve(matrixEquation: MatrixEquation, X0: D2Array<Double>, epsilon: Double) : TaskResults
    fun aprioriIterationAmount(matrixEquation: MatrixEquation, X0: D2Array<Double>, epsilon: Double) : Int
}