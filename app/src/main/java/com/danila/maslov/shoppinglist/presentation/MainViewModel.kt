package com.danila.maslov.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danila.maslov.shoppinglist.data.ShopListRepositoryImpl
import com.danila.maslov.shoppinglist.domain.DeleteShopItemUseCase
import com.danila.maslov.shoppinglist.domain.EditShopItemUseCase
import com.danila.maslov.shoppinglist.domain.GetShopListUseCase
import com.danila.maslov.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopItemUseCase.getShopList()

    fun deleteShopItem (shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}