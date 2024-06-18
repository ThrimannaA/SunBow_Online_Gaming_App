package com.example.onlinegamingapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

object GameData {
    //if a single player playing,store data as a local storage
    private var _gameModel:MutableLiveData<GameModel> = MutableLiveData()
    var gameModel:LiveData<GameModel> = _gameModel
    var myId=""

    fun saveGameModel(model: GameModel){
        _gameModel.postValue(model)

        //if only a online game should store in firebase-if mutiplayer playing
        if(model.gameId!="-1"){
            Firebase.firestore.collection("games")
                .document(model.gameId)
                .set(model)
        }

    }

    fun fetchGameModel(){
        gameModel.value?.apply{
            if(gameId!="-1"){
                Firebase.firestore.collection("games")
                    .document(gameId)
                    .addSnapshotListener { value, error ->
                        val model=value?.toObject(GameModel::class.java)
                        _gameModel.postValue(model)
                    }
            }
        }

    }

}