package com.example.myreceipeapp

sealed class Route(val routeName: String){
    object CategoryScreen: Route("categoryScreen")
    object DetailScreen: Route("detailScreen")
}