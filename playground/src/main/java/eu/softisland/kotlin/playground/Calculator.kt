package eu.softisland.kotlin.playground


class Calculator {

    var listener: (() -> Unit)? = null

    fun calculate() {
        if(listener != null) {
            val thread = object : Thread() {
                override fun run() {
                    sleep(4000)
                    listener!!()
                }
            }
            thread.start()
        }
    }
}

