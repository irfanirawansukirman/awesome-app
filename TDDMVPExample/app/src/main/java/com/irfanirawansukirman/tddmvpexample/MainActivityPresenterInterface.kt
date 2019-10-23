package com.irfanirawansukirman.tddmvpexample

interface MainActivityPresenterInterface {
    fun setCharacterCount(noteLength: Int, maxLength: Int)
    fun setLabelColor(noteLength: Int, maxLength: Int)
}