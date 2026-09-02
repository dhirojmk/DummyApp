package com.dhiroj.dummyapp.data.network

object ApiEndpoints {
    const val LOGIN = "auth/login"
    const val CURRENT_USER = "auth/me"
    const val QUOTES  = "/quotes"
    const val PRODUCTS = "products"
    const val PRODUCT_BY_ID = "products/{id}"
    const val SEARCH_PRODUCTS = "products/search"
    const val PRODUCT_CATEGORIES = "products/categories"
    const val PRODUCT_CATEGORY_LIST = "products/category-list"
    const val PRODUCTS_BY_CATEGORY = "products/category/{category}"
    const val ADD_PRODUCT = "products/add"
}