import kotlin.math.pow

class Variant6EquationF : Equation {
    override fun calculateEquation(x: Double): Double {
        return x - (1.0/8) * (x.pow(7.0) + x.pow(3) + 2*x.pow(2) + x - 1)
    }

}