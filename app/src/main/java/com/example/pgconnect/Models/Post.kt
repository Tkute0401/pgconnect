package com.example.pgconnect.Models

class Post {
    var posturl:String=""
    var caption:String=""
    var location:String=""
    var rent:String=""
    var teanant:String=""
    var room:String=""
    var uid:String=""
    var time:String=""
    constructor()
    constructor(
        posturl: String,
        caption: String,
        location: String,
        rent: String,
        teanant: String,
        room: String,
        uid:String,
        time:String
    ) {
        this.posturl = posturl
        this.caption = caption
        this.location = location
        this.rent = rent
        this.teanant = teanant
        this.room = room
        this.uid = uid
        this.time = time

    }

    constructor(
        posturl: String,
        caption: String,
        location: String,
        rent: String,
        teanant: String,
        room: String
    ) {
        this.posturl = posturl
        this.caption = caption
        this.location = location
        this.rent = rent
        this.teanant = teanant
        this.room = room
    }

    constructor(posturl: String, caption: String, location: String, rent: String) {
        this.posturl = posturl
        this.caption = caption
        this.location = location
        this.rent = rent
    }


}