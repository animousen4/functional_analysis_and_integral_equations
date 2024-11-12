import org.jetbrains.kotlinx.multik.ndarray.data.D2Array

data class TaskResults(
    val x: D2Array<Double>,
    val iterationAmount: Int
) {
    override fun toString(): String {
        return "X = $x\nIteration amount = $iterationAmount"
    }
}
