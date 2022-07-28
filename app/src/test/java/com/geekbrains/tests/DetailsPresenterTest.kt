package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {
    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = DetailsPresenter(viewContract)
        presenter.onAttach(viewContract)
    }

    @Test
    fun onIncrement_Test() {
        val countIncrement = 1
        presenter.onIncrement()
        Mockito.verify(viewContract, Mockito.times(countIncrement)).setCount(countIncrement)
    }

    @Test
    fun onMultiIncrement_Test() {
        val countMultiIncrement = 3
        presenter.onIncrement()
        presenter.onIncrement()
        presenter.onIncrement()
        Mockito.verify(viewContract, Mockito.times(countMultiIncrement)).setCount(Mockito.anyInt())
    }

    @Test
    fun onDecrement_Test() {
        val countDecrement = - 1
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(1)).setCount(countDecrement)
    }

    @Test
    fun onMultiDecrement_Test() {
        val countMultiDecrement = 3
        presenter.onDecrement()
        presenter.onDecrement()
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(countMultiDecrement)).setCount(Mockito.anyInt())
    }

    @Test
    fun onAttach_Test() {
        presenter.onIncrement()
        presenter.onIncrement()
        presenter.onIncrement()
        Mockito.verify(viewContract, Mockito.times(3)).setCount(Mockito.anyInt())
    }

    @Test
    fun onDetach_Test() {
        presenter.onDetach()
        presenter.onIncrement()
        presenter.onIncrement()
        presenter.onDecrement()
        Mockito.verify(viewContract, Mockito.times(0)).setCount(Mockito.anyInt())
    }
}