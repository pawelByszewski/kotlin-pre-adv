package eu.softisland.kotlin.playground


class BetterButton {


    fun setupListener(listener: Listener) {
    }

    interface Listener {

        fun onClick(text: String)
    }

}