import org.jetbrains.kotlinx.multik.api.Multik.linalg
import org.jetbrains.kotlinx.multik.api.identity
import org.jetbrains.kotlinx.multik.api.linalg.*
import org.jetbrains.kotlinx.multik.ndarray.data.*
import org.jetbrains.kotlinx.multik.api.linalg.norm
import org.jetbrains.kotlinx.multik.api.*
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.sqrt

class SystemOfInhomogeneousLinearEquationsMethodOfSuccessiveApproximations : TaskSolver{
    override fun solve(matrixEquation: MatrixEquation, X0: D2Array<Double>, epsilon: Double): TaskResults {

        val matrixPairCD = calculateCD(matrixEquation);
        val alpha = calculateAlpha(matrixPairCD.first)

        var iterationAmount = 1
        var xn = X0
        var xn1 = performIteration(X0, matrixPairCD.first, matrixPairCD.second)

        while (true) {
            if (isInEpsilonRange(alpha, epsilon, xn ,xn1)){
                return TaskResults(xn1, iterationAmount);
            }
            xn = xn1
            xn1 = performIteration(xn, matrixPairCD.first, matrixPairCD.second)
            iterationAmount++

        }

    }


    private fun calculateCD(matrixEquation: MatrixEquation) : Pair<D2Array<Double>, D2Array<Double>> {
        val A = matrixEquation.A()
        val B = matrixEquation.B()

        val E = mk.identity<Double>(matrixEquation.A().shape[0])

        val AtA = linalg.dot(A.transpose(), A)
        val AtB = linalg.dot(A.transpose(), B)

        val maxLambda = calculateMaxLambda(AtA)

        val C = E - (AtA / maxLambda)
        val D = AtB / maxLambda

        return Pair(C, D);
    }

    private fun performIteration(X: D2Array<Double>, C: D2Array<Double>, D: D2Array<Double>) : D2Array<Double> {
        return linalg.dot(C,X) + D;
    }


    private fun isInEpsilonRange(alpha: Double, epsilon: Double, xn: D2Array<Double>, xn1: D2Array<Double> ): Boolean {
        return (alpha / (1 - alpha)) * linalg.norm(xn1 - xn) <= epsilon
    }

    private fun calculateMaxLambda(matrix: D2Array<Double>) : Double {
        return linalg.eigVals(matrix).map { it.re } .toList().max()
    }

    private fun calculateAlpha(C: D2Array<Double>) : Double {
        return sqrt(C.map { it * it }.sum())
    }

    override fun aprioriIterationAmount(matrixEquation: MatrixEquation, X0: D2Array<Double>, epsilon: Double): Int {
        val matrixPairCD = calculateCD(matrixEquation)
        val X1 = performIteration(X0, matrixPairCD.first, matrixPairCD.second)
        val alpha = calculateAlpha(matrixPairCD.first)
        return floor(log(epsilon * (1 - alpha) / linalg.norm(X0 - X1), alpha)).toInt() + 1
    }


}