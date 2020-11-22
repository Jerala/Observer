fun main(args: Array<String>) {
    val counter = Counter()
    val observer = CounterObserver()
    counter.addObserver(observer)
    counter.increase().increase().decrease()
}

class Counter {
    private var number: Int = 0
    private var observers: MutableList<Observable> = arrayListOf()

    fun increase() : Counter {
        val current = number
        number++
        observers.forEach { it.observeValueChange(current, number) }
        return this
    }

    fun decrease() : Counter {
        val current = number
        number--
        observers.forEach { it.observeValueChange(current, number) }
        return this
    }

    fun addObserver(observable: Observable) {
        observers.add(observable)
    }

    fun removeObserver(observable: Observable) {
        observers.remove(observable)
    }
}

interface Observable {
    fun observeValueChange(oldValue: Any, newValue: Any)
}

class CounterObserver: Observable {
    override fun observeValueChange(oldValue: Any, newValue: Any) {
        println("Old value: $oldValue, new value: $newValue")
    }
}