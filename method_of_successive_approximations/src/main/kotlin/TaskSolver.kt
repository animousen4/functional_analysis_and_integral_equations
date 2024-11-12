interface TaskSolver {
    fun solve(g: Equation, epsilon: Double, x0: Double, alpha: Double): EquationSolution
    fun aprioriAmountOfIterations(g: Equation, epsilon: Double, x0: Double, alpha: Double): Int
}