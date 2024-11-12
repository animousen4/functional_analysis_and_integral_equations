import kotlin.math.pow

fun main() {
    val equation = Variant6EquationF()
    val epsilon = 10.0.pow(-4)
    val x0 = 0.3
    val alpha = 7.0/8
    val solver = MethodOfSuccessiveApproximationsTaskSolver()

    val result = solver.solve(equation, epsilon, x0, alpha)
    val aprioriAmount = solver.aprioriAmountOfIterations(equation, epsilon, x0, alpha)

    println(result)
    println("Apriori amount: $aprioriAmount")
}