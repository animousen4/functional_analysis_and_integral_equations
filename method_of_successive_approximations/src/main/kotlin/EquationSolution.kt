data class EquationSolution (
    val x: Double,
    val iterationAmount: Int,
) {
    override fun toString(): String {
        return "x = $x\nAmount of iterations: $iterationAmount"
    }
}