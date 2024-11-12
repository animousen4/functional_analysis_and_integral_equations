import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log

class MethodOfSuccessiveApproximationsTaskSolver : TaskSolver {
    override fun solve(g: Equation, epsilon: Double, x0: Double, alpha: Double): EquationSolution {
        var iterationAmount = 1
        var xn : Double
        var xn1 = g.calculateEquation(x0)

        while (true) {
            xn = xn1
            xn1 = g.calculateEquation(xn)
            if (isInEpsilonRange(xn, xn1, alpha, epsilon)) {
                return EquationSolution(xn1, iterationAmount)
            }
            iterationAmount++
        }

    }

    override fun aprioriAmountOfIterations(g: Equation, epsilon: Double, x0: Double, alpha: Double): Int {
        val x1 = g.calculateEquation(x0)
        return floor(log(epsilon * (1 - alpha) / abs(x0 - x1), alpha)).toInt() + 1
    }


    private fun isInEpsilonRange(xn: Double, xn1: Double, alpha: Double, epsilon: Double): Boolean {
        return alpha / (1 - alpha) * abs(xn1 - xn) <= epsilon
    }

}