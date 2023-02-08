package com.example.structure.ui.paging

import com.example.structure.data.model.GithubUser

sealed class PagingUiModel {
    data class UserItem(val items: GithubUser.ItemsItem) : PagingUiModel()
    data class SeparatorItem(val item: String) : PagingUiModel()
}