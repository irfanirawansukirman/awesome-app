package com.irfanirawansukirman.tddmvpexample

import android.graphics.Color
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


/**
 * reference: [
 * https://www.vogella.com/tutorials/Mockito/article.html
 * https://medium.com/@shasindranpoonudurai/android-simple-and-easy-approach-to-test-driven-development-685261367060
 * ]
 */
@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    private lateinit var mainActivityPresenter: MainActivityPresenter

    @Mock
    private lateinit var mainActivityView: MainActivityViewInterface

    @Before
    fun setup() {
        mainActivityPresenter = MainActivityPresenter(mainActivityView)
    }

    @Test
    fun testIfPresenterUpdateCharacterLeftText() {
        //Arrange
        val noteLength = 150
        val maxLength = 200
        val count = (maxLength - noteLength).toString()

        //Act
        mainActivityPresenter.setCharacterCount(noteLength, maxLength)

        //Assert
        Mockito.verify<MainActivityViewInterface>(mainActivityView).updateCharacterCount(count)
    }

    @Test
    fun testIfPresenterUpdateLabelColorRed() {
        //Arrange
        val noteLength = 200
        val maxLength = 200
        val colorRed = Color.RED

        //Act
        mainActivityPresenter.setLabelColor(noteLength, maxLength)

        //Assert
        Mockito.verify<MainActivityViewInterface>(mainActivityView).updateTextColor(colorRed)
    }

    @Test
    fun testIfPresenterUpdateLabelColorGrey() {
        //Arrange
        val noteLength = 100
        val maxLength = 200
        val colorGrey = Color.GRAY

        //Act
        mainActivityPresenter.setLabelColor(noteLength, maxLength)

        //Assert
        Mockito.verify<MainActivityViewInterface>(mainActivityView).updateTextColor(colorGrey)
    }
}