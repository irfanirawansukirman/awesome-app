package com.irfanirawansukirman.tddmvpexample

import android.graphics.Color

class MainActivityPresenter(private val mainActivityViewInterface: MainActivityViewInterface) :
    MainActivityPresenterInterface {

    override fun setCharacterCount(noteLength: Int, maxLength: Int) {
        val count = (maxLength - noteLength)
        mainActivityViewInterface.updateCharacterCount(count.toString())
    }

    override fun setLabelColor(noteLength: Int, maxLength: Int) {
        if (noteLength == maxLength) {
            mainActivityViewInterface.updateTextColor(Color.RED)
        } else {
            mainActivityViewInterface.updateTextColor(Color.GRAY)
        }
    }

}